package objectData.requestObject;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Request_Product implements Request_Preparation {

    private String title;
    private Integer price;
    private String description;
    private Integer categoryId;
    //private List<BookProduct> images;
    private List<String> images;

    public Request_Product(HashMap<String, String> testData) {
        prepareObject(testData);
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "title":
                    setTitle(testData.get(key));
                    break;
                case "price":
                    setPrice(Integer.valueOf(testData.get(key)));
                    break;
                case "description":
                    setDescription(testData.get(key));
                    break;
                case "categoryId":
                    setCategoryId(Integer.valueOf(testData.get(key)));
                    break;
                case "images":
                    prepareProducts(testData.get(key));
                    break;
            }
        }

    }

    //trebuie sa parsam valoarea pentru carti intr o lista de obiecte (BookProduct)
    private void prepareProducts(String value) {
        images = new ArrayList<>();
        String[] products = value.split(",");
        for (String product : products) {
            //images.add(new BookProduct(product));
            images.add(product.trim());

        }

    }

}
