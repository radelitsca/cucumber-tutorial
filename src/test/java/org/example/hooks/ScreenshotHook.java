package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHook {

    private static final String PNG_FILE_EXTENSION = "image/png";

    @After(order = 10)
    public void takeScreenshot(Scenario scenario) {
        scenario.attach(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES),
                PNG_FILE_EXTENSION, scenario.getName());
    }
}
