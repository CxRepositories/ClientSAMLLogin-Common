package com.cx.plugin.common;

import com.cx.plugin.common.cxservergateway.CxServer;
import com.cx.plugin.common.cxservergateway.ICxServer;
import com.cx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.cx.plugin.common.webbrowsering.SAMLWebBrowser;

public class Main {

    public static void main(String[] args) {
        ICxServer server = new CxServer("http://localhost:1515/",  "http://localhost:63325/");
        ISAMLWebBrowser saml = new SAMLWebBrowser();
        CxSAMLConnector c = new CxSAMLConnector(server, saml, "SDK");

        try {
            System.out.println(c.Connect());
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
