package com.cx.plugin.common;

import com.cx.plugin.common.cxservergateway.ICxServer;
import com.cx.plugin.common.sdk.CxWSResponseLoginData;
import com.cx.plugin.common.webbrowsering.ISAMLWebBrowser;

public class CxSAMLConnector {

    private static final String SAML_LOGIN_RELATIVE_PATH = "/cxrestapi/auth/samlLogin";
    ICxServer cxServer;
    String clientName;
    ISAMLWebBrowser samlWebBrowser;

    public CxSAMLConnector(ICxServer cxServer, ISAMLWebBrowser samlWebBrowser, String clientName) {
        this.cxServer = cxServer;
        this.samlWebBrowser = samlWebBrowser;
        this.clientName = clientName;
    }

    public String Connect() throws Exception {
        String ott = samlWebBrowser.BrowseForOtt(cxServer.getRestURL() + SAML_LOGIN_RELATIVE_PATH, clientName);
        CxWSResponseLoginData loginResult = cxServer.LoginWithToken(ott);
        if (!loginResult.isIsSuccesfull())
            throw new Exception(loginResult.getErrorMessage());
        return loginResult.getSessionId();
    }

}
