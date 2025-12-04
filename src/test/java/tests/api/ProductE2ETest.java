package tests.api;

import actions.ProductStoreActions;
import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import objectData.responseObject.ResponseProductSuccess;
import propertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ProductE2ETest extends Hooks {
    public String userID;
    public String actualProductId;
    public String expectedProductId;
    public Request_Product requestProduct;
    public RequestUpdateProduct requestUpdateProduct;
    public Integer limit;
    public Integer offset;
    public ProductStoreActions productStoreActions =  new ProductStoreActions();

    @Test
    public void verifyProductEndToEnd() {

        System.out.println("\n Step 1: GET PRODUCT LIST USING WITH LIMIT ");
        getProductList();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "LIST OF PRODUCTS DISPLAYED WITH SUCCESS");

        System.out.println("\n Step 2: CREATE NEW PRODUCT");
        createProduct();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT CREATED WITH SUCCESS");

        System.out.println("\n Step 3: GET SPECIFIC PRODUCT BY ID");
        getSpecificProductById();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT LISTED WITH SUCCESS");

        System.out.println("\n Step 4: GET RELATED PRODUCT");
        getRelatedProductById();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "RELATED PRODUCT LISTED WITH SUCCESS");

        System.out.println("\n Step 5: UPDATE SPECIFIC PRODUCT");
        updateSpecProduct();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT UPDATED WITH SUCCESS");

        System.out.println("\n Step 6:DELETE SPECIFIC PRODUCT");
        deleteProductFromAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT DELETED WITH SUCCESS");

        System.out.println("\n Step 7: VERIFY SPECIFIC PRODUCT AFTER DELETION");
        checkIfProductDeleted();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT DELETION IS CONFIRMED WITH SUCCESS");
    }

    public void getProductList(){
        propertyUtility = new PropertyUtility("RequestData/CreateProductData");
        HashMap<String, String> data = propertyUtility.getAllData();

        int limit = Integer.parseInt(data.get("limit"));
        int offset = Integer.parseInt(data.get("offset"));

        productStoreActions.getProductsWithLimit(limit, offset);
    }

    public void createProduct() {
        propertyUtility = new PropertyUtility("RequestData/CreateProductData");
        HashMap<String, String> testData = propertyUtility.getAllData();

        testData.put("userID", userID);

        String uniqueTitle = "New Product " + System.currentTimeMillis();
        testData.put("title", uniqueTitle);

        requestProduct = new Request_Product(testData);

        productStoreActions = new ProductStoreActions();
        ResponseProductSuccess responseProduct = productStoreActions.addProduct(requestProduct);

        // actual ID from API
        actualProductId = responseProduct.getId();
        System.out.println("Created product id: " + actualProductId);
    }

    public void getSpecificProductById(){
        productStoreActions.getSpecificProduct(actualProductId);
    }

    public void getRelatedProductById(){
        productStoreActions.getRelatedProduct(actualProductId);
    }

    public void updateSpecProduct() {
        propertyUtility = new PropertyUtility("RequestData/CreateProductData");
        HashMap<String, String> testData = propertyUtility.getAllData();

        // id-ul din body = ce ne asteptam sa fie in produs
        expectedProductId = actualProductId;
        testData.put("id", expectedProductId);

        //  INSTANTIEM BODY-UL PENTRU UPDATE
        requestUpdateProduct = new RequestUpdateProduct(testData);

        // facem update pe campuri (title, price)
        requestUpdateProduct.setTitle(requestUpdateProduct.getTitle() + System.currentTimeMillis() + "updatat");
        requestUpdateProduct.setPrice(requestUpdateProduct.getPrice() * 2);

        // apelam direct update
        productStoreActions.updateSpecificProduct(requestUpdateProduct, actualProductId);
    }

    public void deleteProductFromAccount() {
        productStoreActions.deleteSpecificProduct(actualProductId);
    }

    public void checkIfProductDeleted() {
        productStoreActions.verifyProductDeleted(actualProductId);
    }

}


