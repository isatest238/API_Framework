package Actions;

import ObjectData.RequestObject.Request_Product;
import RestClient.ResponseStatus;
import Service.Implementation.ProductStoreServiceImp;
import Service.Implementation.UserServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

public class ProductStoreActions {
    private ProductStoreServiceImp productStoreServiceImp;


    public ProductStoreActions() {
        productStoreServiceImp   = new ProductStoreServiceImp();
    }

    public void addProduct(String token, Request_Product body) {
        Response response = productStoreServiceImp.addProduct(body, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);
    }
}
