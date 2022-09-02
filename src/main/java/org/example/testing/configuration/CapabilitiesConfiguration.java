package org.example.testing.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class CapabilitiesConfiguration {
    private CapabilitiesConfiguration(){}

    public static DesiredCapabilities getLocalCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID,ConfigReader.getConfigReader().udid());
        capabilities.setCapability(AVD,ConfigReader.getConfigReader().deviceName());
        capabilities.setCapability(APP_PACKAGE,ConfigReader.getConfigReader().appPackage());
        capabilities.setCapability(APP_ACTIVITY,ConfigReader.getConfigReader().appActivity());
        capabilities.setCapability(APP,new File(ConfigReader.getConfigReader().appPath()).getAbsolutePath());

        return capabilities;
    }
}
