package pages;

import enums.Months;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

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
    @FindBy(id="city")
    WebElement inputCity;
    @FindBy(id="dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath= "//button[@aria-label='Choose month and year']")
    WebElement btnChooseMonthYear;




    public void clickBtnYalla_WithWait(){
        clickWait(btnYalla,2);
    }
    public void clickBtnYalla(){
        btnYalla.click();
    }

    public void setDateRange(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String dateRange = startDate.format(formatter)
                + " - "
                + endDate.format(formatter);
        inputDates.sendKeys(dateRange);
    }

    public void clickBtnSignUp(){
        btnSignUp.click();
    }
    public void clickBtnLogin(){
        btnLogin.click();
    }

    public void typeFindYourCarForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        setDateRange(startDate, endDate);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
    }

    public void typeFindYourCarForm_WOJavascript(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        setDateRange(startDate, endDate);
    }

    //CALENDAR--------------------------------------------------

    private void typeMonth(Months month){
        WebElement btnMonth = driver.findElement(By.xpath(month.getLocator()));
        btnMonth.click();
    }

    private void typeCalendar(LocalDate date){
        btnChooseMonthYear.click();
        //td[@aria-label="2026"]
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By
                .xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();

        int month = date.getMonth().getValue();
        typeMonth(Months.values()[month-1]);

        //div[text()=' 1 ']
        String day = Integer.toString(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By
                .xpath("//div[text()=' " + day + " ']"));
        btnDay.click();
    }

    public void typeFindYourCarFormWithCalandar(String city, LocalDate startDate, LocalDate endDate){
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
    }
}
