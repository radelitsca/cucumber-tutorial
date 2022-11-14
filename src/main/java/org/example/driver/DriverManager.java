package org.example.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverManager {

    private static final long IMPLICIT_WAIT_TIMEOUT = 2;
    private static final long PAGE_LOAD_TIMEOUT = 10;
    private static final String SELENOID_HUB = "http://192.168.88.102:4444/wd/hub";
    private static final String BROWSER = System.getProperty("browser", "firefox");
    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setupDriver() {
        WebDriver driver = getBrowser(Browser.valueOf(BROWSER.toUpperCase()));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        webDriverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static void quitDriver() {
        Optional.ofNullable(getDriver()).ifPresent(webDriver -> {
            webDriver.quit();
            webDriverThreadLocal.remove();
        });
    }

    private static WebDriver getBrowser(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("Wrong browser type provided. Choices are: chrome, firefox");
        }
    }

    private static WebDriver getChromeDriver() {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("106.0");
        capabilities.setCapability("selenoid:options", selenoidOptions);

        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
                    URI.create(SELENOID_HUB).toURL(), capabilities);
            remoteWebDriver.manage().window().setSize(new Dimension(1280, 1024));
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            return remoteWebDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static WebDriver getFirefoxDriver() {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("104.0");
        capabilities.setCapability("selenoid:options", selenoidOptions);

        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
                    URI.create(SELENOID_HUB).toURL(), capabilities);
            remoteWebDriver.manage().window().setSize(new Dimension(1280, 1024));
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            return remoteWebDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
