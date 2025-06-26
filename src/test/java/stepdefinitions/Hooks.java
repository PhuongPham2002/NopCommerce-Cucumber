package stepdefinitions;

import commons.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;

public class Hooks {
    WebDriver driver;

    @Before
    public void beforeScenario (){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        driver.get("https://localhost:59579/");

    }


    @After
    public void afterScenario(){
        DriverManager.quitDriver();
        }


}
