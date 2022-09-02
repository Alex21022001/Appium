package org.example.testing.configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AddressConfiguration {
    private static final Logger logger = LogManager.getRootLogger();
    private static final String ERROR_LOG_LEVEL = "error";
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfiguration(){}

    public static AppiumDriverLocalService  getAppiumDriverLocalService(int port){
        if (appiumDriverLocalService==null)
           startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port){
        makePortAvailable(port);
        appiumDriverLocalService = new AppiumServiceBuilder().withIPAddress(ConfigReader.getConfigReader().appiumAddress()).usingPort(port)
                .withArgument(SESSION_OVERRIDE).withArgument(LOG_LEVEL,ERROR_LOG_LEVEL).build();
        appiumDriverLocalService.start();
        logger.info("Appium server was started on port {}",port);
    }

    public static void stopService(){
        Optional.ofNullable(appiumDriverLocalService).ifPresent(appiumServ -> {
            appiumServ.stop();
            logger.info("Service was stopped");
        });
    }

    private static boolean isPortFree(int port){
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)){
            logger.info("Specified port {} is available and ready to use",port);
        }catch (Exception e) {
            isFree=false;
            logger.warn("Specified port {} is occupied");
        }
        return isFree;
    }

    private static void makePortAvailable(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM node.exe");
            } catch (IOException e) {
                logger.error("Couldn't execute runtime command, message {}", e.getMessage());
            }
        }
    }
}
