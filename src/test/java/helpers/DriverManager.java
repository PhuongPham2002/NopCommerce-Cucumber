package helpers;

import interfaces.enums.BrowserList;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.java.vi.Nhưng;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static interfaces.enums.BrowserList.CHROME;

public class DriverManager {

    //Đảm bảo mỗi thread có 1 WebDriver riêng
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Gán driver từ bên ngoài vào thread local, nơi khởi tạo browser sẽ thường dùng hàm này

    //để truyền driver vào luôn
    public static void setDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }
    //Trả về webDriver hiện tại đang được lưu trong thread, có thể gọi bất cứ nơi đâu để thao tác
    public static WebDriver getDriver(){
       return driver.get();
    }

    private static String getBrowserName() {
        String browser = System.getProperty("browser");

        if (browser == null || browser.isEmpty()) {
            try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                browser = prop.getProperty("browser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        } else {
            browser = browser.toLowerCase();
        }

        return browser;
    }

    public static WebDriver getBrowserDriver(){
        String browser = getBrowserName();
        System.out.println("BrowserName: "+browser);
        BrowserList browserName = BrowserList.valueOf(browser.toUpperCase());
        switch (browserName){
            case CHROME:
                driver.set(new ChromeDriver());
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            default:
                throw new CucumberException("BrowserName is not valid: " + browserName);
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }


    public static void quitDriver(){
        if (driver.get()!=null){
            driver.get().quit();
            driver.remove();
        }
    }
    
}
