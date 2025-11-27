package Service.Implementation;

import ObjectData.RequestObject.Request_Product;
import Service.APIService.ProductStoreAPIService;
import Service.APIService.UserAPIService;
import Service.InterfaceService.ProductStoreServiceInterface;
import io.restassured.response.Response;

public class ProductStoreServiceImp implements ProductStoreServiceInterface {

    private ProductStoreAPIService productStoreAPIService;



    @Override
    public Response addProduct(Request_Product body, String token) {
        productStoreAPIService = new ProductStoreAPIService();
        return productStoreAPIService.post(body, "api/v1/products", token);
    }
}
