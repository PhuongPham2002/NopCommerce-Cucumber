package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.SearchPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends BasePage {

    //1. Locator
    public static final String EMPTY_KEYWORD_ERROR_MESSAGE ="Search term minimum length is 3 characters";
    public static final String NON_EXISTED_KEYWORD_ERROR_MESSAGE ="No products were found that matched your criteria.";

    //2.Constructor
    public SearchPageObject(WebDriver driver) {

        super(driver);
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
        waitForElementVisible(SearchPageUI.PAGE_TITLE_TEXT);
        return getElementText(SearchPageUI.PAGE_TITLE_TEXT);
    }

    public void clickSearchButton() {
        waitForElementClickable(SearchPageUI.SEARCH_BUTTON);
        clickElement(SearchPageUI.SEARCH_BUTTON);
    }

    public String getValidationMessageForEmptyKeyword() {
        waitForLoadingScreenInvisible(driver);
        waitForTextToBePresentInElement(SearchPageUI.EMPTY_KEYWORD_ERROR_MESSAGE, EMPTY_KEYWORD_ERROR_MESSAGE);
        return getElementText(SearchPageUI.EMPTY_KEYWORD_ERROR_MESSAGE);
    }

    public void enterKeywordSearch(String keyword) {
        waitForElementVisible(SearchPageUI.INPUT_TEXTBOX);
        sendKeyToElement(SearchPageUI.INPUT_TEXTBOX, keyword);
    }

    public String getValidationMessageForNonExistedKeyword() {
        waitForLoadingScreenInvisible(driver);
        waitForTextToBePresentInElement(SearchPageUI.NON_EXISTED_KEYWORD_MESSAGE, NON_EXISTED_KEYWORD_ERROR_MESSAGE);
        return getElementText(SearchPageUI.NON_EXISTED_KEYWORD_MESSAGE);
    }

    public void checkAdvancedSearchCheckbox() {
        waitForElementClickable(SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
        checkNativeCheckbox(SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
    }

    public void selectCategoryDropdown(String categoryOption) {
        selectDropdownOption(SearchPageUI.ADVANCED_SEARCH_CATEGORY_DROPDOWN, categoryOption);
        waitForElementSelected(SearchPageUI.DYNAMIC_ADVANCED_SEARCH_CATEGORY_OPTION, categoryOption);
    }


    public void checkSubCategorySearchCheckbox() {
        waitForElementClickable(SearchPageUI.ADVANCED_SEARCH_SUB_CATEGORY_CHECKBOX);
        clickElement(SearchPageUI.ADVANCED_SEARCH_SUB_CATEGORY_CHECKBOX);
    }

    public void selectManufacturerDropdown(String manufacturer) {
        selectDropdownOption(SearchPageUI.ADVANCED_SEARCH_MANUFACTURER_DROPDOWN, manufacturer);
        waitForElementSelected(SearchPageUI.DYNAMIC_ADVANCED_SEARCH_MANUFACTURER_OPTION, manufacturer);
    }

    //5.VERIFICATION:

    public boolean isProductSizeEqualTo (int number) {
        waitForNumberOfElementsTobe(SearchPageUI.PRODUCT_TITLE, number);
        return getListElementsSize(SearchPageUI.PRODUCT_TITLE) == number;

    }

    public boolean isDisplayedProductEqualTo(String productName){
        waitForTextToBePresentInElement(SearchPageUI.PRODUCT_TITLE, productName);
        return getElementText(SearchPageUI.PRODUCT_TITLE).equals(productName);
    }

    public List<String> getDisplayedProductNames(){
        waitForListElementsVisible(SearchPageUI.PRODUCT_TITLE);
        List<WebElement> listElement = getListElement(SearchPageUI.PRODUCT_TITLE);
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
