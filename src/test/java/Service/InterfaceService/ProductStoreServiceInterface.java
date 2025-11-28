package Service.InterfaceService;

import ObjectData.RequestObject.Request_Product;
import io.restassured.response.Response;

public interface ProductStoreServiceInterface {

    // aceasta interfata reprezinta actiunile pe care serviciul Product Store le poate face

    Response addProduct(Request_Product request_product, String token);

    //DE IMPLEMENTAT DATA VIITOARE
    Response updateSpecificProduct();
    Response deleteSpecificProduct(String specificID);
}
