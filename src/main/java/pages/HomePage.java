package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage() {
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//a[@ng-reflect-router-link='login']")
    WebElement btnLogin;

    @FindBy(xpath = "//a[@ng-reflect-router-link='logout']")
    WebElement btnLogout;

    public LoginPage clickBtnLogin(){
        btnLogin.click();
        return new LoginPage();
    }

    public HomePage clickBthLogout(){
        btnLogout.click();
        return new HomePage();
    }

}
