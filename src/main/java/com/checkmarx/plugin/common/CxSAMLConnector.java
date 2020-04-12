package com.checkmarx.plugin.common;

import com.checkmarx.plugin.common.cxservergateway.ICxServer;
import com.checkmarx.plugin.common.exception.SamlException;
import com.checkmarx.plugin.common.sdk.CxWSResponseLoginData;
import com.checkmarx.plugin.common.webbrowsering.AuthenticationData;
import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.checkmarx.plugin.common.webbrowsering.SAMLLoginData;

public class CxSAMLConnector {

    private static final String SAML_LOGIN_RELATIVE_PATH = "/cxrestapi/auth/samlLogin";
    String cxServer;
    String clientName;
    ISAMLWebBrowser samlWebBrowser;

    public CxSAMLConnector(String cxServer, ISAMLWebBrowser samlWebBrowser, String clientName) {
        this.cxServer = cxServer;
        this.samlWebBrowser = samlWebBrowser;
        this.clientName = clientName;
    }

    public SAMLLoginData connect() throws Exception {
        AuthenticationData authenticationData = samlWebBrowser.browseAuthenticationData(cxServer + SAML_LOGIN_RELATIVE_PATH, clientName);

        if (authenticationData.wasCancled) {
            return new SAMLLoginData(true);
        }

        return new SAMLLoginData(authenticationData.CXRFCookie, authenticationData.CxCookie);
    }

}
