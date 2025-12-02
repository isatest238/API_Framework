package tests;

import actions.UserActions;
import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import propertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UserE2ETest extends Hooks {
    public String userID;
    public Request_User requestUserBody;
    public String token;
    UserActions userActions = new UserActions();


    @Test
    public void verifyUserEndToEnd() {

        System.out.println("Step 1: GET LIST OF USERS WITH LIMIT");
        getListOfUsers();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Users list retrieved successfully");

        System.out.println("Step 2: CREATE NEW USER");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "New account created with success ");

        System.out.println("\n Step 3: GET SPECIFIC USER ");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user validated with success ");


        System.out.println("\n Step 4: UPDATE SPECIFIC USER ");
        updateSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user updated with success ");


//        System.out.println("\n Step 4: GENERATE NEW TOKEN ");
//        generateToken();
//        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Token generated with success ");
//
        System.out.println("\n Step 5: DELETE SPECIFIC USER ");
        deleteSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deleted");

        System.out.println("\n Step 6: VERIFY SPECIFIC USER AFTER DELETION");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deletion is confirmed with success ");
    }


    public void getListOfUsers() {
        propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        HashMap<String, String> data = propertyUtility.getAllData();

        int limit = Integer.parseInt(data.get("limit"));
        userActions.getListOfUsers(String.valueOf(limit));

    }

    public void createAccount() {
        //pregateste body ul
        propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());

        // chemam layer3 de account action care trigger pe cea de jos
        // face actiunea de post si returneaza rez in response account success - si extrage user ID
        ResponseAccountSuccess responseAccountBody = userActions.createNewAccount(requestUserBody);
        userID = responseAccountBody.getId().toString();
    }

    public void generateToken() {
        ResponseTokenSuccess responseTokenSuccess = userActions.generateToken(requestUserBody);
        System.out.println("Access Token:" + responseTokenSuccess.getAccess_token());
        token = responseTokenSuccess.getAccess_token();
    }

    public void getSpecificUser() {
        userActions.getSpecificAccount(userID, requestUserBody);
    }

    public void deleteSpecificUser() {
        userActions.deleteSpecificAccount(userID);
    }

    public void updateSpecificUser() {
        // 1. Create user
        propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());

        ResponseAccountSuccess createdUser = userActions.createNewAccount(requestUserBody);
        String userId = createdUser.getId();


        requestUserBody.setName(requestUserBody.getName() + System.currentTimeMillis() + "updatat");
        userActions.updateSpecificUser(userId, requestUserBody);

    }
}

