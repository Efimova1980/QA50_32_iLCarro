package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopupPage;
import utils.PropertiesReader;

import java.lang.reflect.Method;

import static utils.UserFactory.emptyUser;
import static utils.UserFactory.positiveUser;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    //------------------LOGIN POSITIVE TESTS----------------------------
    @Test
    public void loginPositiveTest(Method method){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties","login"))
                .password(PropertiesReader.getProperty("base.properties","password"))
                .build();
        logger.info("start test " + method.getName() + " with " + user);
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }


    @Test
    public void loginPositiveTest_WithPopupPage(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties","login"))
                .password(PropertiesReader.getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Logged in success"));
    }

    //------------------LOGIN NEGATIVE TESTS----------------------------
    //in the class
    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties","login"))
                .password("Pass1234")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Login or Password incorrect"));
    }

    //in the class
    //softAssert example
    @Test
    public void loginNegativeTest_WrongEmail_EmptyPassword(){
        User user = User.builder()
                .username("harrygmail.com")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"), "validate field email");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate field password");
        softAssert.assertAll();
    }



    //------------------------HOMEWORK 7 LOGIN NEGATIVE TESTS-------------------------------------------
    @Test
    public void loginNegativeTest_UserIsNotRegistered(){
        loginPage.typeLoginForm(positiveUser());
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_PasswordRight_EmailValidButWrong(){
        User user = User.builder()
                .username("harry1@gmail.com")
                .username(PropertiesReader.getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmailRight_PasswordValidButWrong(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties","login"))
                .password("Pass1234!   ")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmptyFields(){
        loginPage.typeLoginForm(emptyUser());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required")
                , "validate field email - empty");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required")
                , "validate field password - empty");
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(), "validate button Yalla enabled");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_EmptyEmail_PassswordRight(){
        User user = User.builder()
                .username("")
                .username(PropertiesReader.getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required")
                , "validate field email - empty");
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(), "validate button Yalla enabled");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_EmptyPassword_EmailRight(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties","login"))
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required")
                , "validate field password - empty");
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(), "validate button Yalla enabled");
        softAssert.assertAll();
    }

}
