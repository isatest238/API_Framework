package tests;

import actions.UserActions;
import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import propertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

public class Create_User_Test extends Hooks {
    public String userID;
    public Request_User requestUserBody;
    public String token;
    public UserActions accountActions;

    @Test
    public void verifyUserEndToEnd() {
        System.out.println("Step 1: CREATE NEW USER");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "New account created with success ");

        System.out.println("\n Step 2: GENERATE NEW TOKEN ");
        generateToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Token generated with success ");

        System.out.println("\n Step 3: GET SPECIFIC USER ");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user validated with success ");

        System.out.println("\n Step 4: DELETE SPECIFIC USER ");
        deleteSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deleted");

        System.out.println("\n Step 5: VERIFY SPECIFIC USER AFTER DELETION");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deletion is confirmed with success ");
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

    public void deleteSpecificUser() {
        accountActions.deleteSpecificAccount(userID);
    }
}

