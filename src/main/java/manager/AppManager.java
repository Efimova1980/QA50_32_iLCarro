package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppManager {
    private WebDriver driver;
    public final static Logger logger = LoggerFactory.getLogger(AppManager.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        logger.info("Start testing: " + LocalDate.now() + " : " + LocalTime.now());
        WebDriverListener webDriverListener = new WDListener();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        options.addArguments("--headless=new");       //
        options.addArguments("--window-size=1920,1080"); //
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver = new EventFiringDecorator<>(webDriverListener)
                .decorate(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = true,alwaysRun = true)
    public void tearDown(){
        logger.info("Stop testing: " + LocalDate.now() + " : " + LocalTime.now());
        if (driver != null)
            driver.quit();
    }

}


