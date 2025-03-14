package org.example.utils.FileHelper;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FileHelper {
    protected static final Logger logger = LogManager.getLogger();

    private static final String saveFilePath = System.getProperty("user.dir") + "/src/test/resources/download/";

    // Create new certificates
    public void createCertificates() {
        logger.info("Create a new certificates to apply for https request...");

        try {
            // Create a new trust manager that trusts all certificates
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Activate the new trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // Download file
    public void downloadFile(String urlDownload) {
        // Trusts all certificates
        createCertificates();

        logger.info("Downloading file from url `{}`", urlDownload);
        String fileName = urlDownload.substring(urlDownload.lastIndexOf("/") + 1);

        try {
            URL url = new URI(urlDownload).toURL();
            URLConnection connection = url.openConnection();

            FileUtils.copyURLToFile(connection.getURL(), new File(saveFilePath + fileName));
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // Check file download
    public boolean checkFileDownload(String downloadFileName) {
        File directory = new File(saveFilePath);
        String[] filesList = directory.list();

        int flag = 0;
        if (filesList != null) {
            for (String fileName : filesList) {
                if (fileName.equalsIgnoreCase(downloadFileName)) {
                    logger.info("File `{}` is downloaded successfully", downloadFileName);
                    flag = 1;
                }
            }
        } else {
            logger.info("No file is downloaded");
            return false;
        }

        if (flag == 0) {
            logger.info("File `{}` is not downloaded", downloadFileName);
            return false;
        }

        return true;
    }

    // Empty folder before download

    // Rename file

    // Unzip file

    // Unzip file to folder

}
