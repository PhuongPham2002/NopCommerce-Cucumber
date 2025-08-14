package interfaces.componentUI.header;

import runners.Hooks;

import java.util.HashMap;
import java.util.Map;

public class HeaderAccountComponentUI {
    static Map<String, String> result = new HashMap<>();
    public static final String WISHLIST_LINK ="CSS=a.ico-wishlist";
    public static final String SHOPPING_CART_LINK ="CSS=a.ico-cart";
    public static final String REGISTER_LINK ="css=a.ico-register";
    public final static String LOGOUT_LINK="css=a.ico-logout";
    public static String LOGIN_LINK="css=a.ico-login";
    public final static String ACCOUNT_LINK="css=a.ico-account";


    public static Map<String, String> defineElementLocatorBasedOnLanguage() {
        if (result.isEmpty()) {
            result.put(ACCOUNT_LINK, "");
            switch (Hooks.TEST_LANGUAGE) {
                case "vietnamese":
                    result.put(LOGIN_LINK, "css=#login");
                    break;
                case "english":
            }
        }
        return result;
    }
}
