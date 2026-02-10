package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.HeaderMenuItem;

import java.time.Duration;
import java.util.List;

public class BasePage {
    static WebDriver driver;
    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextInErrorPresent(String text){
        if (listErrors == null || listErrors.isEmpty())
            return false;
        for (WebElement element:listErrors)
            if (element.getText().contains(text))
                return true;
        return false;
    }

    public void pause(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public boolean isTextInElementPresentWait(WebElement element, String text){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public boolean isElementEnabled(WebElement element){
        return element.isEnabled();
    }

    public <T extends BasePage> T clickButtonHeader(HeaderMenuItem item){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(item.getLocator())));
        button.click();
        switch (item){
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case SEARCH, LOG_OUT, DELETE_ACCAUNT -> {
                return (T) new HomePage(driver);
            }
            case SIGN_UP -> {
                return (T) new RegistrationPage(driver);
            }
            case TERMS_OF_USE -> {
                return (T) new TermsOfUsePage(driver);
            }
            case LET_THE_CAR_WORK -> {
                return (T) new LetTheCarWorkPage(driver);
            }
            default -> throw new IllegalArgumentException("Invalid parameter");
        }
    }
}
