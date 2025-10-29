package Tests.Before;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class Create_User_Test_before {

    @Test
    public void testMethod() {
        // definim configurilele pentru client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");

        //definim requestul
        JSONObject createUserBody = new JSONObject();
        createUserBody.put("email", "isabela.vulpe" + System.currentTimeMillis() + "@yahoo.com");
        createUserBody.put("name", "Isabela");
        createUserBody.put("password", "Pass4858");
        createUserBody.put("role", "customer");
        createUserBody.put("avatar", "https://api.escuelajs.co/docs#/users/UsersController_create");
        requestSpecification.body(createUserBody.toString());

        // interactionam cu responsul
        Response response = requestSpecification.post("api/v1/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.asString());
    }
}
