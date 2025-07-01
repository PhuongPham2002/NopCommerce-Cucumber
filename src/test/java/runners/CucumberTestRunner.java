package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;




@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin ={"pretty", "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        dryRun = false,
        monochrome = true,
        tags="@Login or @Register"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests{
}