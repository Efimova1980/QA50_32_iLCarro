package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopupPage;
import pages.RegistrationPage;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage(){
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    //----------------------------------POSITIVE TESTS-------------------------------------

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

    //--------------------------------NEGATIVE TESTS-------------------------------------

    @Test
    public void RegistrationNegativeTest_EmptyFields(){
        registrationPage.typeRegistrationFormEmpty();
        registrationPage.setCheckBoxAgree_WithActions(true);
        softAssert.assertTrue(registrationPage.isBtnYallaDisabled(), "validate button Yalla is disabled");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"), "validate field Name");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"), "validate field Last name");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"), "validate field Email");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"), "validate field Password");
        softAssert.assertAll();
    }

    @Test
    public void RegistrationNegativeTest_UserAlreadyExists(){
        User user = new User("ola_mail@gmail.com","Pass1234!", "Ola", "Ola");
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgree_WithActions(true);
        registrationPage.clickYalla();
        Assert.assertTrue(new PopupPage(getDriver()).isTextInPopupMessagePresent("User already exists"));
    }
}
