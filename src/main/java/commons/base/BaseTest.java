package commons.base;

import commons.helpers.CommonHelper;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
BaseTest thường sẽ chứa:
@BeforeMethod / @BeforeClass để setup WebDriver.
@AfterMethod / @AfterClass để quit WebDriver.
Các method hỗ trợ như getDriver(), closeBrowser(), v.v.
 */
public class BaseTest {
    WebDriver driver;
    protected final Logger log;
    public BaseTest(){
        log = LogManager.getLogger(getClass());
    }
    public WebDriver getDriver(){
        return driver;
    }
    public WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
            case SAFARI:
                driver = new SafariDriver();
            default:
                throw new RuntimeException("Trình duyệt nhập vào không được hỗ trợ "+browserName);
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;

    }

    public void closeBrowserDriver(){
        String cmd = null;

        try {
            if (driver != null){
                driver.manage().deleteAllCookies();
                driver.quit();
            }
           String osName = System.getProperty("os.name").toLowerCase();
           String driverInstanceName = driver.toString().toLowerCase();
           String browserDriverName;
           if (driverInstanceName.contains("chrome")){
               browserDriverName = "chromedriver";
           }
           else if (driverInstanceName.contains("firefox")){
               browserDriverName ="geckodriver";
           }
           else if (driverInstanceName.contains("edge")){
               browserDriverName ="edgedriver";
           } else {browserDriverName ="safaridriver";}

           if (osName.contains("window")){
               cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
           } else {
               cmd = "pkill " + browserDriverName;
           }
        }
        catch (Exception exp)
        {System.out.println(exp.getMessage());}//SAU NÀY NHỚ THÊM LOG4J VÀO ĐÂY NHÉ
        finally {
            try {
            Process process =Runtime.getRuntime().exec(cmd);
            process.waitFor();
            }
            catch (IOException e)
            {e.printStackTrace();}
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int generateRandomNumber() {
        return CommonHelper.generateRandomNumber();
    }
    public String generateUniqueEmail(){
        return CommonHelper.generateUniqueEmail();
    }


    @BeforeSuite
    public void deleteFileInReport() {
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        CommonHelper.deleteAllFileInFolder(folderName);
    }

    private final static ThreadLocal<List<Throwable>> errorCollector = ThreadLocal.withInitial(ArrayList::new);

    @Step("VERIFY TRUE  -> {message}")
    public void verifyTrue(boolean condition, String message){
        try {
            Assert.assertTrue(condition,message);
            log.info("PASSED: " + message);

        } catch (AssertionError e ){
            log.info("FAILED: "+message);
            errorCollector.get().add(e);

        }
    }

    @Step("ASSERT ALL VERIFICATIONS")
    public void assertAll(){
        List<Throwable> errors = errorCollector.get();
        if (!errors.isEmpty()){
            String combinedMessage = "Errors List:\n";
            for (int i=0;i<errors.size();i++){
                Throwable e = errors.get(i);
                combinedMessage += "- " + e.getMessage() + "\n";
            }
            errors.clear(); // xóa dánh sách lỗi để không ảnh hưởng test case sau
            Assert.fail(combinedMessage);
        }else {
            log.info("No errors found – assertAll PASSED.");
        }
    }
    @Step("VERIFY FALSE  -> {message}")
    public void verifyFalse(boolean condition, String message){
        try {
            Assert.assertFalse(condition,message);
            log.info("PASSED: " + message);

        } catch (AssertionError e ){
            log.info("FAILED: "+message);
            errorCollector.get().add(e);

        }
    }

    @Step("VERIFY EQUALS  -> {message}")
    public void verifyEquals(String actual,String expected, String message){
        try {
            Assert.assertEquals(actual,expected,message);
            log.info("[PASSED] " + message + " | Expected: " + expected + " | Actual: " + actual);

        } catch (AssertionError e ){
            log.error("[FAILED] " + message + " | Expected: " + expected + " | Actual: " + actual);
            errorCollector.get().add(e);

        }
    }

}
