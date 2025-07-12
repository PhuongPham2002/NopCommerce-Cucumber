package hooks;

import helpers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;

public class Hooks {
    WebDriver driver;

    @Before
    public void beforeScenario (){
        driver = DriverManager.getBrowserDriver();
    }


    @After
    public void afterScenario(){
        DriverManager.quitDriver();
        }


    @AfterStep
    public void takeScreenshotIfFailed(Scenario scenario){
        if (scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("❌ Screenshot on failure", new ByteArrayInputStream(screenshot));
        }


    }

}
