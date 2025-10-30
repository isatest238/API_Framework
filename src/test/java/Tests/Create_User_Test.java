package Tests;

import ObjectData.RequestObject.Request_User;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.Response_Token_Success;
import Property_Utility.Property_Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User_Test {
    public Integer userID;
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
    }

    public void createAccount() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");

        //definim requestul
        Property_Utility propertyUtility = new Property_Utility("RequestData/CreateUserData");
        requestUserBody = new Request_User(propertyUtility.getAllData());
        requestSpecification.body(requestUserBody);

        // interactionam cu response-ul
        Response response = requestSpecification.post("api/v1/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

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
        System.out.println(response.getStatusLine());

        //validam response body-ul
        Response_Token_Success responseTokenSuccess = response.body().as(Response_Token_Success.class);
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
    }
}

