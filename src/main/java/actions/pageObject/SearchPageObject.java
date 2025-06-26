package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.SearchPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends BasePage {
    WebDriver driver;

    //1. Locator
    public static final String EMPTY_KEYWORD_ERROR_MESSAGE ="Search term minimum length is 3 characters";
    public static final String NON_EXISTED_KEYWORD_ERROR_MESSAGE ="No products were found that matched your criteria.";

    //2.Constructor
    public SearchPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //3.Business actions

    public void searchProduct(String keyword){
        enterKeywordSearch(keyword);
        clickSearchButton();

    }
    public void enterKeywordAdvancedSearch(String keyword,String categoryOption){
        enterKeywordSearch(keyword);
        checkAdvancedSearchCheckbox();
        selectCategoryDropdown(categoryOption);
    }
    public void enterKeywordAdvancedSearch(String keyword,String categoryOption,String manufacturer){
        enterKeywordSearch(keyword);
        checkAdvancedSearchCheckbox();
        selectCategoryDropdown(categoryOption);
        checkSubCategorySearchCheckbox();
        selectManufacturerDropdown(manufacturer);
    }

    //4.Element-Level Actions
    public String getPageTitleText() {
        waitForElementVisible(driver, SearchPageUI.PAGE_TITLE_TEXT);
        return getElementText(driver,SearchPageUI.PAGE_TITLE_TEXT);
    }

    public void clickSearchButton() {
        waitForElementClickable(driver,SearchPageUI.SEARCH_BUTTON);
        clickElement(driver,SearchPageUI.SEARCH_BUTTON);
    }

    public String getValidationMessageForEmptyKeyword() {
        waitForLoadingScreenInvisible(driver);
        waitForTextToBePresentInElement(driver,SearchPageUI.EMPTY_KEYWORD_ERROR_MESSAGE,EMPTY_KEYWORD_ERROR_MESSAGE);
        return getElementText(driver,SearchPageUI.EMPTY_KEYWORD_ERROR_MESSAGE);
    }

    public void enterKeywordSearch(String keyword) {
        waitForElementVisible(driver,SearchPageUI.INPUT_TEXTBOX);
        sendKeyToElement(driver,SearchPageUI.INPUT_TEXTBOX,keyword);
    }

    public String getValidationMessageForNonExistedKeyword() {
        waitForLoadingScreenInvisible(driver);
        waitForTextToBePresentInElement(driver,SearchPageUI.NON_EXISTED_KEYWORD_MESSAGE,NON_EXISTED_KEYWORD_ERROR_MESSAGE);
        return getElementText(driver,SearchPageUI.NON_EXISTED_KEYWORD_MESSAGE);
    }

    public void checkAdvancedSearchCheckbox() {
        waitForElementClickable(driver,SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
        checkNativeCheckbox(driver,SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
    }

    public void selectCategoryDropdown(String categoryOption) {
        selectDropdownOption(driver,SearchPageUI.ADVANCED_SEARCH_CATEGORY_DROPDOWN,categoryOption);
        waitForElementSelected(driver,SearchPageUI.DYNAMIC_ADVANCED_SEARCH_CATEGORY_OPTION,categoryOption);
    }


    public void checkSubCategorySearchCheckbox() {
        waitForElementClickable(driver,SearchPageUI.ADVANCED_SEARCH_SUB_CATEGORY_CHECKBOX);
        clickElement(driver,SearchPageUI.ADVANCED_SEARCH_SUB_CATEGORY_CHECKBOX);
    }

    public void selectManufacturerDropdown(String manufacturer) {
        selectDropdownOption(driver,SearchPageUI.ADVANCED_SEARCH_MANUFACTURER_DROPDOWN,manufacturer);
        waitForElementSelected(driver,SearchPageUI.DYNAMIC_ADVANCED_SEARCH_MANUFACTURER_OPTION,manufacturer);
    }

    //5.VERIFICATION:

    public boolean isProductSizeEqualTo (int number) {
        waitForNumberOfElementsTobe(driver,SearchPageUI.PRODUCT_TITLE,number);
        return getListElementsSize(driver,SearchPageUI.PRODUCT_TITLE) == number;

    }

    public boolean isDisplayedProductEqualTo(String productName){
        waitForTextToBePresentInElement(driver,SearchPageUI.PRODUCT_TITLE,productName);
        return getElementText(driver,SearchPageUI.PRODUCT_TITLE).equals(productName);
    }

    public List<String> getDisplayedProductNames(){
        waitForListElementsVisible(driver,SearchPageUI.PRODUCT_TITLE);
        List<WebElement> listElement = getListElement(driver,SearchPageUI.PRODUCT_TITLE);
        List<String> listProductName = new ArrayList<>();
        for (WebElement element:listElement){
            listProductName.add(element.getText().trim());
        }
        return listProductName;
    }

    public boolean displayedProductsContain(String productName) {
        return getDisplayedProductNames().contains(productName);
    }
}
