package org.example.testing.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Properties properties = new Properties();
    private static ConfigReader instance;

    private ConfigReader() {
    }

    public static ConfigReader getConfigReader() {
        if (instance == null)
            instance = new ConfigReader();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/test.properties")));
        } catch (IOException e) {
            logger.error("Properties weren't loaded");
        }
        return instance;
    }

    public String env() {
        return properties.getProperty("env.type");
    }

    public String appPath() {
        return properties.getProperty("app.path");
    }

    public String appPackage() {
        return properties.getProperty("app.package");
    }

    public String appActivity() {
        return properties.getProperty("app.activity");
    }

    public String platformName() {
        return properties.getProperty("platform.name");
    }

    public String platformVersion() {
        return properties.getProperty("platform.version");
    }

    public String deviceName() {
        return properties.getProperty("local.device.name");
    }

    public String udid() {
        return properties.getProperty("udid");
    }

    public String appiumAddress() {
        return properties.getProperty("appium.address");
    }

    public int appiumPort() {
        return Integer.parseInt(properties.getProperty("appium.port"));
    }

}
