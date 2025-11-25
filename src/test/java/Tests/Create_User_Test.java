package Tests;

import Actions.Account_Actions;
import ObjectData.RequestObject.Request_User;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.Response_Token_Success;
import Property_Utility.Property_Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User_Test {
    public String userID;
    public Request_User requestUserBody;
    public String token;
    public Account_Actions accountActions;

    @Test
    public void testMethod() {
        System.out.println("Step 1: CREATE NEW USER");
        createAccount();
        System.out.println();

        System.out.println("Step 2: GENERATE NEW TOKEN ");
        generateToken();
        System.out.println();

        System.out.println("Step 3: GET SPECIFIC USER ");
        getSpecificUser();
        System.out.println();

        System.out.println("Step 4: DELETE SPECIFIC USER ");
        deleteSpecificUser();
        System.out.println();

        System.out.println("Step 5: VERIFY SPECIFIC USER AFTER DELETION");
        getSpecificUser();
        System.out.println();
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

    public void deleteSpecificUser() {
        accountActions.deleteSpecificAccount(userID);
    }


}

