package commonTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

public class BaseTest{
    protected static WebDriver driver ;
    protected static Properties pros;
    InputStream inputStream;

    TestUtils utils = new TestUtils();

    public BaseTest(){
        PageFactory.initElements( driver, this);
    }

    @BeforeTest
    @Parameters({"platformName", "browserType"})
    public void setup(String platformName, String browserType) throws IOException {
        pros = new Properties();
        String proFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
        pros.load(inputStream);
        switch (browserType)
        {
            case "chrome":
            {
                String driverPath = TestUtils.driverPath;

                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                break;
            }
            case "safari":
            {
                driver = new SafariDriver();
                break;
            }
            default: break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().window().maximize();
    }

    public void openURL()
    {
        driver.get(pros.getProperty("URL"));
    }

    public void waitForVisibilityWithExplicitWait(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibilityWithFluentWait(WebElement e){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public WebElement findElementByXpath(WebElement parent, String xpath)
    {
        try
        {
            if(parent == null) return driver.findElement(By.xpath(xpath));
            else return parent.findElement(By.xpath(xpath));
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public WebElement findElementByCssSelector(WebElement parent, String cssSelector)
    {
        try
        {
            return parent.findElement(By.cssSelector(cssSelector));
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public String getText(WebElement e)
    {
        return  e == null ? "" : e.getText();
    }
    public void clear(WebElement e) {
        waitForVisibilityWithFluentWait(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibilityWithFluentWait(e);
        e.click();
    }

    public void click(WebElement e, String msg) {
        waitForVisibilityWithFluentWait(e);
        e.click();
    }

    protected void clickWithHelpOfJSExecution(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    protected void sendKeyWithHelpOfJSExecution(WebElement element, String text)
    {
        String js = "arguments[0].setAttribute('value','"+text+"')";
        ((JavascriptExecutor) driver).executeScript(js, element);
    }
    protected void sendKeyAndEnterWithHelpOfJSExecution(WebElement element, String text)
    {
//        jse.executeScript("document.getElementById('elementID').setAttribute('value', 'new value for element')");

        String js = "document.getElementById('LocationSearch_input').setAttribute('value','"+text+"')";
        ((JavascriptExecutor) driver).executeScript(js, element);
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibilityWithExplicitWait(e);
        e.sendKeys(txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibilityWithFluentWait(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibilityWithFluentWait(e);
        return e.getAttribute(attribute);
    }

    public String getText(WebElement e, String msg) {
        String txt = null;
        return txt;
    }

//    @AfterMethod
//    public void BeforeMethod()
//    {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @AfterTest (alwaysRun = true)
    @Parameters({"platformName"})
    public void afterTest(String platformName) throws IOException {
        driver.quit();
        switch(platformName)
        {
            case "iOS":{
                Runtime.getRuntime().exec("pkill chrome");
                Runtime.getRuntime().exec("pkill safari");
                break;}
            case "window":{
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM safaridriver.exe /T");
////                other option
//                Process[] chromeDriverProcesses = Process.GetProcessesByName("chromedriver");
//                foreach(var chromeDriverProcess in chromeDriverProcesses){
//                    chromeDriverProcess.Kill();
//                }
                break;}

        }

    }
}
