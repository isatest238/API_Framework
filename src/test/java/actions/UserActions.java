package actions;

import objectData.requestObject.RequestRefreshToken;
import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountGetFailed;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import restClient.ResponseStatus;
import service.serviceImplementation.UserServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

public class UserActions {

    private final UserServiceImp userServiceImp;

    public UserActions() {
        userServiceImp = new UserServiceImp();
    }

    public ResponseAccountSuccess createNewAccount(Request_User request_User) {
        Response response = userServiceImp.createUser(request_User);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println("UserID: " + responseAccountBody.getId());
        System.out.println("Email: " + responseAccountBody.getEmail());
        System.out.println("Password: " + responseAccountBody.getPassword());

        responseAccountBody.validateNotNullFields();
        Assert.assertEquals(responseAccountBody.getName(), request_User.getName());
        return responseAccountBody;
    }

    public ResponseTokenSuccess generateToken(Request_User request_User) {
        Response response = userServiceImp.generateUserToken(request_User);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        responseTokenSuccess.validateNotNullFields();

        Assert.assertNotNull(responseTokenSuccess.getAccess_token(), "Access token is null!");
        Assert.assertFalse(responseTokenSuccess.getAccess_token().isEmpty(), "Access token is empty!");
        Assert.assertNotNull(responseTokenSuccess.getRefresh_token(), "Refresh token is null!");

        return responseTokenSuccess;
    }

    public ResponseTokenSuccess refreshToken(RequestRefreshToken requestRefreshToken) {
        Response response = userServiceImp.refreshToken(requestRefreshToken);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

         ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        // access_token trebuie să existe și să NU fie gol
        Assert.assertNotNull(responseTokenSuccess.getAccess_token(), "Access token is null!");
        Assert.assertFalse(responseTokenSuccess.getAccess_token().isEmpty(), "Access token is empty!");

        // refresh_token trebuie să existe și să NU fie gol
        Assert.assertNotNull(responseTokenSuccess.getRefresh_token(), "Refresh token is null!");
        Assert.assertFalse(responseTokenSuccess.getRefresh_token().isEmpty(), "Refresh token is empty!");

        return responseTokenSuccess;

    }

    public void getSpecificAccount(String userID, Request_User request_user) {
        Response response = userServiceImp.getSpecificAccount(userID);
        if (response.getStatusCode() == ResponseStatus.SC_OK) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);
            Assert.assertEquals(response.getStatusCode(), 200);
            //validam response body-ul
            ResponseAccountSuccess responseAccountGetSuccess = response.body().as(ResponseAccountSuccess.class);
            Assert.assertEquals(responseAccountGetSuccess.getName(), request_user.getName());
        } else {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_BAD_REQUEST);
            ResponseAccountGetFailed responseAccountGetFailed = response.body().as(ResponseAccountGetFailed.class);
            Assert.assertEquals(responseAccountGetFailed.getName(), "EntityNotFoundError");
            Assert.assertTrue(
                    responseAccountGetFailed.getMessage().contains("Could not find any entity of type"),
                    "Message does not match expected error"
            );

        }

    }

    public void getListOfUsers(String limit) {
        Response response = userServiceImp.getListOfUsers(limit);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);
    }


    public void deleteSpecificAccount(String userID) {
        Response response = userServiceImp.deleteSpecificAccount(userID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

    }

    public void updateSpecificUser(String userId, Request_User body) {
        Response response = userServiceImp.updateSpecificUser(userId, body);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        Assert.assertEquals(responseAccountBody.getId(), userId);
        responseAccountBody.validateNotNullFields();
    }

    public ResponseAccountSuccess getUserProfile(String token) {
        Response response = userServiceImp.retrieveUserProfile(token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        responseAccountBody.validateNotNullFields();

        return responseAccountBody;

    }


}


