package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;

    // Cucumber Hooks
    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
    }

    @Before()
    public void beforeScenario(Scenario scenario) {
        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");
        String implicityWait = ConfigReader.getProperty("implicityWait");
        driver = DriverManager.createDriver(browser, url, Integer.parseInt(implicityWait));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" + scenario.getName() + "\" }}");

    }

    @After()
    public void afterScenario(Scenario scenario) {
        // Cucumber after hook code
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (scenario.isFailed()) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Title not matched\"}}");
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot()).build());
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
        }
        DriverManager.quitDriver();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

    }

    public String getBase64Screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
