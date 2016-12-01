package com.checkmarx.plugin.common;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by: iland.
 * Date: 11/30/2016.
 */
public class SamlGUI extends JFrame implements ActionListener {

    private static final String TITLE = "Checkmarx SAML Login";

    private Browser browser;

    public SamlGUI() throws HeadlessException, IOException {
        setTitle(TITLE);

        JPanel contentPane = new JPanel(new GridLayout(2, 2));
        browser = BrowserFactory.create();
        browser.loadURL("http://www.google.com");
        contentPane.add(browser.getView().getComponent());

        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public Browser getBrowser() {
        return browser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
