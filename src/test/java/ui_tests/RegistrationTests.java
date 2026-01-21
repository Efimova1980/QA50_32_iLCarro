package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;

public class RegistrationTests extends AppManager {

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

        new HomePage(getDriver()).clickBtnSignUp();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgree(true);
        registrationPage.clickYalla();

        Assert.assertTrue(registrationPage.isRegistredDipllayed());
    }
}
