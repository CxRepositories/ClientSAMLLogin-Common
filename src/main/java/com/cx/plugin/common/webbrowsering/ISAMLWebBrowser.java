package com.cx.plugin.common.webbrowsering;

/**
 * Created by eranb on 20/11/2016.
 */
public interface ISAMLWebBrowser {
    String BrowseForOtt(String samlURL, String clientName) throws Exception;
}
