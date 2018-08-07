package com.checkmarx.plugin.common.api;

import com.checkmarx.plugin.common.CxSAMLConnector;
import com.checkmarx.plugin.common.cxservergateway.CxServer;
import com.checkmarx.plugin.common.cxservergateway.ICxServer;
import com.checkmarx.plugin.common.webbrowsering.ISAMLWebBrowser;
import com.checkmarx.plugin.common.webbrowsering.SAMLLoginData;
import com.checkmarx.plugin.common.webbrowsering.SAMLWebBrowser;

import java.net.URL;

/**
 * Created by ehuds on 3/9/2017.
 */
public class CxSamlClientImpl implements CxSamlClient {

    URL serverUrl;
    String clientName;

    public CxSamlClientImpl(URL serverUrl, String clientName) {
        this.serverUrl = serverUrl;
        this.clientName = clientName;
    }

    @Override
    public SAMLLoginData login() throws Exception {
        ICxServer server = new CxServer(serverUrl.toString(), serverUrl.toString());
        ISAMLWebBrowser saml = new SAMLWebBrowser();
        CxSAMLConnector connector = new CxSAMLConnector(server, saml, clientName);
        return connector.connect();
    }

}
