package interfaces.pageUI;

public class HomePageUI {
    public final static String REGISTER_LINK = "css=a.ico-register";
    public final static String SUCCESSFUL_REGISTER_MESSAGE ="XPATH=//div[contains(text(),'Your registration completed')]";
    public final static String LOGIN_LINK="css=a.ico-login";
    public final static String MY_ACCOUNT_LINK ="css=a.ico-account";
    public final static String HEADER_MENU_ITEM_BY_NAME="Xpath=//div[@class='header-menu']/ul[@class='top-menu notmobile']/li/a[contains(text(),'%s')]";
    public final static String DYNAMIC_SUBMENU_LINK ="xpath=//ul[@class='top-menu notmobile']/li/ul[@class='sublist first-level']//a[contains(text(),'%s')]";
}
