package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.HeaderMenuItem;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    @FindBy(css = ".error")
    List<WebElement> listErrors;
    //    @FindBy(xpath = "//div[@class='error']")
    //    List<WebElement> listErrors;

    private WebDriver driver; //!
    private WebDriverWait wait; //!
    protected Header header;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //!
        this.header = new Header(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10),this);//!
    }

    //--------------------------------getters , setters -----------------------
    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

//    public static void setDriver(WebDriver driver) {
//        //BasePage.driver = driver;
//    }

    public Header getHeader() {
        return header;
    }

    //----------------------------------base actions---------------------------------------------
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected static void pause(int time){
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------------------------------------------------------------------

    public boolean isTextInErrorPresent(String text){
        if (listErrors == null || listErrors.isEmpty())
            return false;
        for (WebElement element:listErrors) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }

    protected boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    protected boolean isTextInElementPresentWait(WebElement element, String text){
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    protected boolean isElementEnabled(WebElement element){
        return element.isEnabled();
    }

    public void clickWait(WebElement element, int time){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean isUrlContains(String partOfUrl, int time){
        try {
//            return new WebDriverWait(driver, Duration.ofSeconds(time))
//                    .until(ExpectedConditions.urlContains(partOfUrl));
            return wait.until(ExpectedConditions.urlContains(partOfUrl));
        }catch (TimeoutException e){
            System.err.println("Error in runtime urlContains" + e);
            return false;
        }
    }
}
