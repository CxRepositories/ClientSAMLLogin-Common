package com.checkmarx.plugin.common.webbrowsering;

import com.teamdev.jxbrowser.chromium.Cookie;

/**
 * Created by ehuds on 12/4/2016.
 */
public class AuthenticationData {
    public Cookie CXRFCookie;
    public Cookie CxCookie;
    public String Ott;
    public boolean wasCancled;

    public AuthenticationData(Cookie cxrfCookie, Cookie cxCookie, String ott) {
        CXRFCookie = cxrfCookie;
        CxCookie = cxCookie;
        Ott = ott;
    }

    public AuthenticationData(boolean wasCancled) {
        this.wasCancled = wasCancled;
    }
}
