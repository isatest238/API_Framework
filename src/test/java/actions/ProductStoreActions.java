package actions;

import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import objectData.responseObject.ResponseProductSuccess;
import restClient.ResponseStatus;
import service.serviceImplementation.ProductStoreServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

public class ProductStoreActions {
    private final ProductStoreServiceImp productStoreServiceImp;


    public ProductStoreActions() {
        productStoreServiceImp = new ProductStoreServiceImp();
    }

    public ResponseProductSuccess addProduct(String token, Request_Product body) {
        Response response = productStoreServiceImp.addProduct(body, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseProductSuccess responseProductSuccess = response.body().as(ResponseProductSuccess.class);
        responseProductSuccess.validateNotNullFields();

        return responseProductSuccess;

    }

    public void updateSpecificProduct  (RequestUpdateProduct requestBody, String actualProductID) {
        Response response = productStoreServiceImp.updateSpecificProduct(requestBody,actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);


        ResponseProductSuccess responseProductSuccess = response.body().as(ResponseProductSuccess.class);
        responseProductSuccess.validateNotNullFields();
    }

    public void getSpecificProduct  (String actualProductID) {
        Response response = productStoreServiceImp.getSpecificProduct(actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_BAD_REQUEST);
    }

    public void deleteSpecificProduct(String specificID) {
        Response response = productStoreServiceImp.deleteSpecificProduct(specificID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

    }

}

