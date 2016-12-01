package com.checkmarx.plugin.common.webbrowsering;

import com.checkmarx.plugin.common.exception.SamlException;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
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

/**
 * Created by eranb on 07/11/2016.
 */
public class SAMLWebBrowser extends JFrame implements ISAMLWebBrowser {

    private final Object lock = new Object();
    private String ott = null;
    private String error;
    private Browser browser;
    String clientName;
    JPanel contentPane;

    @Override
    public String browseForOtt(String samlURL, String clientName) throws SamlException {
        this.clientName = clientName;
        initBrowser(samlURL);
        if (hasErrors()) {
            throw new SamlException(error);
        }
        return ott;
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
            ott = extractOtt(html);
            closeBrowser();
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
        return event.getValidatedURL().contains("samlacs");
    }

    private String extractOtt(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    private boolean hasErrors() {
        return error != null && !error.isEmpty();
    }


}
