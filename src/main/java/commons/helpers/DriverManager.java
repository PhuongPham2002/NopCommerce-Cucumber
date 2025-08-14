package commons.helpers;

import interfaces.enums.BrowserList;
import io.cucumber.core.exception.CucumberException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    //Đảm bảo mỗi thread có 1 WebDriver riêng
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Gán driver từ bên ngoài vào thread local, nơi khởi tạo browser sẽ thường dùng hàm này

    //để truyền driver vào luôn
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    //Trả về webDriver hiện tại đang được lưu trong thread, có thể gọi bất cứ nơi đâu để thao tác
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver getBrowserDriver() {
        String browser = System.getProperty("browser", "chrome");
        BrowserList browserName = BrowserList.valueOf(browser.toUpperCase());
        switch (browserName) {
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


    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
