package pageObject.components;

import pageObject.BasePage;
import interfaces.componentUI.NotificationBarComponentUI;
import org.openqa.selenium.WebDriver;

public class NotificationBarComponent extends BasePage {
    public NotificationBarComponent(WebDriver driver){
        super(driver);
    }

    public String getNotificationMessage(String message) {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(NotificationBarComponentUI.NOTIFICATION_MESSAGE, message);
        return getElementText(NotificationBarComponentUI.NOTIFICATION_MESSAGE);

    }
    public void closeNotification(){
        waitForElementClickable(NotificationBarComponentUI.CLOSE_BUTTON);
        clickElement(NotificationBarComponentUI.CLOSE_BUTTON);
    }
    public void waitForNotificationToDisappear(){
        waitForElementInvisible(NotificationBarComponentUI.NOTIFICATION_BAR);
    }


}
