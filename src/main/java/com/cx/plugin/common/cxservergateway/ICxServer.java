package com.cx.plugin.common.cxservergateway;

import com.cx.plugin.common.sdk.CxWSResponseLoginData;

/**
 * Created by eranb on 06/11/2016.
 */
public interface ICxServer {
    String getRestURL();

    CxWSResponseLoginData LoginWithToken(String ott);
}
