package Actions;

import ObjectData.RequestObject.RequestUpdateProduct;
import ObjectData.RequestObject.Request_Product;
import ObjectData.ResponseObject.ResponseProductSuccess;
import RestClient.ResponseStatus;
import Service.ServiceImplementation.ProductStoreServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

public class ProductStoreActions {
    private ProductStoreServiceImp productStoreServiceImp;


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

