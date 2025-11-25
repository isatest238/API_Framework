package Actions;

import ObjectData.RequestObject.Request_User;
import ObjectData.ResponseObject.ResponseAccountGetFailed;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.Response_Token_Success;
import RestClient.ResponseStatus;
import Service.Implementation.UserServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

public class Account_Actions {

    private UserServiceImp userServiceImp;

    public Account_Actions() {
        userServiceImp = new UserServiceImp();
    }

    public ResponseAccountSuccess createNewAccount(Request_User request_User) {
        Response response = userServiceImp.createUser(request_User);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println("UserID: " + responseAccountBody.getId());
        System.out.println("Email: " + responseAccountBody.getEmail());
        System.out.println("Password: " + responseAccountBody.getPassword());
        Assert.assertEquals(responseAccountBody.getName(), request_User.getName());
        return responseAccountBody;
    }

    public Response_Token_Success generateToken(Request_User request_User) {
        Response response = userServiceImp.generateUserToken(request_User);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);
        Response_Token_Success responseTokenSuccess = response.body().as(Response_Token_Success.class);
        Assert.assertEquals(response.getStatusCode(), 201, "Login request failed — expected 201 Created");
        Assert.assertNotNull(responseTokenSuccess.getAccess_token(), "Access token is null!");
        Assert.assertFalse(responseTokenSuccess.getAccess_token().isEmpty(), "Access token is empty!");
        Assert.assertNotNull(responseTokenSuccess.getRefresh_token(), "Refresh token is null!");
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
        }
        else
        {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_BAD_REQUEST);
            ResponseAccountGetFailed responseAccountGetFailed = response.body().as(ResponseAccountGetFailed.class);
            Assert.assertEquals(responseAccountGetFailed.getName(), "EntityNotFoundError");
            Assert.assertTrue(
                    responseAccountGetFailed.getMessage().contains("Could not find any entity of type"),
                    "Message does not match expected error"
            );

        }

       }

    public void deleteSpecificAccount(String userID) {
        Response response = userServiceImp.deleteSpecificAccount(userID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

    }


}
