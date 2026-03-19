package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppManager {
    private WebDriver driver;
    public final static Logger logger = LoggerFactory.getLogger(AppManager.class);

    public WebDriver getDriver() {
        if (driver == null) {
            logger.info("Driver is not initialized, create driver...");
            initDriver();
        }
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup(){
        logger.info("Start testing: " + LocalDate.now() + " : " + LocalTime.now());
        initDriver();

    }

    private void initDriver() {
        if (driver == null){
            logger.info("Create driver...");
            WebDriverListener webDriverListener = new WDListener();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver();
            driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
            driver.manage().window().maximize();
        }
    }

    @AfterMethod(enabled = true,alwaysRun = true)
    public void tearDown(){
        logger.info("Stop testing: " + LocalDate.now() + " : " + LocalTime.now());
        if (driver != null)
            driver.quit();
    }

}


