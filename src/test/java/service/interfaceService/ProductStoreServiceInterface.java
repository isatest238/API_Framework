package service.interfaceService;

import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import io.restassured.response.Response;

public interface ProductStoreServiceInterface {

    // aceasta interfata reprezinta actiunile pe care serviciul Product Store le poate face

    Response addProduct(Request_Product request_product);


    Response updateSpecificProduct(RequestUpdateProduct body, String productID);
    Response deleteSpecificProduct(String specificID);
    Response getSpecificProduct(String specificID);
    Response getProductList(int limit, int offset );
    Response getRelatedProduct(String productID);
}
