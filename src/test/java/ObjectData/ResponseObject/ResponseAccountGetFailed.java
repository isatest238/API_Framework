package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseAccountGetFailed {
    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    @JsonProperty("path")
    private String path;

    @JsonProperty("timestamp")
    private String timestamp;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }
}