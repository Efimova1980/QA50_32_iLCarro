package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoggedInDialogPage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    @Test
    public void loginPositiveTest(){
        BasePage.setDriver(getDriver()); //driver is the one for all pages as it is static

        HomePage homePage= new HomePage();
        LoginPage loginPage = homePage.clickBtnLogin();
        pause(2);

        loginPage.typeEmail("harry@gmail.com");
        loginPage.typePassword("Pass1234!");
        pause(2);

        LoggedInDialogPage loggedInDialogPage = loginPage.clickBtnYalla();
        pause(2);

        loggedInDialogPage.clickBtnOk();
        pause(2);
    }
}
