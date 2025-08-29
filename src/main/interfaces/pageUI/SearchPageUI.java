package interfaces.pageUI;

public class SearchPageUI {
    public final static String SEARCH_BUTTON = "CSS=button.search-button";
    public final static String PAGE_TITLE_TEXT ="css=div.page-title h1";
    public final static String EMPTY_KEYWORD_ERROR_MESSAGE ="CSS=div.search-results div.warning";
    public final static String INPUT_TEXTBOX ="css=input.search-text";
    public final static String NON_EXISTED_KEYWORD_MESSAGE="CSS=div.no-result";
    public final static String PRODUCT_TITLE="Css=h2.product-title a";
    public final static String ADVANCED_SEARCH_CHECKBOX ="xpath=//input[@id='advs' and @type='checkbox']";
    public final static String ADVANCED_SEARCH_CATEGORY_DROPDOWN ="CSS=div.advanced-search div.inputs select[id='cid']";
    public final static String DYNAMIC_ADVANCED_SEARCH_CATEGORY_OPTION ="xpath=//div[@class='advanced-search']/div[@class='inputs']/select[@id='cid']/option[text()='%s']";
    public final static String ADVANCED_SEARCH_CATEGORY_NON_EXISTED_KEYWORD_MESSAGE="CSS=div.no-result";
    public final static String ADVANCED_SEARCH_SUB_CATEGORY_CHECKBOX ="xpath=//input[@id='isc' and @type='checkbox']";

    public final static String ADVANCED_SEARCH_MANUFACTURER_DROPDOWN ="CSS=div.advanced-search div.inputs select[id='mid']";
    public final static String DYNAMIC_ADVANCED_SEARCH_MANUFACTURER_OPTION ="xpath=//div[@class='advanced-search']/div[@class='inputs']/select[@id='mid']/option[text()='%s']";



}
