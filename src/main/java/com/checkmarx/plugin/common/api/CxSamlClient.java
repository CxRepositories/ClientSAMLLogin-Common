package com.checkmarx.plugin.common.api;

import com.checkmarx.plugin.common.webbrowsering.SAMLLoginData;

/**
 * Created by ehuds on 3/9/2017.
 */
public interface CxSamlClient {
    SAMLLoginData login() throws Exception;
}
