package helpers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    //Đảm bảo mỗi thread có 1 WebDriver riêng
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Gán driver từ bên ngoài vào thread local, nơi khởi tạo browser sẽ thường dùng hàm này
    //để truyền driver vào luôn
    public static void setDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }
    //Trả vềeerwebdriver hiện tại đang được lưu trong thread, có thể gọi bất cứ nơi đâu để thao tác
    public static WebDriver getDriver(){
       return driver.get();
    }

    //
    public static void quitDriver(){
        if (driver.get()!=null){
            driver.get().quit();
            driver.remove();
        }
    }

}
