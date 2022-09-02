package org.example.testing.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.testing.driver.DriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    protected static final long WAIT_TIME = 20;
    protected  AppiumDriver<MobileElement> driver = DriverManager.getDriver();

    public void waitForVisibilityOfElement(long waitTime, MobileElement element){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
