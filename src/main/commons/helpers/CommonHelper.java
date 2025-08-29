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

    public static String generateUniqueEmail(){
        return "phuong"+ System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().substring(0,8)+"@gmail.com";
    }
    public static String generateRandomNumber(){
        return System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().substring(0,8);
    }

}
