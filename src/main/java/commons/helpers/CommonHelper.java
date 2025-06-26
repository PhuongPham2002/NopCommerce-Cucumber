package commons.helpers;

import commons.constants.GlobalConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class CommonHelper {
    public static void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie:cookies){
            driver.manage().addCookie(cookie);}
        driver.navigate().refresh();}
    public static void sleepIgnoreInterrupted(long millis){
        try {
            Thread.sleep(millis);}
        catch (InterruptedException ignored){}
    }

    public static int generateRandomNumber() {
        Random rand = new Random();
        int upperLimit = 999;
        int lowerLimit = 9;
        return rand.nextInt(lowerLimit,upperLimit);
    }

    public static String generateUniqueEmail(){
        return "phuong"+ System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().substring(0,8)+"@gmail.com";
    }

    public static void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH +  File.separator+ folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static String generateRandomAlphanumeric(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

}
