package com.checkmarx.plugin.common.webbrowsering;

import com.checkmarx.plugin.common.exception.SamlException;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.cookie.Cookie;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.event.Observer;
import com.teamdev.jxbrowser.navigation.event.FrameLoadFinished;
import com.teamdev.jxbrowser.net.HttpHeader;
import com.teamdev.jxbrowser.net.callback.BeforeSendHeadersCallback;
import com.teamdev.jxbrowser.os.Environment;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.checkmarx.plugin.common.configuration.PropertiesLoader.getLicenseKey;

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
           /* if (!BrowserCore.isInitialized()) {
                BrowserCore.initialize();
            }*/
        }

        contentPane = new JPanel(new GridLayout(1, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Engine engine = Engine.newInstance(EngineOptions
                .newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                .licenseKey(getLicenseKey())
                .build());

        engine.network().set(BeforeSendHeadersCallback.class, params -> {
            List<HttpHeader> httpHeaders = new ArrayList<>(params.httpHeaders());
            httpHeaders.add(HttpHeader.of("cxOrigin",clientName));
            return BeforeSendHeadersCallback.Response.override(httpHeaders);
        });


        browser = engine.newBrowser();
        browser.navigation().loadUrl(samlURL);
        contentPane.add(BrowserView.newInstance(browser));
        browser.navigation()
                .on(FrameLoadFinished.class, AddResponsesHandler());
        setSize(700, 650);
        setLocationRelativeTo(null);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                browser.close();
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

    private Observer<FrameLoadFinished> AddResponsesHandler() {
        return param -> {
                handleErrorResponse(param);
                handleOttResponse(param);
                if (hasOtt() || hasErrors())
                    closePopup();
        };
    }

    private boolean hasOtt() {
        return ott != null && !ott.isEmpty();
    }

    private void handleErrorResponse(FrameLoadFinished event) {
        if (event.frame().isMain()) {

            checkForUrlQueryErrors(event);
            if (!hasErrors())
                checkForBodyErrors(event);
        }
    }

    private void handleOttResponse(FrameLoadFinished event) {
        if (event.frame().isMain() && (ottResponse(event)) && !hasErrors()) {
            Optional<com.teamdev.jxbrowser.dom.Document> document = event.frame().document();
            document.ifPresent(doc -> {
                String html = doc.documentElement().get().innerHtml();
                extractCxCoockies(browser.engine().cookieStore().cookies());
                extractOtt(html);
            });

            response = new AuthenticationData(CXRFCookie, CxCookie, ott);
        }
    }

    private void extractCxCoockies(List<Cookie> allCookies) {
        CXRFCookie = allCookies.
                stream()
                .filter( cookie -> "CXCSRFToken".equalsIgnoreCase(cookie.name()))
                .findAny()
                .orElse(null);

        CxCookie = allCookies.
                stream()
                .filter( cookie -> "cxCookie".equalsIgnoreCase(cookie.name()))
                .findAny()
                .orElse(null);
    }

    private void closePopup() {
        dispatchEvent(new WindowEvent(SAMLWebBrowser.this, WindowEvent.WINDOW_CLOSING));
    }

    private void checkForUrlQueryErrors(FrameLoadFinished event) {
        if (!isUrlErrorResponse(event)) return;

        try {
            String queryStringParams = new URL(event.url()).getQuery();
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

    private boolean isUrlErrorResponse(FrameLoadFinished event) {
        return event.url().contains("Error=");
    }

    private void checkForBodyErrors(FrameLoadFinished event) {
        event.frame().document().ifPresent( doc -> {
            String content = doc.documentElement().get().innerHtml();

            if (!isBodyErrorResponse(content)) return;
            handleInternalServerError(content);

            if (hasErrors() || !content.contains("messageDetails")) return;
            extractMessageErrorFromBody(content);
        });

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

    private boolean ottResponse(FrameLoadFinished event) {
        return event.url().toLowerCase().contains("samlacs");
    }

    private void extractOtt(String html) {
        Document doc = Jsoup.parse(html);
        ott = doc.body().text();
    }

    private boolean hasErrors() {
        return error != null && !error.isEmpty();
    }

}
