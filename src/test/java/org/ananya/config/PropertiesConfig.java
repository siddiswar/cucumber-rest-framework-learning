package org.ananya.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {

    private static Properties properties = new Properties();
    // Load the properties file
    //This is a static block, which means it gets executed only once throughout the test execution lifecycle
    //Fetch the properties from the file and load them to properties object - only once
    static {
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
            //In the properties object, properties are stored as key-value pairs, key is the property name and value is the property value
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}


