package com.parabank.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties;

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
