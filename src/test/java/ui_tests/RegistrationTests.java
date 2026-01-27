package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopupPage;
import pages.RegistrationPage;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationPage(){
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void RegistrationPositiveTest(){
        Random rand = new Random();
        String email = "henry" + rand.nextInt(1000) + "@gmail.com";

        User user = User.builder()
                .firstName("Henry")
                .lastName("Gross")
                .username(email)
                .password("Pass1234!")
                .build();

        registrationPage.typeRegistrationForm(user);
        //registrationPage.setCheckBoxAgree_WithJavascript(true);
        registrationPage.setCheckBoxAgree_WithActions(true);
        registrationPage.clickYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("You are logged in success"));
        //Assert.assertTrue(registrationPage.isRegisteredDisplayed());
    }

    @Test
    public void RegistrationPositiveTest_WithFaker(){
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgree_WithActions(true);
        registrationPage.clickYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("You are logged in success"));
    }
}
