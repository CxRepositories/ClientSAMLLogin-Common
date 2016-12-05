package com.checkmarx.plugin.common.webbrowsering;

import com.checkmarx.plugin.common.exception.SamlException;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
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
    String clientName;
    JPanel contentPane;

    @Override
    public AuthenticationData browseAuthenticationData(String samlURL, String clientName) throws SamlException {
        this.clientName = clientName;
        initBrowser(samlURL);
        if (hasErrors()) {
            throw new SamlException(error);
        }
        return new AuthenticationData(CXRFCookie, CxCookie, ott);
    }

    private void initBrowser(String samlURL) {
        contentPane = new JPanel(new GridLayout(1, 1));
        BrowserPreferences.setUserAgent(clientName);
        browser = BrowserFactory.create();
        browser.loadURL(samlURL);
        contentPane.add(browser.getView().getComponent());
        browser.addLoadListener(AddResponsesHandler());
        setSize(700, 650);
        setLocationRelativeTo(null);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        setVisible(true);


        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        browser.dispose();
    }

    private LoadAdapter AddResponsesHandler() {
        return new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                handleOttResponse(event);
                handleErrorResponse(event);
            }
        };
    }

    private void handleErrorResponse(FinishLoadingEvent event) {
        if (event.isMainFrame() && (errorResponse(event))) {
            try {
                String queryStringParams = new URL(event.getValidatedURL()).getQuery();
                String[] params = queryStringParams.split("&");
            for (Integer i=0; i< params.length; i++) {
                    if (params[i].startsWith("Error")) {
                        error = java.net.URLDecoder.decode(params[i].substring(6), "UTF-8");
                        closeBrowser();
                        break;
                    }
            }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleOttResponse(FinishLoadingEvent event) {
        if (event.isMainFrame() && (ottResponse(event))) {
            Browser browser = event.getBrowser();
            DOMDocument document = browser.getDocument();
            String html = document.getDocumentElement().getInnerHTML();
            extractCxCoockies(browser.getCookieStorage().getAllCookies());
            extractOtt(html);
            closeBrowser();
        }
    }

    private void extractCxCoockies(List<Cookie> allCookies) {
        for (int i=0; i<allCookies.size(); i++){
            if (allCookies.get(i).getName().equals("CXCSRFToken")) {
                CXRFCookie = allCookies.get(i);
            }
            if (allCookies.get(i).getName().equals("cxCookie")) {
                CxCookie = allCookies.get(i);
            }
        }
    }


    private void closeBrowser() {
        synchronized (lock) {
            dispatchEvent(new WindowEvent(SAMLWebBrowser.this, WindowEvent.WINDOW_CLOSING));
            lock.notify();
        }
    }

    private boolean errorResponse(FinishLoadingEvent event) {
        return event.getValidatedURL().contains("Error=");
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
