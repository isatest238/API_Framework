package service.serviceImplementation;

import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import objectData.requestObject.Request_User;
import service.apiService.ProductStoreAPIService;
import service.endpoints.UserEndPoints;
import service.interfaceService.ProductStoreServiceInterface;
import service.endpoints.ProductEndPoints;
import io.restassured.response.Response;

public class ProductStoreServiceImp implements ProductStoreServiceInterface {

    private final ProductStoreAPIService productStoreAPIService;

    public ProductStoreServiceImp() {
        productStoreAPIService = new ProductStoreAPIService();
    }

    @Override
    public Response addProduct(Request_Product body) {
        return productStoreAPIService.post(body, ProductEndPoints.PRODUCT_ADD);
    }

    @Override
    public Response updateSpecificProduct(RequestUpdateProduct body, String actualID) {
        String url = ProductEndPoints.PRODUCT_UPDATE + actualID;
        return productStoreAPIService.put(body, url);
    }

    @Override
    public Response getSpecificProduct(String productId) {
        String url = ProductEndPoints.PRODUCT_GET + productId;
        return productStoreAPIService.get(null, url);
    }

    @Override
    public Response getRelatedProduct(String productId){
        String url = ProductEndPoints.PRODUCT_GET + productId + "/related";
        return productStoreAPIService.get(null, url);
    }

    @Override
    public Response getProductList(int limit, int offset) {
        String url = ProductEndPoints.PRODUCT_GET_LIST + "?limit=" + limit + "&offset=" + offset;
        return productStoreAPIService.get(null, url);
    }

    @Override
    public Response deleteSpecificProduct(String productId) {
        String url = ProductEndPoints.PRODUCT_DELETE + productId;
        return productStoreAPIService.delete(productId, url);
    }
}
