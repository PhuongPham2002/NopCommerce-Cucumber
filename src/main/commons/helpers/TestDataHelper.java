package commons.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.exception.CucumberException;
import org.apache.commons.lang3.RandomStringUtils;
import runners.Hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static commons.constants.GlobalConstants.*;

public class TestDataHelper {
    public static String getData(String key) {
        String value;
        if (key != null) {
            value = loadDataJson().get(key);
            if (value == null) {
                Random random = new Random();
                switch (key.toLowerCase()) {
                    case "randomfirstname":
                        value = "Phuong" + RandomStringUtils.randomAlphabetic(6);
                        break;
                    case "randomlastname":
                        value = RandomStringUtils.randomAlphabetic(6);
                        break;
                    case "randomcompany":
                        value = COMPANY_LIST[random.nextInt(COMPANY_LIST.length)];
                        break;
                    case "randomgender":
                        value = GENDER_LIST[random.nextInt(GENDER_LIST.length)];
                        break;
                    case "randomsubscribed":
                        value = SUBSCRIBED_STATUS[random.nextInt(SUBSCRIBED_STATUS.length)];
                        break;
                    default:
                        throw new CucumberException("key is not valid: " + key);
                }
            }

        } else {
            value = key;
        }

        return value;
    }

    //Read all test data for environment and language from json file
    private static Map<String, String> loadDataJson() {
        Map<String, String> result = new HashMap<>();
        // Read environment test data
        result.putAll(loadEnvironmentTestData());
        //Read language test data
        result.putAll(loadLanguageTestData());
        return result;
    }

    private static Map<String, String> loadEnvironmentTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath;
        switch (Hooks.TEST_ENV.toLowerCase()) {
            case "dev":
                filePath = "src/test/resources/environment/json/dev.json";
                break;
            case "staging":
                filePath = "src/test/resources/environment/json/staging.json";
                break;
            case "production":
                filePath = "src/test/resources/environment/json/production.json";
                break;
            default:
                throw new CucumberException("Invalid Test Environment: " + Hooks.TEST_ENV);
        }
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return putJsonToMap(jsonNode);
    }

    private static Map<String, String> loadLanguageTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath;
        switch (Hooks.TEST_LANGUAGE.toLowerCase()) {
            case "vietnamese":
                filePath = "src/test/resources/environment/json/dev.json";
                break;
            case "english":
                filePath = "src/test/resources/environment/json/staging.json";
                break;
            default:
                throw new CucumberException("Invalid test language!");
        }
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return putJsonToMap(jsonNode);
    }


    // Read json with dynamic tree structure
    private static Map<String, String> putJsonToMap(JsonNode jsonNode) {
        Map<String, String> result = new HashMap<>();
        for (JsonNode node : jsonNode) {
            Set<Map.Entry<String, JsonNode>> setNode = node.properties();
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
                        putJsonToMap(value);
                    }
                }
            }
        }
        return result;
    }

}
