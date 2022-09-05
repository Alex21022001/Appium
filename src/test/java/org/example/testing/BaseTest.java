package org.example.testing;

import org.example.testing.driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;



public class BaseTest {

    @BeforeAll
    public static void createSession(){
        DriverManager.getDriver();
    }

    @AfterEach
    public void resetApp(){
        DriverManager.getDriver().terminateApp("com.google.android.gm");
        DriverManager.closeDriver();
    }

    @AfterAll
    public static void closeSession(){
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
