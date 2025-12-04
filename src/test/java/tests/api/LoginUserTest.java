package tests.api;

import actions.UserActions;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import objectData.requestObject.RequestRefreshToken;
import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.testng.Assert;
import org.testng.annotations.Test;
import propertyUtility.PropertyUtility;

public class LoginUserTest extends Hooks {

    public String userID;
    public Request_User requestUserBody;
    public RequestRefreshToken requestRefreshToken;
    UserActions userActions = new UserActions();
    public String accessToken;
    public String refreshToken;

    @Test
    public void verifyLoginUserEndToEnd() {

        System.out.println("Step 1: CREATE NEW USER");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP,
                "New account created with success ");

        System.out.println("\n Step 2: GENERATE NEW TOKEN ");
        generateTokenLoginUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP,
                "Token generated with success ");

        System.out.println("\n Step 3: RETRIEVE USER PROFILE ");
        retrieveUserProfile();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP,
                "User profile retrieved with success ");

        System.out.println("\n Step 4: REFRESH TOKEN ");
        refreshToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP,
                "TOKEN REFRESHED with success ");


        System.out.println("\n Step 5: DELETE SPECIFIC USER ");
        deleteSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deleted");

        System.out.println("\n Step 6: VERIFY SPECIFIC USER AFTER DELETION");
        getSpecificUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Specific user deletion is confirmed with success ");
    }

    public void createAccount() {
        propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());

        ResponseAccountSuccess responseAccountBody =
                userActions.createNewAccount(requestUserBody);
        userID = responseAccountBody.getId().toString();
    }

    public void generateTokenLoginUser() {
        ResponseTokenSuccess responseTokenSuccess =
                userActions.generateToken(requestUserBody);

        System.out.println("Access Token: " + responseTokenSuccess.getAccess_token());

        accessToken = responseTokenSuccess.getAccess_token();
        refreshToken = responseTokenSuccess.getRefresh_token();

    }

    public void retrieveUserProfile() {
        // folosim token-ul obținut la loginUser()
        ResponseAccountSuccess profile = userActions.getUserProfile(accessToken);

        // verificăm că profilul aparține user-ului creat la createAccount()
        Assert.assertEquals(profile.getEmail(), requestUserBody.getEmail());
        Assert.assertEquals(profile.getName(), requestUserBody.getName());
    }

    public void refreshToken() {
        // INITIALIZEZI requestRefreshToken
        requestRefreshToken = new RequestRefreshToken(refreshToken);

        ResponseTokenSuccess responseTokenSuccess =userActions.refreshToken(requestRefreshToken);

        System.out.println("New Access Token: " + responseTokenSuccess.getAccess_token());
    }
    public void deleteSpecificUser() {
        userActions.deleteSpecificAccount(userID);
    }

    public void getSpecificUser() {
        userActions.getSpecificAccount(userID, requestUserBody);
    }

}