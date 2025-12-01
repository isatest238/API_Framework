package actions;

import objectData.requestObject.RequestUpdateProduct;
import objectData.requestObject.Request_Product;
import objectData.responseObject.ResponseProductSuccess;
import restClient.ResponseStatus;
import service.serviceImplementation.ProductStoreServiceImp;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class ProductStoreActions {
    private final ProductStoreServiceImp productStoreServiceImp;


    public ProductStoreActions() {
        productStoreServiceImp = new ProductStoreServiceImp();
    }

    public ResponseProductSuccess addProduct(Request_Product body) {
        Response response = productStoreServiceImp.addProduct(body);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseProductSuccess responseProductSuccess = response.body().as(ResponseProductSuccess.class);
        responseProductSuccess.validateNotNullFields();

        return responseProductSuccess;

    }

    public void updateSpecificProduct(RequestUpdateProduct requestBody, String actualProductID) {
        Response response = productStoreServiceImp.updateSpecificProduct(requestBody, actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);


        ResponseProductSuccess responseProductSuccess = response.body().as(ResponseProductSuccess.class);
        responseProductSuccess.validateNotNullFields();
        Assert.assertEquals(responseProductSuccess.getId(), actualProductID);
        Assert.assertEquals(responseProductSuccess.getTitle(), requestBody.getTitle());
        Assert.assertEquals(responseProductSuccess.getPrice().intValue(), requestBody.getPrice().intValue());
    }

    public void getSpecificProduct(String actualProductID) {
        Response response = productStoreServiceImp.getSpecificProduct(actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);
        String body = response.getBody().asString();

        ResponseProductSuccess relatedProduct = response.body().as(ResponseProductSuccess.class);
        relatedProduct.validateNotNullFields();

    }

    public void getRelatedProduct (String actualProductID) {
        Response response = productStoreServiceImp.getRelatedProduct(actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        String body = response.getBody().asString();

    }

    public void getProductsWithLimit(int limit, int offset) {
        Response response = productStoreServiceImp.getProductList(limit, offset );
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

    }

    public void verifyProductDeleted(String actualProductID) {
        Response response = productStoreServiceImp.getSpecificProduct(actualProductID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_BAD_REQUEST);
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains("EntityNotFoundError"),
                "Expected EntityNotFoundError in response body");
    }

    public void deleteSpecificProduct(String specificID) {
        Response response = productStoreServiceImp.deleteSpecificProduct(specificID);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

    }

}

