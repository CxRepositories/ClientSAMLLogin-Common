package com.checkmarx.plugin.common;

import com.checkmarx.plugin.common.cxservergateway.CxServer;
import com.checkmarx.plugin.common.cxservergateway.ICxServer;
import com.checkmarx.plugin.common.sdk.CxWSResponseLoginData;
import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.checkmarx.plugin.common.webbrowsering.SAMLWebBrowser;

public class Main {

    public static void main(String[] args) throws Exception {
        ICxServer server = new CxServer("http://10.31.2.44/", "http://10.31.2.44");
        ISAMLWebBrowser saml = new SAMLWebBrowser();
        CxSAMLConnector connector = new CxSAMLConnector(server, saml, "SDK");

        try {
            CxWSResponseLoginData connect = connector.connect();
            System.out.println("SessionId: " + connect.getSessionId());
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

    }
}
