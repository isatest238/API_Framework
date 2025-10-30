package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response_Token_Success {

    @JsonProperty ("access_token")
    private String access_token;

    @JsonProperty ("refresh_token")
    private String refresh_token;

    @JsonProperty ("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    @JsonProperty ("result")
    private String result;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}
