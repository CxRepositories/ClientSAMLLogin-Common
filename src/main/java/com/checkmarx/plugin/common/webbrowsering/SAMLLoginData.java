package com.checkmarx.plugin.common.webbrowsering;

import com.checkmarx.plugin.common.sdk.CxWSResponseLoginData;
import com.teamdev.jxbrowser.chromium.Cookie;

/**
 * Created by ehuds on 12/4/2016.
 */
public class SAMLLoginData {
    private CxWSResponseLoginData cxWSResponseLoginData;
    private Cookie CXRFCookie;
    private Cookie CxCookie;

    public SAMLLoginData(CxWSResponseLoginData cxWSResponseLoginData, Cookie cxrfCookie, Cookie cxCookie) {
        this.cxWSResponseLoginData = cxWSResponseLoginData;
        this.CXRFCookie = cxrfCookie;
        this.CxCookie = cxCookie;
    }

    public CxWSResponseLoginData getCxWSResponseLoginData(){
        return this.cxWSResponseLoginData;
    }

    public Cookie getCXRFCookie() {
        return this.CXRFCookie;
    }

    public Cookie getCxCookie() {
        return this.CxCookie;
    }
}
