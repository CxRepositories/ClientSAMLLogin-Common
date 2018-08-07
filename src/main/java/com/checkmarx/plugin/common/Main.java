/*
package com.checkmarx.plugin.common;

import com.checkmarx.plugin.common.cxservergateway.CxServer;
import com.checkmarx.plugin.common.cxservergateway.ICxServer;
 import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.checkmarx.plugin.common.webbrowsering.SAMLLoginData;
import com.checkmarx.plugin.common.webbrowsering.SAMLWebBrowser;

public class Main {

    public static void main(String[] args) throws Exception {
        ICxServer server = new CxServer("", "");
        ISAMLWebBrowser saml = new SAMLWebBrowser();
        CxSAMLConnector connector = new CxSAMLConnector(server, saml, "cx-SDK");

        try {
            SAMLLoginData connect = connector.connect();

            if (connect.wasCanceled) {
                System.out.println("wasCanceled: " + connect.wasCanceled);
            } else {
                System.out.println("SessionId: " + connect.getCxWSResponseLoginData().getSessionId());
                System.out.println("CxCookie: " + connect.getCxCookie().toString());
                System.out.println("CXRFCookie: " + connect.getCXRFCookie().toString());
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

    }
}
*/
