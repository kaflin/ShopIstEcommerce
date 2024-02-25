package testCases.eCommerce.userAccountTest;

import base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjectModel.eCommerce.userAccountPage.UpdateUserProfilePage;

public class UpdateUserProfileTest extends BaseClass {
    private UpdateUserProfilePage userProfilePage;

    @Epic("Couch Cache")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test will updating user profile information from the account dashboard")
    @Test(priority = 1)
    public void updateUserProfileInformationFromAccountDashboard() {
        userProfilePage=new UpdateUserProfilePage(driver);
        userProfilePage.updateUserProfileInformationFromAccountDashboard();
    }
}
