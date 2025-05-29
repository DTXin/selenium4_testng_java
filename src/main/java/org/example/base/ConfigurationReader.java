package org.example.base;

import java.util.Properties;

public class ConfigurationReader {

    private String configurationPath = System.getProperty("user.dir") + "/src/main/resources/configuration.properties";
    private String propertyNotSpecifiedMessage = "is not specified in the Configuration.properties file";

    private String baseUrl;
    private String timeout;
    private String timeoutWait;
    private String remoteURL;

    public ConfigurationReader() {
        Properties properties = PageManager.getPageManager().getCommon().readFileProperties(configurationPath);

        baseUrl = properties.getProperty("baseUrl");
        timeout = properties.getProperty("timeout");
        timeoutWait = properties.getProperty("timeoutWait");
        remoteURL = properties.getProperty("remoteURL");
    }

    public String getBaseUrl() {
        if (!baseUrl.isEmpty())
            return baseUrl;
        else
            throw new RuntimeException("\"baseUrl\" " + propertyNotSpecifiedMessage);
    }

    public Long getTimeout() {
        if (!timeout.isEmpty())
            return Long.parseLong(timeout);
        else
            throw new RuntimeException("\"timeout\" " + propertyNotSpecifiedMessage);
    }

    public Long getTimeoutWait() {
        if (!timeoutWait.isEmpty())
            return Long.parseLong(timeoutWait);
        else
            throw new RuntimeException("\"timeoutWait\" " + propertyNotSpecifiedMessage);
    }

    public String getRemoteURL() {
        if (!remoteURL.isEmpty())
            return remoteURL;
        else
            throw new RuntimeException("\"retmoteURL\" " + propertyNotSpecifiedMessage);
    }
}
