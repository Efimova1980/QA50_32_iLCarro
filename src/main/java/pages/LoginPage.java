package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
    public LoginPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement fieldEmail;

    @FindBy(xpath = "//input[@id='password']")
    WebElement fieldPassword;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public LoggedInDialogPage clickBtnYalla(){
        btnYalla.click();
        return new LoggedInDialogPage();
    }

    public void typeEmail(String s){
        fieldEmail.sendKeys(s);
    }

    public void typePassword(String s){
        fieldPassword.sendKeys(s);
    }
}
