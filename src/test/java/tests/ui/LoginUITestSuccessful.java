package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.LoginPage;
import sharedDataUI.SharedData;

public class LoginUITestSuccessful extends SharedData {

    @Test
    public void verifyLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login("standard_user", "secret_sauce");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"),"User is not redirected to inventory page after login!");

        String title = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(title, "Products", "Login successful but Products title missing!");

    }

}
