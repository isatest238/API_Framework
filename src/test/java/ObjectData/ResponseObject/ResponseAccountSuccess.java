package ObjectData.ResponseObject;

import ObjectData.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseAccountSuccess implements ResponseNotNull {
    @JsonProperty("id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("role")
    private String role;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("creationAt")
    private String creationAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id);
        Assert.assertNotNull(email);
        Assert.assertNotNull(password);
        Assert.assertNotNull(name);
        Assert.assertNotNull(creationAt);
        Assert.assertNotNull(updatedAt);
    }
}
