package commons.constants;

public class GlobalConstants {

    //Sử dụng cho hàm wait
    public static final long TIMEOUT = 30;
    public static final long POLLING_TIME = 500;

    //System Info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String SEPARATOR = System.getProperty("file.separator");

    //Report
    public static final String ALLURE_REPORT = PROJECT_PATH + SEPARATOR + "allure-html" + SEPARATOR;

    //Login info
    public static final String LOGIN_EMAIL = "admin@yourstore.com";
    public static final String LOGIN_PASSWORD = "admin123";

    public static final String[] COMPANY_LIST = {"Viettel", "FPT", "CMC","Rackspace","VietSoftware","NAB"};
    public static final String[] GENDER_LIST = {"Female","Male"};
    public static final String[] SUBSCRIBED_STATUS ={"subscribed", "unsubscribe"};
}
