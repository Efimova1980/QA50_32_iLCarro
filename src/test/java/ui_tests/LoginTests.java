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
import pages.RegistrationPage;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username("harry@gmail.com")
                .password("Pass1234!")
                .build();

        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }


    @Test
    public void loginPositiveTest_WithPopupPage(){
        User user = User.builder()
                .username("harry@gmail.com")
                .password("Pass1234!")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertTrue(loginPage.isLoggedInDipllayed());
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol(){
        User user = User.builder()
                .username("harry@gmail.com")
                .password("Pass1234")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("Login or Password incorrect"));
    }

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
        //System.out.println("After first assert");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate field password");
        //System.out.println("After second Assert");
        softAssert.assertAll();
    }
}
