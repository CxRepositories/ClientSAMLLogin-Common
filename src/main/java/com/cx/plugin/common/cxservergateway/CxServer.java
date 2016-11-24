package com.cx.plugin.common.cxservergateway;

import com.cx.plugin.common.sdk.CxSDKWebService;
import com.cx.plugin.common.sdk.CxSDKWebServiceSoap;
import com.cx.plugin.common.sdk.CxWSResponseLoginData;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * Created by eranb on 06/11/2016.
 */
public class CxServer implements ICxServer {

    private static final QName SERVICE_NAME = new QName("http://Checkmarx.com/v7", "CxSDKWebService");
    private static URL WSDL_LOCATION = CxSDKWebService.class.getClassLoader().getResource("WEB-INF/CxSDKWebService.wsdl");
    private final CxSDKWebServiceSoap client;

    String serverSoapUrl, pluginSDK, serverRestURL;

    public CxServer(String soapUrl, String pluginSDK, String restURL) {
        this.serverSoapUrl = soapUrl;
        this.pluginSDK = pluginSDK;
        this.serverRestURL = restURL;
        CxSDKWebService service = new CxSDKWebService(WSDL_LOCATION, SERVICE_NAME);
        client = service.getCxSDKWebServiceSoap();
        BindingProvider bindingProvider = (BindingProvider) client;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serverSoapUrl + pluginSDK);
    }

    public String getRestURL() {
        return serverRestURL;
    }

    public CxWSResponseLoginData LoginWithToken(String ott) {
        return client.loginWithToken(ott, 1033);
    }

}
