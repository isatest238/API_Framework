package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.LoginPage;
import sharedDataUI.SharedDataUI;

public class LoginTestHappyFlow extends SharedDataUI {

    @Test
    public void verifyLoginWithValidCredentials() {

        System.out.println("Step 1: Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginWithValidCredentials("standard_user", "secret_sauce");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));

    }

}


