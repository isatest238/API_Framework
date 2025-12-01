package service.serviceImplementation;

import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import service.apiService.ProductStoreAPIService;
import service.interfaceService.ProductStoreServiceInterface;
import service.endpoints.ProductEndPoints;
import io.restassured.response.Response;

public class ProductStoreServiceImp implements ProductStoreServiceInterface {

    private final ProductStoreAPIService productStoreAPIService;

    public ProductStoreServiceImp() {
        productStoreAPIService = new ProductStoreAPIService();
    }

    @Override
    public Response addProduct(Request_Product body, String token) {
        return productStoreAPIService.post(body, ProductEndPoints.PRODUCT_ADD, token);
    }


    @Override
    public Response updateSpecificProduct(RequestUpdateProduct body, String actualID) {
        String url = ProductEndPoints.PRODUCT_UPDATE + actualID;
        return productStoreAPIService.put(body, url);
    }

    @Override
    public Response getSpecificProduct(String actualID) {
        String url = ProductEndPoints.PRODUCT_GET + actualID;
        return productStoreAPIService.get(actualID, url);
    }

    @Override
    public Response deleteSpecificProduct(String specificID) {
        String url = ProductEndPoints.PRODUCT_DELETE + specificID;
        return productStoreAPIService.delete(specificID, url);
    }
}
