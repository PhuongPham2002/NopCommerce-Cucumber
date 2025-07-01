package helpers;

import commons.base.BrowserList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    String browser = System.getProperty("browser", "chrome");
//    public static WebDriver getBrowserDriver(String browserName, String url) {
//        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
//
//        switch (browserList){
//            case FIREFOX:
//                return new FirefoxDriver();
//                break;
//            case EDGE:
//                return new EdgeDriver();
//            case SAFARI:
//                return new SafariDriver();
//            default:
//                throw new RuntimeException("Trình duyệt nhập vào không được hỗ trợ "+browserName);
//        }
//
//
//    }

}
