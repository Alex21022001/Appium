package org.example.testing.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.testing.configuration.AddressConfiguration;
import org.example.testing.configuration.CapabilitiesConfiguration;
import org.example.testing.configuration.ConfigReader;
import org.example.testing.configuration.EnvironmentType;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {
    private static final Logger logger = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType.valueOf(ConfigReader.getConfigReader().env().toUpperCase());
    private static AppiumDriver<MobileElement> driver;

    private DriverManager() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null)
            driver = createDriver();
        return driver;
    }

    private static AppiumDriver<MobileElement> createDriver() {
        System.out.println(ENVIRONMENT_TYPE);
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                driver = new AndroidDriver<MobileElement>(AddressConfiguration.getAppiumDriverLocalService(ConfigReader.getConfigReader().appiumPort()),
                        CapabilitiesConfiguration.getLocalCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
        }
        logger.info("Driver was created");
        logger.info("Environment type is {}", ENVIRONMENT_TYPE);
        return driver;
    }

    public static void closeDriver() {
        Optional.ofNullable(driver).ifPresent(x -> {
            x.quit();
            driver=null;
            logger.info("Driver was closed");
        });
    }

    public static void closeEmulator(){
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill",ConfigReader.getConfigReader().udid()));
            logger.info("AVD was closed");
        } catch (IOException e) {
            logger.error("AVD wasn't closed {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void closeAppium(){
        AddressConfiguration.stopService();
    }
}
