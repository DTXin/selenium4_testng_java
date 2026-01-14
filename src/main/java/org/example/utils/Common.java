package org.example.utils;

import java.io.FileReader;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common {
    protected static final Logger logger = LogManager.getLogger();

    // Read file JSON
    public JsonObject ReadJsonWithGson(String fileName) {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        FileReader reader;

        try {
            reader = new FileReader(fileName);
            obj = gson.fromJson(reader, JsonObject.class);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return obj;
    }

    // Read file properties
    public Properties readFileProperties(String path) {
        logger.info("Reading file properties from path = `{}`", path);

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }

        return properties;
    }

    // Get value from file properties
    public String getPropertiesValue(String pathFile, String keyName) {
        Properties properties = readFileProperties(pathFile);
        return properties.getProperty(keyName);
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

}
