package com.checkmarx.plugin.common.sdk;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-11-20T11:18:37.665+02:00
 * Generated source version: 3.1.6
 */
@WebServiceClient(name = "CxSDKWebService",
        wsdlLocation = "classpath:com/checkmarx/v7/CxSDKWebService.wsdl",
        targetNamespace = "http://Checkmarx.com/v7")
public class CxSDKWebService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://Checkmarx.com/v7", "CxSDKWebService");
    public final static QName CxSDKWebServiceSoap12 = new QName("http://Checkmarx.com/v7", "CxSDKWebServiceSoap12");
    public final static QName CxSDKWebServiceSoap = new QName("http://Checkmarx.com/v7", "CxSDKWebServiceSoap");

    static {
        URL url = null;
        try {
            url = new URL("classpath:com/checkmarx/v7/CxSDKWebService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CxSDKWebService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "classpath:com/checkmarx/v7/CxSDKWebService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CxSDKWebService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CxSDKWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CxSDKWebService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public CxSDKWebService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CxSDKWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CxSDKWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     * @return returns CxSDKWebServiceSoap
     */
    @WebEndpoint(name = "CxSDKWebServiceSoap12")
    public CxSDKWebServiceSoap getCxSDKWebServiceSoap12() {
        return super.getPort(CxSDKWebServiceSoap12, CxSDKWebServiceSoap.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CxSDKWebServiceSoap
     */
    @WebEndpoint(name = "CxSDKWebServiceSoap12")
    public CxSDKWebServiceSoap getCxSDKWebServiceSoap12(WebServiceFeature... features) {
        return super.getPort(CxSDKWebServiceSoap12, CxSDKWebServiceSoap.class, features);
    }


    /**
     * @return returns CxSDKWebServiceSoap
     */
    @WebEndpoint(name = "CxSDKWebServiceSoap")
    public CxSDKWebServiceSoap getCxSDKWebServiceSoap() {
        return super.getPort(CxSDKWebServiceSoap, CxSDKWebServiceSoap.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CxSDKWebServiceSoap
     */
    @WebEndpoint(name = "CxSDKWebServiceSoap")
    public CxSDKWebServiceSoap getCxSDKWebServiceSoap(WebServiceFeature... features) {
        return super.getPort(CxSDKWebServiceSoap, CxSDKWebServiceSoap.class, features);
    }

}
