package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

//    public void pause(int time){
//        try {
//            Thread.sleep(time * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
