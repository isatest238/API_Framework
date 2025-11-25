package Service.Implementation;

import ObjectData.RequestObject.Request_User;
import Service.APIService.UserAPIService;
import Service.InterfaceService.UserServiceInterface;
import io.restassured.response.Response;

public class UserServiceImp implements UserServiceInterface {

    //facem o instanta de APIService ca sa putem accessa metodele generale: POST, GET, PUT, DELETE ...

    private UserAPIService userApiService;

    @Override
    public Response createUser(Request_User body) {
        userApiService = new UserAPIService();
        return userApiService.post(body, "api/v1/users");
    }

    @Override
    public Response generateUserToken(Request_User body) {
       userApiService = new UserAPIService();
       return userApiService.post(body, "api/v1/auth/login");
    }

    @Override
    public Response getSpecificAccount(String userID) {
        userApiService = new UserAPIService();
        String url = "api/v1/users/" + userID;
        return userApiService.get(userID, url);
    }

    @Override
    public Response deleteSpecificAccount(String userID) {
        userApiService = new UserAPIService();
        String url = "api/v1/users/" + userID;
        return userApiService.delete(userID, url);

    }
}
