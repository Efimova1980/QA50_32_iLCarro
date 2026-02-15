package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class FindYourCarTests extends AppManager {
    HomePage homePage;

    @Test
    public void FindYourCarPositiveTest(){
        homePage= new HomePage(getDriver());
        homePage.setCity("Haifa");
        homePage.setDateRange("2/15/2026 - 2/26/2026");
        homePage.clickBtnYalla();
    }
}
