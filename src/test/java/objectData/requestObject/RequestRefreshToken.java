package objectData.requestObject;

import lombok.Data;

@Data
public class RequestRefreshToken {

    private String refreshToken;

    public RequestRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
