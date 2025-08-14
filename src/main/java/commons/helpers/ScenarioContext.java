package commons.helpers;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> data = new HashMap<>();
    public void set (String key, Object value){
        data.put(key,value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get (String key) {
        return (T) data.get(key);
    }
     public void clear(){
        data.clear();
     }
}
