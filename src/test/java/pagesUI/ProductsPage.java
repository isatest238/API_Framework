package pagesUI;

import baseUI.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {

    // locators - WebElements
    @FindBy(xpath = "//span[@class='title']")
    public WebElement pageTitle;

    @FindBy(id= "add-to-cart-sauce-labs-backpack")
    public WebElement addToCartSauceLabsBackpackButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement removeSauceLabsBackpackButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

}
