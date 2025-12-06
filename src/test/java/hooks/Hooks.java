package hooks;

import extentUtility.ExtentUtility;
import loggerUtility.LoggerUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import propertyUtility.PropertyUtility;

import java.lang.reflect.Method;

public class Hooks {
    private String testName;
    public PropertyUtility propertyUtility;

    @BeforeSuite
    public void beforeSuite() {
        ExtentUtility.initiateReport();
    }

    @BeforeMethod
    public void prepareTest(Method method) {
        testName = this.getClass().getSimpleName();
        LoggerUtility.startTestCase(testName);
        ExtentUtility.startTest(testName);
    }

    @AfterMethod
    public void clearTest() {
        LoggerUtility.endTestCase(testName);
        ExtentUtility.endTest(testName);
    }

    @AfterSuite
    public void clearSuite() {
        ExtentUtility.generateReport();
    }
}
