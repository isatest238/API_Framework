package Actions;

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

    public void addProduct(String token, Request_Product body) {
        Response response = productStoreServiceImp.addProduct(body, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseProductSuccess responseProductSuccess = response.body().as(ResponseProductSuccess.class);
        responseProductSuccess.validateNotNullFields();
    }
}
