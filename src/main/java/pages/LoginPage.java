package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        //setDriver(driver);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
        super(driver);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    By btnYalla_locator = By.xpath("//button[@type='submit']");

    @FindBy(xpath = "//h2[text()='Logged in success']")
    WebElement popupSuccessfullLogin;

    @FindBy(xpath = "//button[text()='Ok']")
    WebElement btnOk;

    public void clickBtnOk(){
        clickWait(btnOk,2);
    }

    public void clickBtnYalla(){
        click(btnYalla_locator);
    }

    public boolean isBtnYallaEnabled(){
        return isElementEnabled(btnYalla);
    }

    public void typeLoginForm(User user){
        inputEmail.sendKeys(user.getUsername(), Keys.TAB);
        inputPassword.sendKeys(user.getPassword(), Keys.TAB);
    }

    public boolean isLoggedInDisplayed(){
        return isElementDisplayed(popupSuccessfullLogin);
    }

}
