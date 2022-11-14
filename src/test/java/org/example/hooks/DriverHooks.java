package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.driver.DriverManager;

public class DriverHooks {

    @Before
    public void setupDriver() {
        DriverManager.setupDriver();
    }

    @After(order = 1)
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
