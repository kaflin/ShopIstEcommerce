package base;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.allure.AllureResultsDeleter;
import utilities.log.LogDeleter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    public static Logger log = Logger.getLogger(BaseClass.class.getName());
    public static final int MAX_DOM_ELEMENT_FLUENT_WAIT_SEC = 40;
    public static final int MAX_DOM_ELEMENT_POLLING_WAIT_SEC = 10;
    public static final int MAX_DOM_ELEMENT_WAIT_SEC = 10;

    public Faker faker = new Faker();
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    public String baseDirectory = System.getProperty("user.dir");
    public String sideBarModule = "//div[contains(text(),'%s')]";
    public String shopNow = "//div[@class='jumbotron-box']/div[3]";

    public String addToCartButton = "//div[@class='purchase-button']";

    public String inStockText = "(//div[@class='status'])[1]";

    @FindBy(xpath = "(//div[@class='status'])[1]")
    public WebElement inStockText_updated;

    @FindBy(xpath = "//a[@class='cart']/div/div")
    public WebElement cartCount;

    public String cartCountUpdated="//a[@class='cart']/div/div";

    public String removeProductButton="//div[@class='remove-button']";

    @FindBy(xpath = "((//div[@class='line'])/div[2])[1]")
    public WebElement orderValue;

    @FindBy(xpath = "((//div[@class='line'])/div[2])[2]")
    public WebElement taxValue;

    @FindBy(xpath = "((//div[@class='line'])/div[2])[3]")
    public WebElement shippingValue;

    @FindBy(xpath = "(//div[@class='line line-total'])/div[2]")
    public WebElement totalPriceValue;

    public String addOperator="(//div[@class='operator']/div)[2]";
    public String subOperator="(//div[@class='operator']/div)[1]";
    public String profileTab="(//a[contains(@class,'profile')])";
    int maxRetries = 20;
    int retryCount = 0;
    boolean isSuccessful = false;

    private void setDriver(String browserType, String baseURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(baseURL);
                break;
            case "ie":
                driver = initIEDriver(baseURL);
                break;
            case "edge":
                driver = initEdgeDriver(baseURL);
                break;
            default:
                driver = initFirefoxDriver(baseURL);
        }
    }

    private WebDriver initChromeDriver(String baseURL) {
        log.info("Launching Google chrome browser..");
        log.info("Opening Couch Cache Project");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(1814, 974));
        while (retryCount < maxRetries) {
            try {
                driver.navigate().to(baseURL);
                driver.findElement(By.xpath("//div[@class='brand-large']"));
                System.out.println("tried");
                isSuccessful = true;
                break;
            } catch (Exception e) {
                // Log the exception or take other actions
                e.printStackTrace();
                // Increment the retry count
                System.out.println(retryCount++);
                retryCount++;
            }
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initEdgeDriver(String baseURL) {
        log.info("Launching Edge browser..");
        log.info("Opening Couch Cache Project");
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-popup-blocking");//Disable Initially,When Popup is Opened when We Log in
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(1814, 974));
        while (retryCount < maxRetries) {
            try {
                driver.navigate().to(baseURL);
                driver.findElement(By.xpath("//div[@class='brand-large']"));
                isSuccessful = true;
                break;
            } catch (Exception e) {
                // Log the exception or take other actions
                e.printStackTrace();
                // Increment the retry count
                retryCount++;
            }
        }        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initFirefoxDriver(String baseURL) {
        log.info("Launching Firefox browser..");
        log.info("Opening Couch Cache Project");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        while (retryCount < maxRetries) {
            try {
                driver.navigate().to(baseURL);
                driver.findElement(By.xpath("//div[@class='brand-large']"));
                isSuccessful = true;
                break;
            } catch (Exception e) {
                // Log the exception or take other actions
                e.printStackTrace();
                // Increment the retry count
                retryCount++;
            }
        }        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initIEDriver(String baseURL) {
        log.info("Launching Internet Explorer browser..");
        log.info("Opening Couch Cache Project");
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        while (retryCount < maxRetries) {
            try {
                driver.navigate().to(baseURL);
                driver.findElement(By.xpath("//div[@class='brand-large']"));
                isSuccessful = true;
                break;
            } catch (Exception e) {
                // Log the exception or take other actions
                e.printStackTrace();
                // Increment the retry count
                retryCount++;
            }
        }        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    @BeforeSuite
    public void deletePreviousLogAndAllureResult() {
        LogDeleter.deleteDirectory();
        AllureResultsDeleter.deleteAllureResultsDirectory();
    }


    @Parameters({"browser", "baseURL"})
    @BeforeClass(alwaysRun = true)
    public void initializeBaseTest(@Optional("edge") String browser, String baseURL) {
        try {
            String log4jConfPath = baseDirectory + "/src/main/resources/config/log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            log.info("Initiate browser..");
            setDriver(browser, baseURL);
        } catch (Exception e) {
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
        log.info("Clean up activity: Closed all browser instances..");
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    public void moveToPreviousWindow() throws InterruptedException {
        // Get the handle of the current window
        String currentWindowHandle = driver.getWindowHandle();

        // Get the handles of all open windows
//        Set<String> windowHandles = driver.getWindowHandles();

        // Remove the handle of the current window from the set of handles
//        windowHandles.remove(currentWindowHandle);

        // Switch to the new window
//        driver.switchTo().window(windowHandles.iterator().next());
        Thread.sleep(3000);//You can add explicit wait too


        //Switch to previous window
        driver.switchTo().window(currentWindowHandle);
    }

    public void Click(String xpath) {
        WebElement element = null;
        try {
            element = (new WebDriverWait(driver, Duration.ofSeconds(5)))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            element.click();
        } catch (Exception e) {
            element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static int getMonthEnum(String month) {
        String[] monthEnum = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return ArrayUtils.indexOf(monthEnum, month) + 1;
    }
    public void assertElementDisplayedUsingXpath(String selector) {
        boolean isDisplayed = false;
        try {
            smartWait(driver,selector);
            isDisplayed = findElementByXPATH(selector).isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertTrue(isDisplayed);
    }

    public WebElement smartWait(WebDriver driver, String... ids) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_DOM_ELEMENT_FLUENT_WAIT_SEC))
                .pollingEvery(Duration.ofSeconds(MAX_DOM_ELEMENT_POLLING_WAIT_SEC))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        WebElement element = wait.until(driver1 -> driver1.findElement(By.xpath(createXPath(ids))));
        return element;

    }

    public WebElement findElementByXPATH(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public String createXPath(String... ids) {
        if (ids[0].contains("//")) {
            String xPath = ids[0];
            return xPath;
        }
        StringBuilder xPath = new StringBuilder(String.format("//%s[(", ids[0]));

        if (ids.length == 3) {
            xPath.append(String.format("%s = '%s'", toLowerCaseAttribute(ids[1]), ids[2].toLowerCase()));
        } else if (ids.length > 3) {
            xPath.append(String.format("contains(%s, '%s')", toLowerCaseAttribute(ids[1]), ids[2].toLowerCase()));

            for (int i = 3; i < ids.length; i++) {
                xPath.append(String.format(" and contains(%s, '%s')", toLowerCaseAttribute(ids[1]), ids[i].toLowerCase()));
            }
        }

        xPath.append(") and not(ancestor::div[contains(@style,'display: none')]");
        xPath.append(")]");
        return xPath.toString();
    }

    private static String toLowerCaseAttribute(String attributeName) {
        @SuppressWarnings("SpellCheckingInspection") final String LAT_CHARS_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LAT_CHARS_LOWER = LAT_CHARS_UPPER.toLowerCase();

        return String.format("translate(%s, '%s', '%s')",
                attributeName.contains("text") ? attributeName + "()" : "@" + attributeName,
                LAT_CHARS_UPPER,
                LAT_CHARS_LOWER);
    }
}

