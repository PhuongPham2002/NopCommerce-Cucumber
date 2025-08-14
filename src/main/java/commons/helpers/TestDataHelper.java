package commons.helpers;

import io.cucumber.core.exception.CucumberException;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import runners.Hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static commons.constants.GlobalConstants.*;

public class TestDataHelper {
    public static final ThreadLocal<Properties> testDataProp = new ThreadLocal<>();
    public static final ThreadLocal<Map<String, String>> TEST_DATA = new ThreadLocal<>();

    public static Properties loadData() {
        if (testDataProp.get() == null) {
            return loadTestDataProperties();
        } else return testDataProp.get();
    }

    private static Properties loadTestDataProperties() {
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
        return testDataProp.get();
    }

    public static String getData(String key) {
        String value;
        if (key != null) {
//            value = loadData().getProperty(key);
            value = loadDataJson().get(key);
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

    // Read all test data for environment and language from json file
    private static Map<String, String> loadDataJson() {
        Map<String, String> result = new HashMap<>();
        // Read environment test data
        result.putAll(loadEnvironmentTestData());
        // Read language test data
        result.putAll(loadLanguageTestData());
        return result;
    }

    private static Map<String, String> loadLanguageTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath;
        switch (Hooks.TEST_LANGUAGE.toLowerCase()) {
            case "vietnamese":
                filePath = "src/test/resources/test.data/json/language/vietnamese.json";
                break;
            case "english":
                filePath = "src/test/resources/test.data/json/language/english.json";
                break;
            default:
                throw new CucumberException("Invalid test language!");
        }
        JsonNode jsonNode;
        Map<String, String> output = new HashMap<>();
        try {
            jsonNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return putJsonToMap(jsonNode, output);
    }

    private static Map<String, String> loadEnvironmentTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath;
        switch (Hooks.TEST_ENV.toLowerCase()) {
            case "dev":
                filePath = "src/test/resources/test.data/json/environment/dev.json";
                break;
            case "uat":
                filePath = "src/test/resources/test.data/json/environment/uat.json";
                break;
            case "prod":
                filePath = "src/test/resources/test.data/json/environment/prod.json";
                break;
            default:
                throw new CucumberException("Invalid test environment!");
        }
        JsonNode jsonNode;
        Map<String, String> output = new HashMap<>();
        try {
            jsonNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return putJsonToMap(jsonNode, output);
    }

    // Read json with dynamic tree structure
    private static Map<String, String> putJsonToMap(JsonNode jsonNode, Map<String, String> result) {
        for (JsonNode node : jsonNode) {
            Set<Map.Entry<String, JsonNode>> setNode =  node.properties();
            if (setNode.isEmpty()) {
                for (Map.Entry<String, JsonNode> entry : jsonNode.properties()) {
                    JsonNode value = entry.getValue();
                    if (!value.isObject()) {
                        result.put(entry.getKey(), value.asText());
                    }
                }
            } else {
                for (Map.Entry<String, JsonNode> entry : setNode) {
                    JsonNode value = entry.getValue();
                    if (!value.isObject()) {
                        result.put(entry.getKey(), value.asText());
                    } else {
                        putJsonToMap(value, result);
                    }
                }
            }
        }
        return result;
    }
}
