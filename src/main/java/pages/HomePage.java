package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//a[@ng-reflect-router-link='login']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[@ng-reflect-router-link='logout']")
    WebElement btnLogout;
    @FindBy(xpath = "//a[@ng-reflect-router-link='registration']")
    WebElement btnSignUp;
    @FindBy(xpath = "//input[@id='city']")
    WebElement inputCity;
    @FindBy(xpath = "//input[@id='dates']")
    WebElement inputDates;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;


    public void setCity(String city){
        inputCity.sendKeys(city);
    }
    public void setDateRange(String dateRange){
        inputDates.sendKeys(dateRange);
    }

    public void clickBtnYalla(){
        btnYalla.click();
    }

    public void clickBtnSignUp(){
        btnSignUp.click();
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }

}
