package tests;

import actions.UserActions;
import actions.ProductStoreActions;
import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseProductSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import propertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ProductTest extends Hooks {
    public String userID;
    public String actualProductId;
    public String expectedProductId;
    public Request_User requestUserBody;
    public String token;
    public UserActions accountActions;
    public Request_Product requestProduct;
    public ProductStoreActions productStoreActions;
    public RequestUpdateProduct requestUpdateProduct;

    @Test
    public void verifyProductEndToEnd() {
        System.out.println("Step 1: CREATE NEW USER");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "New account created with success ");

        System.out.println("\n Step 2: GENERATE NEW TOKEN ");
        generateToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Token generated with success ");

        System.out.println("\n Step 3: GET SPECIFIC USER ");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user validated with success ");

        System.out.println("\n Step 4: ADD PRODUCT TO ACCOUNT ");
        addProductToAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific product added with success ");

        System.out.println("\n Step 5: UPDATE SPECIFIC PRODUCT");
        updateSpecProduct();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT UPDATED WITH SUCCESS");

        System.out.println("\n Step 6:DELETE SPECIFIC PRODUCT");
        deleteProductFromAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "SPECIFIC PRODUCT DELETED WITH SUCCESS");

        System.out.println("\n Step 7: VERIFY SPECIFIC PRODUCT AFTER DELETION");
        getDeletedProduct();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific product deletion is confirmed with success ");
    }

    public void createAccount() {
        // instantam clasa de Actions
        accountActions = new UserActions();

        // 41 , 42 pregateste body ul
        propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());

        // 45 - chemam layer3 de account action care trigger pe cea de jos
        // - face actiunea de post si returneaza rez in response account success - si extrage user ID
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestUserBody);
        userID = responseAccountBody.getId().toString();
    }

    public void generateToken() {
        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestUserBody);
        System.out.println("Access Token:" + responseTokenSuccess.getAccess_token());
        token = responseTokenSuccess.getAccess_token();
    }

    public void getSpecificUser() {
        accountActions.getSpecificAccount(userID, requestUserBody);
    }

    public void addProductToAccount() {
        propertyUtility = new PropertyUtility("RequestData/BookProductData");
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

    public void updateSpecProduct() {
        propertyUtility = new PropertyUtility("RequestData/BookProductData");
        HashMap<String, String> testData = propertyUtility.getAllData();

        // 🔵 id-ul din body = ce ne asteptam sa fie in produs
        expectedProductId = actualProductId;
        testData.put("id", expectedProductId);

        // 🔵 INSTANTIEM BODY-UL PENTRU UPDATE
        requestUpdateProduct = new RequestUpdateProduct(testData);

        // facem update pe campuri (title, price)
        requestUpdateProduct.setTitle(requestUpdateProduct.getTitle() + System.currentTimeMillis() + "updatat");
        requestUpdateProduct.setPrice(requestUpdateProduct.getPrice() * 2);

        // 🔵 apelam direct update
        productStoreActions.updateSpecificProduct(requestUpdateProduct, actualProductId);
    }

    public void deleteProductFromAccount() {
        productStoreActions.deleteSpecificProduct(actualProductId);
    }

    public void getDeletedProduct() {
        productStoreActions.getSpecificProduct(actualProductId);
    }
}


