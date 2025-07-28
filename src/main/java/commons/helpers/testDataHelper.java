package commons.helpers;

import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonAppend;
import org.apache.xmlbeans.SystemProperties;
import runners.Hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class testDataHelper {
    public static final ThreadLocal<Properties> testDataProp = new ThreadLocal<>();
     public static Properties loadData() {
         if (testDataProp.get()==null) {
             String filePath;
             Properties envProp = new Properties();
             switch (Hooks.TEST_ENV.toLowerCase()) {
                 case "dev":
                     filePath = "src/test/resources/env/envProperties/dev.properties";
                     break;
                 case "staging":
                     filePath = "src/test/resources/env/envProperties/staging.properties";
                     break;
                 case "production":
                     filePath = "src/test/resources/env/envProperties/prod.properties";
                     break;
                 default:
                     throw new RuntimeException("Invalid environment: " + Hooks.TEST_ENV);
             }
             try {
                 FileInputStream input = new FileInputStream(filePath);
                 envProp.load(input);
                 input.close();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

             Properties commonProp = new Properties();
             try {
                 FileInputStream input = new FileInputStream("src/test/resources/env/envProperties/common.properties");
                 envProp.load(input);
                 input.close();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

             envProp.putAll(commonProp);
             testDataProp.set(envProp);

         }

         return testDataProp.get();

     }

     public static String getData (String key){
         String value;
         if (key!= null){
           value = loadData().getProperty(key);
         } else {
         value= key;}

         return value;


     }


}
