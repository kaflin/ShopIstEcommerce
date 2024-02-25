package testCases.eCommerce.shoppingCartTest;

import base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjectModel.eCommerce.shoppingCartPage.AddProductToCartPage;

public class AddProductToCartTest extends BaseClass{
    private AddProductToCartPage addProductToCartPage;


    @Epic("Couch Cache")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test will add product to the shopping cart from the product listing page")
    @Test(priority = 1)
    public void addProductToShoppingCartFromProductListingPage() {
        addProductToCartPage=new AddProductToCartPage(driver);
        addProductToCartPage.addProductToShoppingCartFromProductListingPage();
    }
    @Epic("Couch Cache")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test will update Quantity of product within shopping cart")
    @Test(priority = 2)
    public void updateQuantityOfProductWithinShoppingCart() {
        addProductToCartPage=new AddProductToCartPage(driver);
        addProductToCartPage.updateQuantityOfProductWithinShoppingCart();
    }
    @Epic("Couch Cache")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test will remove Quantity of product from shopping cart")
    @Test(priority = 3)
    public void removeQuantityOfProductFromShoppingCart() {
        addProductToCartPage=new AddProductToCartPage(driver);
        addProductToCartPage.removeQuantityOfProductFromShoppingCart();
    }
    @Epic("Couch Cache")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test will Validate the shopping cart's total price updates correctly as\n" +
            "products are added, updated, or removed.")
    @Test(priority = 4)
    public void validatesTheShoppingCartsTotalPriceAsProductAddedOrUpdateOrRemoved() throws InterruptedException {
        addProductToCartPage=new AddProductToCartPage(driver);
        addProductToCartPage.validatesTheShoppingCartsTotalPriceAsProductAddedOrUpdateOrRemoved();
    }
}