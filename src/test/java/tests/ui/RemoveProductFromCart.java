package tests.ui;

import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import loggerUtility.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesUI.ProductsPage;
import sharedDataUI.SharedDataUI;


public class RemoveProductFromCart extends SharedDataUI {

    @Test
    public void verifyRemoveProductFromCart() {
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Start Test: Remove product from cart");

        //1. Login
        loginStandardUser();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "User logged in successfully");
        LoggerUtility.infoTest("User logged in successfully");

        // 2. Products Page
        ProductsPage productsPage = new ProductsPage(driver);

        // 3. Verificam ca suntem pe Products page
        Assert.assertEquals(productsPage.getPageTitle(), "Products", "User is not on Products page!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "User is on Products page");
        LoggerUtility.infoTest("User is on Products page");

        // 4. Adaugam produsul in cos ca sa avem ce sterge
        productsPage.clickAddToCartButton();
        ExtentUtility.attachReportLog(ReportStep.INFO_STEP, "Clicked Add to Cart");
        LoggerUtility.infoTest("Clicked Add to Cart");

        // 6. Soatem produsul din cos
        productsPage.clickRemoveFromCartButton();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Clicked Remove from Cart button");
        LoggerUtility.infoTest("Clicked Remove from Cart button");

        // 7. Verificăm că badge-ul este vizibil și valoarea este 1
        Assert.assertFalse(productsPage.isCartBadgePresent(),"Cart badge still exists after removing product!");
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Product removed, cart badge not present anymore");
        LoggerUtility.infoTest("Product removed, cart badge not present anymore");
    }
}
