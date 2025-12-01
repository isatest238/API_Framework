package restClient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    // layer 1 = clasa unde sunt definite configurari la nivel de client
    // am de facut doua actiuni:
    // 1. method care configureaza clientul
    // 2. method care returneaza un raspuns pe baza configurarilor de la client

    //Layer 1
    private RequestSpecification prepareClient(RequestSpecification requestSpecification) {
        requestSpecification.baseUri("https://api.escuelajs.co/");
        requestSpecification.contentType("application/json");
        return requestSpecification;
    }

    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endpoint) {
        switch (requestType) {
            case RequestType.REQUEST_POST:
                return prepareClient(requestSpecification).post(endpoint);
            case RequestType.REQUEST_PUT:
                return prepareClient(requestSpecification).put(endpoint);
            case RequestType.REQUEST_DELETE:
                return prepareClient(requestSpecification).delete(endpoint);
            case RequestType.REQUEST_GET:
                return prepareClient(requestSpecification).get(endpoint);
        }
        return null;
    }


}
