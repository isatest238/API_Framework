package service;

import restClient.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restClient.RequestType;

public class CommonAPIService {
    // Layer 2 presupune definirea actiunilor care se vor face pe configurarile de client (layer 1)
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";

    public Response post(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un POST cu un body
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_POST);

        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response put(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un PUT cu un body
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_PUT);

        Response response = performRequest(RequestType.REQUEST_PUT, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response post(Object body, String endPoint, String token) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un POST cu un body
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_POST);

        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response get(String userID, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        // pentru acest tip de metoda o sa facem un get fara body - dar aici ne trebuie header
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + userID);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_GET);

        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response delete(String userID, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + userID);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_DELETE);

        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    // DELETE CU BODY - daca o sa avem nevoie
    public Response delete(Object body, String userID, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + userID);
        requestSpecification.body(body);

        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_DELETE);

        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    // GET fara body

    //metoda care instantiaza legatura cu layer1

    private Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint) {
        return new RestClient().performRequest(requestType, requestSpecification, endPoint);
    }


}
