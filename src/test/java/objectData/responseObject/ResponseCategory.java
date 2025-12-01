package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseCategory implements ResponseNotNull {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("image")
    private String image;
    @JsonProperty("creationAt")
    private String creationAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id, "category.id is null");
        Assert.assertNotNull(name, "category.name is null");
        Assert.assertNotNull(image, "category.image is null");
        Assert.assertNotNull(slug, "category.slug is null");
        Assert.assertNotNull(creationAt, "category.image is null");
        Assert.assertNotNull(updatedAt, "category.image is null");
    }
}


