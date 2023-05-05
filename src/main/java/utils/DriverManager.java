package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }

    public static WebDriver createDriver(String browser, String url, int implictyWait) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.user", "eneserdoan_rOI8xL");
        caps.setCapability("browserstack.key", "ECXFTdLEPzsy637qC3AB");
        caps.setCapability("app", "bs://bb885e8e44b59be232ef95aff61bbb21b4f8ad18");
        caps.setCapability("device", "Samsung Galaxy S22 Ultra");
        caps.setCapability("os_version", "12.0");
        caps.setCapability("project", "Project Name");
        caps.setCapability("build", "Feature Name2");
        caps.setCapability("name", "TestName");
        WebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL("http://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        driverThread.set(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implictyWait));
        return driver;
    }
}
