package hooks;

import extentUtility.ExtentUtility;
import loggerUtility.LoggerUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class Hooks {
    private String testName;

    @BeforeSuite
    public void beforeSuite() {
        ExtentUtility.initiateReport();
    }

    @BeforeMethod
    public void prepareTest(Method method) {
        testName = this.getClass().getSimpleName();
       // testName = method.getDeclaringClass().getSimpleName() + " - " + method.getName();
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
