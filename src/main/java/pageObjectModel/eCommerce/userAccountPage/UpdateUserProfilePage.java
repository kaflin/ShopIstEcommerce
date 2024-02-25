package pageObjectModel.eCommerce.userAccountPage;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateUserProfilePage extends BaseClass {
    public UpdateUserProfilePage(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    String editProfileButton="//section/a[@class='button']";

    String uploadPhotoButton="//label[@class='button inline']";

    @FindBy(xpath = "//input[@id='picture']")
    public WebElement uploadPicture;
    @FindBy(xpath = "//input[@id='firstname']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement lastName;

    @FindBy(xpath="//input[@id='address1']")
    public WebElement address1;

    @FindBy(xpath="//input[@id='address2']")
    public WebElement address2;

    @FindBy(xpath="//input[@id='addressCity']")
    public WebElement addressCity;

    String dropDownIcon="//div[@class='vs__actions']";

    @FindBy(xpath="//input[@id='addressZipcode']")
    public WebElement addressZipCode;

    @FindBy(xpath="//input[@id='phone']")
    public WebElement phone;

    String saveProfileButton="//button[text()='Save profile']";

    public void updateUserProfileInformationFromAccountDashboard() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        log.info("Clicking On User Profile Tab");
        smartWait(driver,profileTab);
        Click(profileTab);

        log.info("Clicking On Edit Profile Button");
        smartWait(driver,editProfileButton);
        Click(editProfileButton);

        log.info("Clicking On Upload Photo Button");
        smartWait(driver,uploadPhotoButton);
        uploadPicture.sendKeys(baseDirectory+"/src/main/resources/files/Picture1.png");

        log.info("Filling Updated First Name");
        firstName.clear();
        firstName.sendKeys(faker.name().firstName());

        log.info("Filling Updated last Name");
        lastName.clear();
        lastName.sendKeys(faker.name().lastName());

        log.info("Filling Updated Address 1");
        address1.clear();
        address1.sendKeys(faker.address().streetAddress());


        log.info("Filling Updated Address 2");
        address2.clear();
        address2.sendKeys(faker.address().streetAddressNumber());


        log.info("Filling Updated City");
        addressCity.clear();
        addressCity.sendKeys(faker.address().cityName());

        log.info("Filling Updated ZipCode");
        addressZipCode.clear();
        addressZipCode.sendKeys(faker.address().zipCode());

        log.info("Filling Updated PhoneNumber");
        phone.clear();
        phone.sendKeys(faker.phoneNumber().cellPhone());


        log.info("Clicking On Save Profile Button");
        smartWait(driver,saveProfileButton);
        Click(saveProfileButton);


        WebElement element=driver.findElement(By.xpath("//div[@class='success banner']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        assertElementDisplayedUsingXpath("//div[@class='success banner']");


    }
}
