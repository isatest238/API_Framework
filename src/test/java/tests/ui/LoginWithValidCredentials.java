package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import loggerUtility.LoggerUtility;
import pagesUI.ProductsPage;
import propertyUtility.PropertyUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.LoginPage;
import sharedDataUI.SharedDataUI;

import java.util.HashMap;

public class LoginWithValidCredentials extends SharedDataUI {
    // Metoda helper: citește datele din LoginData.properties
    private HashMap<String, String> getLoginData() {
        PropertyUtility prop = new PropertyUtility("LoginData");
        return prop.getAllData();
    }

    // Metoda helper: creează pagina și o deschide
    private LoginPage openLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        return loginPage;
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Start test: Login with valid credentials");

        //1. Login
        loginStandardUser();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "User logged in successfully");
        LoggerUtility.infoTest("User logged in successfully");

        // Products page
        ProductsPage productsPage = new ProductsPage(driver);

        // Verificare URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "URL does not contain inventory.html!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "URL contains inventory.html");
        LoggerUtility.infoTest("URL contains inventory.html - expected");

        // Verify Title = Products
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Products page title is correct");
        LoggerUtility.infoTest("Products page title is correct");
    }

}



