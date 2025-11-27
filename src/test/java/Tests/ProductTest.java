package Tests;

import Actions.Account_Actions;
import Actions.ProductStoreActions;
import ObjectData.RequestObject.Request_Product;
import ObjectData.RequestObject.Request_User;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.Response_Token_Success;
import Property_Utility.Property_Utility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ProductTest extends Hooks {
    public String userID;
    public Request_User requestUserBody;
    public String token;
    public Account_Actions accountActions;
    public Request_Product requestProduct;
    public ProductStoreActions productStoreActions;

    @Test
    public void method() {
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
    }

    public void createAccount() {
        // instantam clasa de Actions
        accountActions = new Account_Actions();

        // 41 , 42 pregateste body ul
        Property_Utility propertyUtility = new Property_Utility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());

        // 45 - chemam layer3 de account action care trigger pe cea de jos
        // - face actiunea de post si returneaza rez in response account success - si extrage user ID
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestUserBody);
        userID = responseAccountBody.getId().toString();
    }

    public void generateToken() {
        Response_Token_Success responseTokenSuccess = accountActions.generateToken(requestUserBody);
        System.out.println("Access Token:" + responseTokenSuccess.getAccess_token());
        token = responseTokenSuccess.getAccess_token();
    }

    public void getSpecificUser() {
        accountActions.getSpecificAccount(userID, requestUserBody);
    }

    public void addProductToAccount() {
        Property_Utility propertyUtility = new Property_Utility("RequestData/BookProductData");
        HashMap<String, String> testData = propertyUtility.getAllData();
        testData.put("userID", userID);
        requestProduct = new Request_Product(testData);

        productStoreActions = new ProductStoreActions();
        productStoreActions.addProduct(token, requestProduct);
    }
}
