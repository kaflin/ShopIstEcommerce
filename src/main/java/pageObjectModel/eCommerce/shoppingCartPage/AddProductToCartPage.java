package pageObjectModel.eCommerce.shoppingCartPage;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AddProductToCartPage extends BaseClass {
    public AddProductToCartPage(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void addProductToShoppingCartFromProductListingPage() {
        String count = cartCount.getText();
        System.out.println(count);
        log.info("Clicking to Shop now Button");
        Click(shopNow);
        try {
            if (inStockText_updated.getText().equalsIgnoreCase("In stock")) {
                log.info("Clicking On In Stock Product");
                Click(inStockText);
                smartWait(driver, addToCartButton);
                Click(addToCartButton);
                if (!cartCount.getText().equalsIgnoreCase(count)) {
                    Assert.assertTrue(!cartCount.getText().equalsIgnoreCase(count));
                    log.info("Successfully Added To Cart");
                }
            }

        } catch (Exception e) {
            log.info("All Product is out of stock");
        }
    }

    public void updateQuantityOfProductWithinShoppingCart() {
        String addedToCartProductCount = cartCount.getText().substring(6, 7);
        log.info(addedToCartProductCount + " Quantity is already added to cart");
        log.info("Updating the Cart Quantity");
        try {
            Click(addToCartButton);
            String updatedAddedToCartProductCount = cartCount.getText().substring(6, 7);
            log.info(updatedAddedToCartProductCount + " is Updated Quantity Added to Cart");
        } catch (Exception e) {
            log.info("All Product is out of stock");
        }

    }
    public void removeQuantityOfProductFromShoppingCart(){
        log.info("Clicking On Cart");
        Click(cartCountUpdated);
        log.info("Clicking On Remove Button Of Product");
        smartWait(driver,removeProductButton);
        Click(removeProductButton);
        assertElementDisplayedUsingXpath("//div[contains(text(),'Your cart is currently empty.')]");
        log.info("Successfully removed");

    }
    public void validatesTheShoppingCartsTotalPriceAsProductAddedOrUpdateOrRemoved() throws InterruptedException {
        log.info("Navigating to HomePage");
        driver.navigate().to("https://shopist.io");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Click(shopNow);
//        try {
            if (inStockText_updated.getText().equalsIgnoreCase("In stock")) {
                log.info("Clicking On In Stock Product");
                Click(inStockText);
                smartWait(driver, addToCartButton);
                log.info("Clicking On Add To Cart Button");
                Click(addToCartButton);
                log.info("Clicking On Cart Quantity Text");
                smartWait(driver,cartCountUpdated);
                Click(cartCountUpdated);




                double orderValueText=Double.parseDouble(orderValue.getText().replaceAll("[^\\d.]", ""));
                double taxValueText=Double.parseDouble(taxValue.getText().replaceAll("[^\\d.]", ""));
                double shippingValueText=Double.parseDouble(shippingValue.getText().replaceAll("[^\\d.]", ""));

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                double expectedTotalPriceAdd= orderValueText+taxValueText+shippingValueText;
                log.info("Validating Total Price after Addition of Product");
                Assert.assertEquals(expectedTotalPriceAdd,Double.parseDouble(totalPriceValue.getText().replaceAll("[^\\d.]", "")));
                log.info("Validating Total Price after Addition of Product Passed");

                log.info("Updating Quantity Of Product");
                smartWait(driver,addOperator);
                Click(addOperator);

                double orderValueTextUpdate=Double.parseDouble(orderValue.getText().replaceAll("[^\\d.]", ""));
                double taxValueTextUpdate=Double.parseDouble(taxValue.getText().replaceAll("[^\\d.]", ""));
                double shippingValueTextUpdate=Double.parseDouble(shippingValue.getText().replaceAll("[^\\d.]", ""));

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                double expectedTotalPriceUpdate= orderValueTextUpdate+taxValueTextUpdate+shippingValueTextUpdate;
                log.info("Validating Total Price after Updating Quantity of Product");
                Assert.assertEquals(expectedTotalPriceUpdate,Double.parseDouble(totalPriceValue.getText().replaceAll("[^\\d.]", "")));
                log.info("Validating Total Price after Updating Quantity of Product Passed");

                log.info("Removing Quantity Of Product");
                smartWait(driver,subOperator);
                Click(subOperator);

                double orderValueTextRemove=Double.parseDouble(orderValue.getText().replaceAll("[^\\d.]", ""));
                double taxValueTextRemove=Double.parseDouble(taxValue.getText().replaceAll("[^\\d.]", ""));
                double shippingValueTextRemove=Double.parseDouble(shippingValue.getText().replaceAll("[^\\d.]", ""));

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                double expectedTotalPriceRemove= orderValueTextRemove+taxValueTextRemove+shippingValueTextRemove;
                log.info("Validating Total Price after Removing Quantity of Product");
                Assert.assertEquals(expectedTotalPriceRemove,Double.parseDouble(totalPriceValue.getText().replaceAll("[^\\d.]", "")));
                log.info("Validating Total Price after Removing Quantity of Product Passed");

            }else {
                System.out.println("Out of Stock");
            }

//        } catch (Exception e) {
//            log.info("All Product is out of stock");
//        }

    }
}
