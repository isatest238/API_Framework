package sharedDataUI;
import hooks.Hooks;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesUI.LoginPage;
import propertyUtility.PropertyUtility;

import java.util.HashMap;

@Getter
public class SharedDataUI extends Hooks {

    protected WebDriver driver;

    @BeforeMethod
    public void prepareBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    @AfterMethod
    public void clearBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

}
