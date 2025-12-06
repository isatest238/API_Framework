package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.LoginPage;
import pagesUI.ProductsPage;
import propertyUtility.PropertyUtility;
import sharedDataUI.SharedDataUI;

import java.util.HashMap;

public class LoginWithLockedUserName extends SharedDataUI {
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
    public void verifyLoginWithLockedUsername() {
        System.out.println("Step 3: Login with locked username");

        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Login with locked username");
        HashMap<String, String> testData = getLoginData();
        String username = testData.get("lockedUsername");
        String password = testData.get("password");

        LoginPage loginPage = openLoginPage();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Opened Login Page");

        loginPage.loginWithCredentials(username, password);
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Insert credentials and try to login");

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = testData.get("expected_error_locked");

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is incorrect!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "User not able to logged in with locked username. Correct Error Message displayed");
    }
}



