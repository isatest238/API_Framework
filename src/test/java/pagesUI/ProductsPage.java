package pagesUI;

import baseUI.BasePage;
import org.openqa.selenium.By;
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

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;


    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void clickAddToCartButton() {
        addToCartSauceLabsBackpackButton.click();
    }

     public String getCartBadgeText(){
        return cartBadge.getText();
    }
    public void clickRemoveFromCartButton() {
        removeSauceLabsBackpackButton.click();
    }

    public boolean isCartBadgePresent() {
        return driver.findElements(By.className("shopping_cart_badge")).size() > 0;
    }
}
