package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import loggerUtility.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.LoginPage;
import propertyUtility.PropertyUtility;
import sharedDataUI.SharedDataUI;

import java.util.HashMap;

public class LoginWithInvalidCredentials extends SharedDataUI {
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
    public void verifyLoginWithInvalidCredentials() {
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Start Test: Login with invalid credentials");

        HashMap<String, String> testData = getLoginData();
        String username = testData.get("username");
        String password = testData.get("invalidPassword");

        LoginPage loginPage = openLoginPage();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Login Page opened");
        LoggerUtility.infoTest("Login Page opened");

        loginPage.loginWithCredentials(username, password);
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Credentials inserted. Trying to login with invalid credentials");
        LoggerUtility.infoTest("Credentials inserted. Trying to login with invalid credentials");

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = testData.get("expected_error");

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is incorrect!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "User cannot login with invalid credentials. Correct error message displayed");
        LoggerUtility.infoTest("User cannot login with invalid credentials. Correct error message displayed");

    }
}



