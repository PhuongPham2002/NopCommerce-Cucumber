package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "runners"},
        plugin ={"pretty", "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        dryRun = false,
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    @BeforeClass
    public void setupAllureBeforeTest(){
        cleanAllureResultsFolder();
        generateAllureEnvironmentFile();

    }

    public void cleanAllureResultsFolder() {
        try {
            File allureResults = new File("allure-results");
            if (allureResults.exists()) {
                for (File file : allureResults.listFiles()) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
                System.out.println("🧹 Allure results folder cleaned before test run.");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to clean allure-results folder: " + e.getMessage());
        }
    }


    public void generateAllureEnvironmentFile() {
        try {
            Properties props = new Properties();
            props.setProperty("Environment", System.getProperty("environment", "local"));
            props.setProperty("Browser", System.getProperty("browser", "chrome"));
            props.setProperty("OS", System.getProperty("os.name"));

            File file = new File("allure-results/environment.properties");
            file.getParentFile().mkdirs(); // Ensure folder exists
            FileOutputStream fos = new FileOutputStream(file);
            props.store(fos, "Allure Environment Settings");
            fos.close();

            System.out.println("✅ Allure environment.properties generated.");
        } catch (IOException e) {
            System.out.println("❌ Failed to generate environment.properties: " + e.getMessage());
        }
    }

}