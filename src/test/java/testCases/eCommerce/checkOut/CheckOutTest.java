package testCases.eCommerce.checkOut;

import base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjectModel.eCommerce.checkoutPage.CheckOutPage;

public class CheckOutTest extends BaseClass {
    private CheckOutPage checkOutPage;
//This test case include
/*Test the checkout process with a guest account from start to finish,
including filling in personal information, shipping address, selecting
a shipping method, payment information (use a test payment
method provided by the platform), and confirming the order.*/

    /*Test the checkout process with a registered account and validate
that the user's saved information is populated correctly*/

    /*Validate error handling for missing or incorrect information in the
checkout process.*/

    @Epic("Couch Cache")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test will checkout process with a registered account and validate that the user's saved information is populated correctly.")
    @Test(priority = 1)
    public void checkOutProcessWithRegisteredAccountAndValidateErrorHandlingMissingInformationTheCheckoutProcess() {
        checkOutPage=new CheckOutPage(driver);
        checkOutPage.checkOutProcessWithRegisteredAccountAndValidateErrorHandlingMissingInformationTheCheckoutProcess();
    }
}
