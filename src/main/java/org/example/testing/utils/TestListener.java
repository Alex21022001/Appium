package org.example.testing.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.testing.driver.DriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

public class TestListener implements TestWatcher {
    private static AppiumDriver<MobileElement> driver;
    private static final Logger logger = LogManager.getRootLogger();
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("Test {} was disabled",context.getRequiredTestMethod().getName());
        DriverManager.getDriver().resetApp();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("Test {} success",context.getRequiredTestMethod().getName());
        DriverManager.getDriver().resetApp();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.info("Test {} was aborted",context.getRequiredTestMethod().getName());
        DriverManager.getDriver().resetApp();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        driver = DriverManager.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File(format("target/screnshots/%s.png", LocalDate.now())));
        } catch (IOException e) {
            logger.error("Take screnshot was failed");
            throw new RuntimeException(e);
        }
        driver.resetApp();
    }
}
