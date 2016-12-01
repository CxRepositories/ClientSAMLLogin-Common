package com.checkmarx.plugin.common.webbrowsering;


import com.checkmarx.plugin.common.events.ISubscriber;
import com.checkmarx.plugin.common.exception.SamlException;
import com.teamdev.jxbrowser.chromium.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Created by eranb on 07/11/2016.
 */
public class SAMLWebBrowser extends JFrame implements ISubscriber, ISAMLWebBrowser {

    private static final String SUCCESS_EVENT_NAME = "Success";
    private static final String FAILURE_EVENT_NAME = "Failure";
    private static final String OTT = "?Ott=";


    private final Object lock = new Object();
    private String ott = null;
    private String error;
    private Browser browser;

    @Override
    public String browseForOtt(String samlURL, String clientName) throws SamlException {
        initBrowser(samlURL);
        if (hasErrors()) {
            throw new SamlException(error);
        }
        return ott;
    }

    private void initBrowser(String samlURL) {
        JPanel contentPane = new JPanel(new GridLayout(1, 1));
        browser = BrowserFactory.create();
        browser.loadURL(samlURL);
        contentPane.add(browser.getView().getComponent());
        browser.setLoadHandler(new LoadHandler() {
            @Override
            public boolean onLoad(LoadParams loadParams) {
                String url = loadParams.getURL();
                if (url.contains(OTT)) {
                    int start = url.indexOf(OTT);
                    ott = url.substring(start + OTT.length());
                }
                if (ott != null) {
                    synchronized (lock) {
                        dispatchEvent(new WindowEvent(SAMLWebBrowser.this, WindowEvent.WINDOW_CLOSING));
                        lock.notify();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean canNavigateOnBackspace() {
                return false;
            }

            @Override
            public boolean onCertificateError(CertificateErrorParams certificateErrorParams) {
                return false;
            }
        });
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

    private boolean hasErrors() {
        return error != null && !error.isEmpty();
    }

    @Override
    public void eventPublished(String eventName, String parameter) {
        if (SUCCESS_EVENT_NAME.equals(eventName)) {
            ott = parameter;
        } else if (FAILURE_EVENT_NAME.equals(eventName)) {
            error = parameter;
        }
    }

}
