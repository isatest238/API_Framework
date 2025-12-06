package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
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
        System.out.println("Step 1: Login with valid credentials");

        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Login with valid credentials");
        HashMap<String, String> testData = getLoginData();
        String username = testData.get("username");
        String password = testData.get("password");

        LoginPage loginPage = openLoginPage();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Opened Login Page");

        loginPage.loginWithCredentials(username, password);
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Entered credentials and login");

        // Verificare URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "URL contains inventory.html");

        // Verificare title = Products
        ProductsPage productsPage = new ProductsPage(driver);
        String actualPageTitle = productsPage.getPageTitle();
        String expectedPageTitle = testData.get("expectedPageTitle");
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Page title is Products");
    }

}



