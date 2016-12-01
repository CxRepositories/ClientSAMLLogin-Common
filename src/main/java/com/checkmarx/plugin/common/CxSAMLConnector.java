package com.checkmarx.plugin.common;

import com.checkmarx.plugin.common.cxservergateway.ICxServer;
import com.checkmarx.plugin.common.exception.SamlException;
import com.checkmarx.plugin.common.sdk.CxWSResponseLoginData;
import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;

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

    public CxWSResponseLoginData connect() throws Exception {
        String ott = samlWebBrowser.browseForOtt(cxServer.getRestURL() + SAML_LOGIN_RELATIVE_PATH, clientName);
        CxWSResponseLoginData loginResult = cxServer.LoginWithToken(ott);
        if (!loginResult.isIsSuccesfull()) {
            throw new SamlException(loginResult.getErrorMessage());
        }
        return loginResult;
    }

}
