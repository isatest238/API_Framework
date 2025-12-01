package service.serviceImplementation;

import objectData.requestObject.Request_User;
import service.apiService.UserAPIService;
import service.endpoints.ProductEndPoints;
import service.interfaceService.UserServiceInterface;
import service.endpoints.UserEndPoints;
import io.restassured.response.Response;

public class UserServiceImp implements UserServiceInterface {

    //facem o instanta de APIService ca sa putem accessa metodele generale: POST, GET, PUT, DELETE ...

    private UserAPIService userApiService;

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
        return userApiService.get(limit,url);

    }

    @Override
    public Response generateUserToken(Request_User body) {
        return userApiService.post(body, UserEndPoints.USER_TOKEN);
    }

    @Override
    public Response getSpecificAccount(String userID) {
        String url = UserEndPoints.USER_GET + userID;
        return userApiService.get(userID, url);
    }

    @Override
    public Response deleteSpecificAccount(String userID) {
        String url = UserEndPoints.USER_DELETE + userID;
        return userApiService.delete(userID, url);

    }
}
