package sharedDataUI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

    public class HooksUI {

        protected WebDriver driver;

        @BeforeMethod
        public void setUpDriver() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @AfterMethod
        public void tearDownDriver() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

