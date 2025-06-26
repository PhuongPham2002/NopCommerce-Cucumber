package commons.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import static commons.helpers.ElementHelper.getElement;

public class ActionHelper {
    protected static final Logger log = LogManager.getLogger(ActionHelper.class);

    public static void hoverToElementWithLog(WebDriver driver, String locatorForLog){
        try{
            log.info("Perform hover to element: "+locatorForLog);
            new Actions(driver).moveToElement(getElement(driver,locatorForLog)).perform();
            log.info("Hover to element successfully");
        } catch (NoSuchElementException e){
            log.error("Element is not found: "+locatorForLog);
            throw new RuntimeException("Element is not presence in DOM or wrong locator: "+locatorForLog,e);
        }catch (MoveTargetOutOfBoundsException e){
            log.error("Element is out of viewport or cannot be hovered: "+locatorForLog);
            throw new RuntimeException("Element cannot be hovered (out of viewport?): "+locatorForLog,e);
        }catch (ElementNotInteractableException e){
            log.error("Element is not displayed: "+locatorForLog);
            throw new RuntimeException("Element is not displayed: "+locatorForLog,e);
        }catch (Exception e){
            log.error("Unexpected error during hover: "+locatorForLog);
            log.info("Exception Message: "+ e.getMessage());
            throw new RuntimeException("Unexpected Error During Click: "+locatorForLog,e);
        }
    }
}
