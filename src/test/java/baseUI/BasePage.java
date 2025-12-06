package baseUI;

import helpersUI.HelperMethod;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends HelperMethod {

    public BasePage(WebDriver driver) {
        super(driver);
    }
}