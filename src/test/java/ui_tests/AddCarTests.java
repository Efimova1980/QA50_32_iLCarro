package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.CarFactory;
import utils.PropertiesReader;
import utils.enums.HeaderMenuItem;

import static pages.BasePage.clickButtonHeader;

public class AddCarTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void login() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "login"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        //logger.info("start test " + method.getName() + " with " + user);
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        letTheCarWorkPage = clickButtonHeader(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void AddNewCarPositiveTest(){
        Car car = CarFactory.positiveCar();
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage.isButtonSubmitEnabled());
    }

}
