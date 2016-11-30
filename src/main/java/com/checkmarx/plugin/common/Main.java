package com.checkmarx.plugin.common;

import com.checkmarx.plugin.common.cxservergateway.CxServer;
import com.checkmarx.plugin.common.cxservergateway.ICxServer;
import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.checkmarx.plugin.common.webbrowsering.SAMLWebBrowser;

public class Main {

    public static void main(String[] args) throws Exception {
        ICxServer server = new CxServer("http://localhost:1515/", "http://localhost:63325/");
        ISAMLWebBrowser saml = new SAMLWebBrowser();
        CxSAMLConnector connector = new CxSAMLConnector(server, saml, "SDK");

        System.out.println(connector.connect());
    }
}
