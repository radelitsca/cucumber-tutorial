package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHook {

    private static final String PNG_FILE_EXTENSION = "image/png";

    @After
    public void takeScreenshot(Scenario scenario) {
        //TODO need to remove, for debugging
        System.out.println("Scenario: " + scenario);
        System.out.println("Driver: " + DriverManager.getDriver());
        System.out.println("Scenario name: " + scenario.getName());

        scenario.attach(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES),
                PNG_FILE_EXTENSION, scenario.getName());
    }
}
