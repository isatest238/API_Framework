package Service;

import RestClient.RestClient;
import Service.Implementation.UserServiceImp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import RestClient.RequestType;

public class CommonAPIService {
    // Layer 2 presupune definirea actiunilor care se vor face pe configurarile de client (layer 1)

    public Response post(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un POST cu un body
        requestSpecification.body(body);
        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endPoint);
        return  response;
    }

    public Response get (String userID, String endPoint){
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un get fara body - dar aici ne trebuie header
        requestSpecification.header("Authorization", "Bearer " + userID);
        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endPoint);
        return  response;
    }

    public Response delete(String userID, String endPoint){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + userID);
        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endPoint);
        return  response;
    }

    //metoda care instantiaza legatura cu layer1

    private Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint) {
        return  new RestClient().performRequest(requestType, requestSpecification, endPoint);
    }


}
