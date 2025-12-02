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

    // POST fara token
    public Response post(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_POST);

        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    // POST cu token (/auth/profile )
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

    // PUT fara token
    public Response put(Object body, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_PUT);

        Response response = performRequest(RequestType.REQUEST_PUT, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }


    // GET cu token (ex: /api/v1/auth/profile)
    public Response get(String token, String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_GET);

        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    // GET fara token (pentru /api/v1/users, /api/v1/users/{id})
    public Response getWithoutToken(String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + endPoint);
        ServiceHelper.requestLogs(requestSpecification, endPoint, RequestType.REQUEST_GET);

        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endPoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response deleteWithoutToken(String endPoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        //requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE);
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
