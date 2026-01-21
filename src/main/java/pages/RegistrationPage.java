package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id="terms-of-use")
    WebElement checkBoxAgree;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(xpath = "//h2[text()='You are logged in success']")
    WebElement popupRegistred;

    public void clickYalla(){
        btnYalla.click();
    }

    public void setCheckBoxAgree(boolean value){
        if (checkBoxAgree.isSelected() != value)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBoxAgree);
    }

    public void typeRegistrationForm(User user){
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public boolean isRegistredDispllayed(){
        return isElementDisplayed(popupRegistred);
    }

}
