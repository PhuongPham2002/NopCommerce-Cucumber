package actions.components.Header;

import actions.pageObject.*;
import commons.base.BasePage;
import interfaces.componentUI.header.HeaderProductCategoryComponentUI;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class HeaderProductCategoryComponent extends BasePage {
    WebDriver driver;

    public HeaderProductCategoryComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverToHeaderProductCategory(String productCategory) {
        waitForElementVisible(driver,HeaderProductCategoryComponentUI.DYNAMIC_PRODUCT_CATEGORY_HEADER,productCategory);
        hoverToElement(driver, HeaderProductCategoryComponentUI.DYNAMIC_PRODUCT_CATEGORY_HEADER,productCategory);

    }
    public void waitForSubProductCategoryVisible(String subProductCategory){
        waitForElementVisible(driver,HeaderProductCategoryComponentUI.DYNAMIC_SUB_PRODUCT_CATEGORY_LINK,subProductCategory);
    }


    //Xử lý điều hướng:

    public BasePage clickSubProductCategory (String productCategory,String subProductCategory){
        Map<String,Map<String,Class<? extends BasePage>>> categoryMap = new HashMap<String,Map<String,Class<? extends BasePage>>>();
        Map<String, Class<? extends BasePage>> computersMap = new HashMap<String, Class<? extends BasePage>>();
        computersMap.put("notebooks", NotebooksPageObject.class);
        computersMap.put("desktops", DesktopsPageObject.class);
        computersMap.put("software", SoftwarePageObject.class);
        log.info("ComputersMap value: "+computersMap);

        Map<String, Class<? extends BasePage>> electronicsMap = new HashMap<String, Class<? extends BasePage>>();
        electronicsMap.put("camera & photo", CamerasPhotoPageObject.class);
        electronicsMap.put("cell phones", CellphonesPageObject.class);
        electronicsMap.put("others", OthersPageObject.class);

        log.info("ElectronicsMap value: "+electronicsMap);

        Map<String, Class<? extends BasePage>> apparelMap = new HashMap<String, Class<? extends BasePage>>();
        electronicsMap.put("shoes", ShoesPageObject.class);
        electronicsMap.put("clothing", ClothingPageObject.class);
        electronicsMap.put("accessories", AccessoriesPageObject.class);
        log.info("ApparelMap value: "+electronicsMap);

        categoryMap.put("computers",computersMap);
        categoryMap.put("electronics",electronicsMap);
        categoryMap.put("apparel",apparelMap);
        log.info("CategoryMap value: "+ categoryMap);


        waitForElementClickable(driver, HeaderProductCategoryComponentUI.DYNAMIC_SUBMENU_LINK,subProductCategory);
        clickElement(driver,HeaderProductCategoryComponentUI.DYNAMIC_SUBMENU_LINK,subProductCategory);
        Map<String, Class<? extends BasePage>> subCategoryMap = categoryMap.get(productCategory.toLowerCase());

        if (subCategoryMap==null){
            throw new RuntimeException("product category is not valid: "+productCategory);
        }
        Class<? extends BasePage> pageClass = subCategoryMap.get(subProductCategory.toLowerCase());
        if (pageClass==null){
            throw new RuntimeException("Sub product Category is not valid: "+ subProductCategory+"for category: "+productCategory);
        }
        try {
            Constructor<? extends BasePage> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (Exception e){
            throw new RuntimeException("Cannot instantiate page: " + pageClass.getSimpleName(), e);
        }
        }

    }



