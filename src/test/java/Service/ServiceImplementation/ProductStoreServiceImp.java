package Service.ServiceImplementation;

import ObjectData.RequestObject.RequestUpdateProduct;
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


    @Override
    public Response updateSpecificProduct(RequestUpdateProduct body, String actualID) {
        productStoreAPIService = new ProductStoreAPIService();
        String url = "https://api.escuelajs.co/api/v1/products/" + actualID;
        return productStoreAPIService.put(body, url);
    }

    @Override
    public Response getSpecificProduct(String actualID) {
        productStoreAPIService = new ProductStoreAPIService();
        String url = "https://api.escuelajs.co/api/v1/products/" + actualID;
        return productStoreAPIService.get(actualID, url);
    }


    @Override
    public Response deleteSpecificProduct(String specificID) {
        productStoreAPIService = new ProductStoreAPIService();
        String url = "https://api.escuelajs.co/api/v1/products/" + specificID;
        return productStoreAPIService.delete(specificID, url);
    }
}
