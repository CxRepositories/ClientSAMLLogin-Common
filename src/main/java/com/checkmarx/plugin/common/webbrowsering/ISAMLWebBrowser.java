package com.checkmarx.plugin.common.webbrowsering;

/**
 * Created by eranb on 20/11/2016.
 */
public interface ISAMLWebBrowser {

    /**
     *
     * @param samlURL
     * @param clientName
     * @return
     * @throws Exception
     */
    String browseForOtt(String samlURL, String clientName) throws Exception;

}
