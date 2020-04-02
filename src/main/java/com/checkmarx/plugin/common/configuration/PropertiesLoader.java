package com.checkmarx.plugin.common.configuration;


import java.io.IOException;
import java.util.Properties;

public abstract class PropertiesLoader {
    private static String licenseKey;

    public static String getLicenseKey() {
        if(licenseKey != null && "".equalsIgnoreCase(licenseKey)){
            Properties properties = new Properties();
            try {
                properties.load(
                        PropertiesLoader.class
                                .getClassLoader()
                                .getResourceAsStream("config.properties"));
                licenseKey =  properties.getProperty("jxbrowser.license.key");

            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        return licenseKey;
    }
}
