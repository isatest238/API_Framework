package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter

public class ResponseTokenSuccess implements ResponseNotNull {

    @JsonProperty ("access_token")
    private String access_token;

    @JsonProperty ("refresh_token")
    private String refresh_token;


    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(access_token, "Access token is null!");
        Assert.assertNotNull(refresh_token, "Refresh token is null!");
    }
}
