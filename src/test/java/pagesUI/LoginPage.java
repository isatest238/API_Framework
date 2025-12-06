package pagesUI;

import baseUI.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    // locators - WebElements
    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    // constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // actiuni pe care le facem pe LoginPage
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void typeUsername(String username) {
        type(usernameInput, username);
    }

    public void typePassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }


    public void loginWithCredentials(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
