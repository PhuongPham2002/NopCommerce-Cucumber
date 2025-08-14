package commons.helpers;

import commons.constants.GlobalConstants;
import io.cucumber.core.exception.CucumberException;
import org.apache.commons.lang3.RandomStringUtils;
import runners.Hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import static commons.constants.GlobalConstants.*;

public class TestDataHelper {
    public static final ThreadLocal<Properties> testDataProp = new ThreadLocal<>();

    public static Properties loadData() {
        if (testDataProp.get() == null) {
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

    public static String getData(String key) {
        String value;
        if (key != null) {
            value = loadData().getProperty(key);
        } else {
            value = key;
        }
        if (value == null) {
            Random random = new Random();
            switch (key.toLowerCase()) {
                case "randomfirstname":
                    value = "Phuong" + RandomStringUtils.random(3);
                    break;
                case "randomlastname":
                    value = RandomStringUtils.random(6);
                    break;
                case "randomcompany":
                    value = COMPANY_LIST[random.nextInt(COMPANY_LIST.length)];
                    break;
                case "randongender":
                    value = GENDER_LIST[random.nextInt(GENDER_LIST.length)];
                    break;
                case "randomsubscribed":
                    value = SUBSCRIBED_STATUS[random.nextInt(GENDER_LIST.length)];
                default:
                    throw new CucumberException("key is not valid: " + key);
            }
        }
        testDataProp.get().put(key, value);

        return value;
    }
}
