package pageObjectModel.login;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginToShopIstApplication extends BaseClass {
    @FindBy(xpath = "//input[@id='Username']")
    WebElement email;

    @FindBy(xpath = "//input[@id='Password']")
    WebElement password;

    @FindBy(css = "button[value='login']")
    WebElement submit;

    @FindBy(xpath = "//h2[@id='toggle-login-form']")
    WebElement clickToLoginPage;

    public LoginToShopIstApplication(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void login(String email_cred,String password_cred) throws InterruptedException {
        clickToLoginPage.click();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        email.sendKeys(email_cred);
        password.sendKeys(password_cred);
        submit.click();
        Thread.sleep(2000);
    }
}
