package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import loggerUtility.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.ProductsPage;
import sharedDataUI.SharedDataUI;


public class AddProductToCart extends SharedDataUI {

    @Test
    public void verifyAddProductToCart() {
         ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Start Test: Add product to cart");

        //1. Login
        loginStandardUser();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "User logged in successfully");
        LoggerUtility.infoTest("User logged in successfully");

        // 2. Products Page
        ProductsPage productsPage = new ProductsPage(driver);
        LoggerUtility.infoTest("Navigated to Products page");

        // 3. Verificam ca suntem pe Products page
        Assert.assertEquals(productsPage.getPageTitle(), "Products", "User is not on Products page!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "User is on Products page");
        LoggerUtility.infoTest("User is on Products page");

        // 4. Adaugam produsul în cos
        productsPage.clickAddToCartButton();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Product added to cart");
        LoggerUtility.infoTest("Product added to cart");

        // 5. Verificam ca badge-ul este vizibil si valoarea este 1
        Assert.assertEquals(productsPage.getCartBadgeText(), "1", "Crat badge is not correct!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Product added to cart successfully");
        System.out.println("Cart badge este" + productsPage.getCartBadgeText());
        LoggerUtility.infoTest("Product added to cart successfully");
    }
}
