package org.example.utils.Common;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common {
    protected static final Logger logger = LogManager.getLogger();

    // Read file propterties
    public Properties readFilePropterties(String path) {
        logger.info("Reading file properties from path = `{}`", path);

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }

        return properties;
    }

    // Get random a string
    public String getRandomString(int stringLength) {
        String alphaBe = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        Random random = new Random();

        for (int i = 0; i < stringLength; i++) {
            stringBuilder.append(alphaBe.charAt(random.nextInt(alphaBe.length())));
        }
        return stringBuilder.toString();
    }

    // Empty folder
}
