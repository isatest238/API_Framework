package Tests;

import ObjectData.RequestObject.Request_User;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import Property_Utility.Property_Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User_Test {

    @Test
    public void testMethod() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");

        //definim requestul
        Property_Utility propertyUtility = new Property_Utility("RequestData/CreateUserData");
        Request_User requestUserBody = new Request_User(propertyUtility.getAllData());
        requestSpecification.body(requestUserBody);


        // interactionam cu response-ul
        Response response = requestSpecification.post("api/v1/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

       //validam response body-ul
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);

        System.out.println("UserID: " + responseAccountBody.getId());
        Assert.assertEquals(responseAccountBody.getName(), requestUserBody.getName());


    }
}
