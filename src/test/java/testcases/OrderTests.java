package testcases;

import actions.pageObject.*;
import commons.helpers.RegisterLoginHelper;
import commons.base.BaseTest;
import commons.helpers.CommonHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderTests extends BaseTest {
    public HomePageObject homePage;
    public DesktopsPageObject desktopsPage;
    public ProductDetailPageObject productDetailPage;
    public ShoppingCartPageObject shoppingCartPage;
    public NotebooksPageObject notebooksPage;
    public EstimateShippingPopupPageObject estimateShippingPopupPage;
    public CheckoutPageObject checkoutPage;

    public static final String ADD_TO_CART_SUCCESSFUL_MESSAGE="The product has been added to your shopping cart";
    public static final String EMPTY_PRODUCT_IN_SHOPPING_CART_MESSAGE="Your Shopping Cart is empty!";
    public int numberOfItem;
    public String ram,hdd,os,software,productPrice,quantity;

    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser, url);
        CommonHelper.setCookies(driver, RegisterLoginHelper.getNopCommerceCookie());
        homePage = PageGenerator.getHomePage(driver);
        desktopsPage = (DesktopsPageObject) homePage.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers", "Desktops");
        productDetailPage = desktopsPage.clickProduct("Build your own computer");
        numberOfItem=1;
        ram = "2 GB";
        hdd="320 GB";
        os="Vista Premium [+$60.00]";
        software="Acrobat Reader [+$10.00]";
        productPrice="1,335.00";
        quantity= String.valueOf(2);


    }
    @Test
    public void Order01_Add_Product_To_Cart() {
        productDetailPage.selectRAMValueDropdown(ram);
        productDetailPage.selectRadioButton("HDD",hdd);
        productDetailPage.selectRadioButton("OS",os);
        productDetailPage.selectSoftwareCheckbox(software);

        productDetailPage.clickAddToCartButton();
        Assert.assertEquals(productDetailPage.getSuccessfulMessageForAddingProductToCart(),ADD_TO_CART_SUCCESSFUL_MESSAGE);
        productDetailPage.closeNotification();
        productDetailPage.hoverToShoppingCartFromHeader();
       Assert.assertEquals(productDetailPage.getNumberOfItemInShoppingCart(),numberOfItem);
       Assert.assertTrue(productDetailPage.isProductDetailContained("Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]"));
       Assert.assertTrue(productDetailPage.isProductDetailContained("RAM: 2 GB"));
        Assert.assertTrue(productDetailPage.isProductDetailContained("HDD: 320 GB"));
        Assert.assertTrue(productDetailPage.isProductDetailContained("Vista Premium [+$60.00]"));
        Assert.assertTrue(productDetailPage.isProductDetailContained("Acrobat Reader [+$10.00]"));
       Assert.assertEquals(productDetailPage.getProductPriceInMiniShoppingCart(),productPrice);
       shoppingCartPage = productDetailPage.clickGoToCartButton();
    }

    @Test (dependsOnMethods = "Order01_Add_Product_To_Cart")
    public void Order02_Edit_Product_In_Shopping_Cart () {
        productDetailPage = shoppingCartPage.clickEditLink();
        productDetailPage.selectProcessorDropdown("2.2 GHz Intel Pentium Dual-Core E2200");
        productDetailPage.selectRAMValueDropdown("4GB [+$20.00]");
        productDetailPage.selectRadioButton("OS", "Vista Home [+$50.00]");
        productDetailPage.enterProductQuantity(quantity);
        Assert.assertEquals(productDetailPage.getProductPrice(), "1,330.00");
        productDetailPage.clickUpdateButton();
        Assert.assertEquals(productDetailPage.getSuccessfulMessageForAddingProductToCart(), ADD_TO_CART_SUCCESSFUL_MESSAGE);
        productDetailPage.closeNotification();
        shoppingCartPage = productDetailPage.clickShoppingCartFromHeader();
        Assert.assertEquals(shoppingCartPage.getQuantityOfProductInShoppingCart("Build your own computer"),"2");
        Assert.assertTrue(shoppingCartPage.isProductDetailContained("Build your own computer","Processor: 2.2 GHz Intel Pentium Dual-Core E2200"));
        Assert.assertTrue(shoppingCartPage.isProductDetailContained("Build your own computer","RAM: 4GB [+$20.00]"));
        Assert.assertTrue(shoppingCartPage.isProductDetailContained("Build your own computer","HDD: 320 GB"));
        Assert.assertTrue(shoppingCartPage.isProductDetailContained("Build your own computer","Vista Home [+$50.00]"));
        Assert.assertTrue(shoppingCartPage.isProductDetailContained("Build your own computer","Microsoft Office [+$50.00]"));
        Assert.assertEquals(shoppingCartPage.getProductPrice(), "$2,660.00");
    }


    @Test (dependsOnMethods = "Order01_Add_Product_To_Cart")
    public void Order03_RemoveFromCart() {
        shoppingCartPage.clickRemoveButton();
        Assert.assertEquals(shoppingCartPage.getEmptyProductMessageOfShoppingCart(),EMPTY_PRODUCT_IN_SHOPPING_CART_MESSAGE);
        Assert.assertTrue(shoppingCartPage.isShoppingCartEmpty());
    }

    @Test
    public void Order04_UpdateShoppingCart() {
        desktopsPage= (DesktopsPageObject) shoppingCartPage.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers","Desktops");
        desktopsPage.clickAddToCartButton("Lenovo IdeaCentre");
        shoppingCartPage=desktopsPage.clickShoppingCartLinkFromHeader();
        shoppingCartPage.enterProductQuantity("Lenovo IdeaCentre","5");
        Assert.assertEquals(shoppingCartPage.getTotalPriceOfShoppingCart("Lenovo IdeaCentre"),"$2,500.00");
    }

    @Test
    public void Order05_Checkout_Order_By_Cheque() {
        notebooksPage= (NotebooksPageObject) shoppingCartPage.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers","Notebooks");
        notebooksPage.clickAddToCartButton("Asus Laptop");
        shoppingCartPage=notebooksPage.clickShoppingCartLinkFromHeader();
        estimateShippingPopupPage=shoppingCartPage.clickEstimateShippingButton();
        estimateShippingPopupPage.selectCountryDropdown("Vietnam");
        estimateShippingPopupPage.selectCityDropdown("Hà Nội");
        estimateShippingPopupPage.enterPostalCode("100000");
        estimateShippingPopupPage.selectMethodShippingRadio("Next Day Air");

        shoppingCartPage=estimateShippingPopupPage.clickApplyButton();
        shoppingCartPage.clickTermOfServiceAgreementButton();
        checkoutPage=shoppingCartPage.clickCheckoutButton();

        checkoutPage.selectShipToTheSameAddressCheckbox();
        checkoutPage.enterFirstnameTextbox("Hau");
        checkoutPage.enterLastnameTextbox("Ho");
        checkoutPage.enterEmailAddressTextbox("hau@gmail.com");
        checkoutPage.selectCountryDropdown("Vietnam");
        checkoutPage.selectProvinceDropdown("Hà Nội");
        checkoutPage.enterCityTextbox("Ha Noi");
        checkoutPage.enterAddress1Textbox("Tay Mo, Ha Noi");
        checkoutPage.enterPostalCodeTextbox("100000");
        checkoutPage.enterPhoneNumberTextbox("123456789");

        checkoutPage.clickContinueButton("Billing address");
        Assert.assertTrue(checkoutPage.isPreviousCheckoutTabClosedAndNextCheckoutTabShowed("Billing address","Shipping method"));
        checkoutPage.selectShippingMethodRadioButton("Ground ($0.00)");
        checkoutPage.clickContinueButton("Shipping method");
        Assert.assertTrue(checkoutPage.isPreviousCheckoutTabClosedAndNextCheckoutTabShowed("Shipping method","Payment method"));
        checkoutPage.selectPaymentMethodRadioButton("Credit Card");
        checkoutPage.clickContinueButton("Payment method");

        checkoutPage.isPreviousCheckoutTabClosedAndNextCheckoutTabShowed("Payment method","Payment information");
        checkoutPage.selectCreditCardDropdown("Master card");
        checkoutPage.enterCardHolderName("Phuong Pham");
        checkoutPage.enterCardNumber("5238380325698321");
        checkoutPage.selectExpirationMonthDropdown("03");
        checkoutPage.selectExpirationYearDropdown("2027");
        checkoutPage.enterCardCode("314");
        checkoutPage.clickContinueButton("Payment information");
        checkoutPage.isPreviousCheckoutTabClosedAndNextCheckoutTabShowed("Payment information","Confirm order");

//
//        Assert.assertTrue(checkoutPage.isBillingAddress());
//        Assert.assertTrue(checkoutPage.isShippingAddressContains());
//        Assert.assertEquals(checkoutPage.getProductSKU(),"");
//        Assert.assertEquals(checkoutPage.getProductName(),"");
//        Assert.assertEquals(checkoutPage.getProductPrice(),"");
//        Assert.assertEquals(checkoutPage.getProductQuantity(),"");
//        Assert.assertEquals(checkoutPage.getProductTotal(),"");
//        Assert.assertTrue(checkoutPage.isGiftWrappingSelected());
//        Assert.assertEquals(checkoutPage.getSubTotalOfOrder(),"");
//        Assert.assertEquals(checkoutPage.getShippingFeeOfOrder(),"");
//        Assert.assertEquals(checkoutPage.getTaxOfOrder(),"");
//        Assert.assertEquals(checkoutPage.getTotalOfOrder(),"");
//        checkoutPage.clickConfirmButton();

    }



    @Test
    public void Order06_Checkout_Order_By_Card() {

    }

    @Test
    public void Order07_Re_Order_Product() {

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
