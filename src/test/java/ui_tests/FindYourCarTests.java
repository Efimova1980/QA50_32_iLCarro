package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;

public class FindYourCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        homePage= new HomePage(getDriver());
    }

    @Test
    public void findYourCarPositiveTest(){
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026,3,12);
        LocalDate endDate = LocalDate.of(2026,3,22);
        homePage.typeFindYourCarForm(city, startDate, endDate);
        homePage.clickBtnYalla_WithWait();
        Assert.assertTrue(homePage.urlContains("results",2));
    }

    //HOMEWORK10
    //позитивный тест с календарем
    @Test
    public void findYourCarPositiveTestWithCalandar(){
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026,3,12);
        LocalDate endDate = LocalDate.of(2026,3,22);
        homePage.typeFindYourCarFormWithCalandar(city, startDate, endDate);
        homePage.clickBtnYalla_WithWait();
        Assert.assertTrue(homePage.urlContains("results",2));
    }

    //демонстрация обработки исключения
    @Test(expectedExceptions =  org.openqa.selenium.TimeoutException.class)
    public void findYourCarNegativeTest_EmptyFieldCityWithException(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026,3,12);
        LocalDate endDate = LocalDate.of(2026,3,22);
        homePage.typeFindYourCarForm_WOJavascript(city, startDate, endDate);
        homePage.clickBtnYalla_WithWait();
    }

    @Test()
    public void findYourCarNegativeTest_EmptyFieldCity(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026,3,12);
        LocalDate endDate = LocalDate.of(2026,3,22);
        homePage.typeFindYourCarForm_WOJavascript(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("City is required"));
    }

    //HOMEWORK 10
    //NEGATIVE TESTS WITH WRONG DATES
    @Test()
    public void findYourCarNegativeTest_WrongDate_TheSameDays(){
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026,3,12);
        LocalDate endDate = LocalDate.of(2026,3,12);
        homePage.typeFindYourCarForm_WOJavascript(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test()
    public void findYourCarNegativeTest_WrongDate_FistIsBigger() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 3, 22);
        LocalDate endDate = LocalDate.of(2026, 3, 12);
        homePage.typeFindYourCarForm_WOJavascript(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date")
                && homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test()
    public void findYourCarNegativeTest_WrongDate_DateInThePast() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2024, 3, 12);
        LocalDate endDate = LocalDate.of(2024, 3, 22);
        homePage.typeFindYourCarForm_WOJavascript(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"));
    }


}
