package ObjectData.ResponseObject;

import ObjectData.ResponseCategory;
import ObjectData.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProductSuccess implements ResponseNotNull {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("price")
    private String price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private ResponseCategory category; // un alt obiect
    @JsonProperty("images")
    private List<String> images;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id, "id is null");
        Assert.assertNotNull(title, "title is null");
        Assert.assertNotNull(slug, "slug is null");
        Assert.assertNotNull(price, "price is null");
        Assert.assertNotNull(description, "description is null");
        Assert.assertNotNull(category, "category is null");
        category.validateNotNullFields();
        Assert.assertNotNull(images, "images list is null");
    }
}
