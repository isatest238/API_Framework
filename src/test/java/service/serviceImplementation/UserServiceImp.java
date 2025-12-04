package service.serviceImplementation;

import objectData.requestObject.RequestRefreshToken;
import objectData.requestObject.Request_User;
import service.apiService.UserAPIService;
import service.interfaceService.UserServiceInterface;
import service.endpoints.UserEndPoints;
import io.restassured.response.Response;

public class UserServiceImp implements UserServiceInterface {

    //facem o instanta de APIService ca sa putem accessa metodele generale: POST, GET, PUT, DELETE ...

    private final UserAPIService userApiService;

    public UserServiceImp() {
        userApiService = new UserAPIService();

    }

    @Override
    public Response createUser(Request_User body) {
        return userApiService.post(body, UserEndPoints.USER_CREATE);
    }

    @Override
    public Response getListOfUsers(String limit){
       String url = UserEndPoints.USER_GET + "?limit=" + limit;
       return userApiService.getWithoutToken(url);

    }

    @Override
    public Response updateSpecificUser(String actualID, Request_User body) {
        String url = UserEndPoints.USER_UPDATE + actualID;
        return userApiService.put(body, url);
    }

    @Override
    public Response generateUserToken(Request_User body) {
        return userApiService.post(body, UserEndPoints.USER_TOKEN);
    }

    @Override
    public Response getSpecificAccount(String userID) {
        String url = UserEndPoints.USER_GET + userID;
        return userApiService.getWithoutToken(url);
    }

    @Override
    public Response deleteSpecificAccount(String userID) {
        String url = UserEndPoints.USER_DELETE + userID;
        return userApiService.deleteWithoutToken(url);

    }

    @Override
    public Response retrieveUserProfile (String token){
        String url = UserEndPoints.USER_PROFILE;
        return userApiService.get(token, url);
    }

    @Override
    public Response refreshToken (RequestRefreshToken requestRefreshToken){
        String url = UserEndPoints.USER_REFRESH_TOKEN;
        return userApiService.post(requestRefreshToken, url);
    }

 }
