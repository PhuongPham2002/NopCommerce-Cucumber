package runners;

import commons.helpers.CommonHelper;
import commons.helpers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {
    public static final String TEST_LANGUAGE = System.getProperty("language","english");
    WebDriver driver;
    public final static String TEST_ENV = System.getProperty("testEnv");

    @Before
    public void beforeScenario() {
        driver = DriverManager.getBrowserDriver();
    }


    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }


    @AfterStep
    public void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("❌ Screenshot on failure", new ByteArrayInputStream(screenshot));
        }


    }

}
