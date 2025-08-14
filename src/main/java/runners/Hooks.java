package runners;

import commons.helpers.DriverManager;
import commons.helpers.ScenarioContext;
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
    public static final String TEST_LANGUAGE = System.getProperty("language", "english");
    WebDriver driver;
    ScenarioContext context;
    public final static String TEST_ENV = System.getProperty("testEnv", "dev");

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        driver = DriverManager.getBrowserDriver();
    }

    @After
    public void afterScenario() {
        if (context.get("emailChanged").equals(true)) {
            String originalEmail = context.get("originalEmail");
            //Call API để lấy lại email
        }
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
