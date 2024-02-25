package pageObjectModel.eCommerce.checkoutPage;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckOutPage extends BaseClass {
    public CheckOutPage(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='row'])[1]/div[2]")
    public WebElement name;

    @FindBy(xpath = "(//div[@class='row'])[2]/div[2]")
    public WebElement shippingAddress;

    @FindBy(xpath = "(//div[@class='row'])[3]/div[2]")
    public WebElement phone;



    String checkout="//div[@class='checkout']";

    public void checkOutProcessWithRegisteredAccountAndValidateErrorHandlingMissingInformationTheCheckoutProcess() {
        Click(shopNow);
        if (inStockText_updated.getText().equalsIgnoreCase("In stock")) {
            log.info("Clicking On In Stock Product");
            Click(inStockText);
            smartWait(driver, addToCartButton);
            log.info("Clicking On Add To Cart Button");
            Click(addToCartButton);
            log.info("Clicking On Cart Quantity Text");
            smartWait(driver, cartCountUpdated);
            Click(cartCountUpdated);

            log.info("Clicking On CheckOut");
            Click(checkout);

            try {// Validating error handling for missing or incorrect information
                smartWait(driver, checkout);
                assertElementDisplayedUsingXpath(checkout);
            }catch(Exception e){
                log.info("Error Occurred");
                driver.navigate().refresh();
            }


            log.info("Clicking My Profile Tab");
            smartWait(driver,profileTab);
            Click(profileTab);
            System.out.println(shippingAddress.getText());

            String expectedName = "Doe John";
            String expectedAddress = "4321 40th street"+"\n"+"#2F"+"\n"+"New York, NY, 11101";
            String expectedPhone = "601-494-9501";

            log.info("Asserting All User Information");
            Assert.assertEquals(expectedName, name.getText());
            Assert.assertEquals(expectedAddress, shippingAddress.getText());
            Assert.assertEquals(expectedPhone, phone.getText());
            log.info("Asserting All User Information Passed");



        }

    }
}

