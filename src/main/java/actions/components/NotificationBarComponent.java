package actions.components;

import commons.base.BasePage;
import interfaces.componentUI.NotificationBarComponentUI;
import org.openqa.selenium.WebDriver;

public class NotificationBarComponent extends BasePage {
    WebDriver driver;
    public NotificationBarComponent(WebDriver driver){
        this.driver=driver;
    }

    public String getNotificationMessage(String message) {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(driver, NotificationBarComponentUI.NOTIFICATION_MESSAGE,message);
        return getElementText(driver,NotificationBarComponentUI.NOTIFICATION_MESSAGE);

    }
    public void closeNotification(){
        waitForElementClickable(driver,NotificationBarComponentUI.CLOSE_BUTTON);
        clickElement(driver,NotificationBarComponentUI.CLOSE_BUTTON);
    }
    public void waitForNotificationToDisappear(){
        waitForElementInvisible(driver,NotificationBarComponentUI.NOTIFICATION_BAR);
    }


}
