package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter

public class ResponseAccountGetFailed {
    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    @JsonProperty("path")
    private String path;

    @JsonProperty("timestamp")
    private String timestamp;
}