package commons.helpers;

import org.apache.xmlbeans.SystemProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class testDataHelper {

     public static Properties loadData() {
         String env = SystemProperties.getProperty("TestEnv", "dev");
         String envPath;
         Properties envProp = new Properties();
         switch (env.toLowerCase()){
             case "dev":
                 envPath="src/test/resources/env/envProperties/dev.properties";
                 break;
             case "staging":
                 envPath="src/test/resources/env/envProperties/staging.properties";
                 break;
             case "production":
                 envPath="src/test/resources/env/envProperties/prod.properties";
                 break;
             default:
                 throw new RuntimeException("Invalid environment: " + env);
         }
         try {
             FileInputStream input = new FileInputStream(envPath);
             envProp.load(input);
             input.close();
         } catch (IOException e) {
             throw new RuntimeException();
         }

         Properties commonProp = new Properties();
         try {
             FileInputStream input = new FileInputStream("src/test/resources/env/envProperties/common.properties");
             envProp.load(input);
             input.close();
         } catch (IOException e) {
             throw new RuntimeException();
         }

         envProp.putAll(commonProp);

         return envProp;
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
