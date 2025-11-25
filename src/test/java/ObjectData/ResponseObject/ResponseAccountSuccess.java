package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseAccountSuccess {
    @JsonProperty("id")
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCreationAt() {
        return creationAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
