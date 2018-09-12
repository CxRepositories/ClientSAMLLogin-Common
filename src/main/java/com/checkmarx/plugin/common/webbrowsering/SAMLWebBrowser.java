package com.checkmarx.plugin.common.webbrowsering;

import com.checkmarx.plugin.common.exception.SamlException;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.internal.Environment;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.DefaultNetworkDelegate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by eranb on 07/11/2016.
 */
public class SAMLWebBrowser extends JFrame implements ISAMLWebBrowser {

    private final Object lock = new Object();
    private String ott = null;
    private Cookie CXRFCookie;
    private Cookie CxCookie;
    private String error;
    private Browser browser;
    private AuthenticationData response;
    String clientName;
    JPanel contentPane;

    static {
        System.setProperty("jxbrowser.ipc.external", "true");
        System.setProperty("java.ipc.external", "true");
    }

    @Override
    public AuthenticationData browseAuthenticationData(String samlURL, String clientName) throws SamlException {
        this.clientName = clientName;
        initBrowser(samlURL);
        waitForAuthentication();
        if (hasErrors()) {
            throw new SamlException(error);
        }

        return response;
    }

    private void initBrowser(String samlURL) {
        if (Environment.isMac()) {
            if (!BrowserCore.isInitialized()) {
                BrowserCore.initialize();
            }
        }

        contentPane = new JPanel(new GridLayout(1, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BrowserContext browserContext = BrowserContext.defaultContext();
        browserContext.getNetworkService().setNetworkDelegate(new DefaultNetworkDelegate() {
            @Override
            public void onBeforeSendHeaders(BeforeSendHeadersParams params) {
                params.getHeaders().setHeader("cxOrigin", clientName);
            }
        });
        browser = new Browser(browserContext);
        browser.loadURL(samlURL);
        contentPane.add(new BrowserView(browser));
        browser.addLoadListener(AddResponsesHandler());
        setSize(700, 650);
        setLocationRelativeTo(null);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                browser.dispose();
                if (response == null) {
                    response = new AuthenticationData(true);
                }
                notifyAuthenticationFinish();
            }
        });
        setVisible(true);
    }

    private void notifyAuthenticationFinish() {
        synchronized (lock) {
            lock.notify();
        }
    }

    private void waitForAuthentication() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private LoadAdapter AddResponsesHandler() {
        return new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                handleErrorResponse(event);
                handleOttResponse(event);
                if (hasOtt() || hasErrors())
                    closePopup();
            }

        };
    }

    private boolean hasOtt() {
        return ott != null && !ott.isEmpty();
    }

    private void handleErrorResponse(FinishLoadingEvent event) {
        if (event.isMainFrame()) {

            checkForUrlQueryErrors(event);
            if (!hasErrors())
                checkForBodyErrors(event);
        }
    }

    private void handleOttResponse(FinishLoadingEvent event) {
        if (event.isMainFrame() && (ottResponse(event)) && !hasErrors()) {
            Browser browser = event.getBrowser();
            DOMDocument document = browser.getDocument();
            String html = document.getDocumentElement().getInnerHTML();
            extractCxCoockies(browser.getCookieStorage().getAllCookies());
            extractOtt(html);
            response = new AuthenticationData(CXRFCookie, CxCookie, ott);
        }
    }

    private void extractCxCoockies(List<Cookie> allCookies) {
        for (int i = 0; i < allCookies.size(); i++) {
            if (allCookies.get(i).getName().equals("CXCSRFToken")) {
                CXRFCookie = allCookies.get(i);
            }
            if (allCookies.get(i).getName().equals("cxCookie")) {
                CxCookie = allCookies.get(i);
            }
        }
    }

    private void closePopup() {
        dispatchEvent(new WindowEvent(SAMLWebBrowser.this, WindowEvent.WINDOW_CLOSING));
    }

    private void checkForUrlQueryErrors(FinishLoadingEvent event) {
        if (!isUrlErrorResponse(event)) return;

        try {
            String queryStringParams = new URL(event.getValidatedURL()).getQuery();
            String[] params = queryStringParams.split("&");
            for (Integer i = 0; i < params.length; i++) {
                if (params[i].startsWith("Error")) {
                    error = java.net.URLDecoder.decode(params[i].substring(6), "UTF-8");
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private boolean isUrlErrorResponse(FinishLoadingEvent event) {
        return event.getValidatedURL().contains("Error=");
    }

    private void checkForBodyErrors(FinishLoadingEvent event) {
        Browser browser = event.getBrowser();
        DOMDocument document = browser.getDocument();
        String content = document.getDocumentElement().getInnerHTML();

        if (!isBodyErrorResponse(content)) return;
        handleInternalServerError(content);

        if (hasErrors() || !content.contains("messageDetails")) return;
        extractMessageErrorFromBody(content);
    }

    private void handleInternalServerError(String content) {
        if (content.contains("HTTP 500")) {
            error = "Internal server error";
        }
    }

    private void extractMessageErrorFromBody(String content) {
        String[] contentComponents = content.split("\\r?\\n");
        for (String component : contentComponents) {
            if (component.contains("messageDetails")) {
                error = component.split(":")[1].trim();
                TrimError();
                break;
            }
        }
    }

    private void TrimError() {
        if (error.startsWith("\""))
            error = error.substring(1);
        if (error.endsWith("\""))
            error = error.substring(0, error.length() - 1);
    }

    private boolean isBodyErrorResponse(String content) {

        return content.toLowerCase().contains("messagecode");
    }

    private boolean ottResponse(FinishLoadingEvent event) {
        return event.getValidatedURL().toLowerCase().contains("samlacs");
    }

    private void extractOtt(String html) {
        Document doc = Jsoup.parse(html);
        ott = doc.body().text();
    }

    private boolean hasErrors() {
        return error != null && !error.isEmpty();
    }

}
