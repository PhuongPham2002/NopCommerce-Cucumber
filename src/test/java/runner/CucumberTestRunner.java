package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin ={"pretty", "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed.txt"},
        dryRun = false,
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
