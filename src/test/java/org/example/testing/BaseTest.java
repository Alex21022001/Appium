package org.example.testing;

import org.example.testing.driver.DriverManager;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    @BeforeAll
    public static void before(){
        DriverManager.getDriver();
    }

    @AfterAll
    public static void closeSession(){
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
