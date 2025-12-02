package service.interfaceService;

import objectData.requestObject.RequestRefreshToken;
import objectData.requestObject.Request_User;
import io.restassured.response.Response;

public interface UserServiceInterface {

    // aceasta interfata reprezinta actiunile pe care vrem sa le facem cu un modul

    Response createUser(Request_User body);

    Response generateUserToken(Request_User body);

    Response getSpecificAccount(String userID);

    Response deleteSpecificAccount(String userID);

    Response getListOfUsers(String limit);

    Response updateSpecificUser(String actualID, Request_User body);

    Response retrieveUserProfile(String token);
    Response refreshToken(RequestRefreshToken requestRefreshToken);
}
