package actions.components.Footer;

import actions.pageObject.PageGenerator;
import actions.pageObject.SearchPageObject;
import commons.base.BasePage;
import interfaces.componentUI.footer.FooterComponentUI;
import org.openqa.selenium.WebDriver;

public class FooterComponent extends BasePage {
    WebDriver driver;
    public CustomerServiceComponent customerService;
    public InformationComponent information;
    public MyAccountComponent myAccount;
    public SocialMediaComponent socialMedia;
    public NewLetterComponent newLetter;

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        this.customerService = new CustomerServiceComponent(driver);
        this.information = new InformationComponent(driver);
        this.myAccount = new MyAccountComponent(driver);
        this.socialMedia = new SocialMediaComponent(driver);
        this.newLetter = new NewLetterComponent(driver);

    }


    public SearchPageObject clickSearchLink(String category, String subList) {
        waitForElementClickable(driver, FooterComponentUI.DYNAMIC_FOOTER_CATEGORY_LINK,category,subList);
        clickElement(driver,FooterComponentUI.DYNAMIC_FOOTER_CATEGORY_LINK,category,subList);
        return PageGenerator.getSearchPage(driver);
    }
}
