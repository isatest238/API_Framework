package tests.before;

import objectData.requestObject.Request_User;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import propertyUtility.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User_Test_Before2 {
    public String userID;
    public Request_User requestUserBody;
    public String token;


    @Test
    public void testMethod() {
        System.out.println("Step 1: create new account");
        createAccount();
        System.out.println();

        System.out.println("Step 2: generate new token");
        generateToken();
        System.out.println();

        System.out.println("Step 3: get new user ");
        getSpecificUser();

    }

    public void createAccount() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");

        //definim requestul
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());
        requestSpecification.body(requestUserBody);

        // interactionam cu response-ul
        Response response = requestSpecification.post("api/v1/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 201);

        //validam response body-ul
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        userID = responseAccountBody.getId();
        System.out.println("UserID: " + responseAccountBody.getId());
        System.out.println("Email: " + responseAccountBody.getEmail());
        System.out.println("Password: " + responseAccountBody.getPassword());
        Assert.assertEquals(responseAccountBody.getName(), requestUserBody.getName());
    }

    public void generateToken() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");

        //definim requestul
        requestSpecification.body(requestUserBody);


        // interactionam cu response-ul
        Response response = requestSpecification.post("api/v1/auth/login");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getStatusLine());

        //validam response body-ul
        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        System.out.println("Access Token:" + responseTokenSuccess.getAccess_token());
        token = responseTokenSuccess.getAccess_token();
        System.out.println("Refresh Token:" + responseTokenSuccess.getRefresh_token());
        Assert.assertEquals(response.getStatusCode(), 201, "Login request failed — expected 201 Created");
        Assert.assertNotNull(responseTokenSuccess.getAccess_token(), "Access token is null!");
        Assert.assertFalse(responseTokenSuccess.getAccess_token().isEmpty(), "Access token is empty!");
        Assert.assertNotNull(responseTokenSuccess.getRefresh_token(), "Refresh token is null!");

    }

    public void getSpecificUser() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");
        requestSpecification.header("Autorization", "Bearer " + token); //pui autorizarea in header

        // interactionam cu response-ul
        Response response = requestSpecification.get("api/v1/users/" + userID);
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        //validam response body-ul
        ResponseAccountSuccess responseAccountGetSuccess = response.body().as(ResponseAccountSuccess.class);
        Assert.assertEquals(responseAccountGetSuccess.getName(), requestUserBody.getName());
    }
}

