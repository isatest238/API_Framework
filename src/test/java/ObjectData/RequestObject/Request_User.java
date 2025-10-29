package ObjectData.RequestObject;

import ObjectData.Request_Preparation;

import java.util.HashMap;

public class Request_User implements Request_Preparation {

    private String email;
    private String name;
    private String password;
    private String role;
    private String avatar;
public Request_User(HashMap<String, String> testData){
    prepareObject(testData);
}

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "email":
                    setEmail(testData.get(key));
                    break;
                case "name":
                    setName(testData.get(key));
                    break;
                case "password":
                    setPassword(testData.get(key));
                    break;
                case "role":
                    setRole(testData.get(key));
                    break;
                case "avatar":
                    setAvatar(testData.get(key));
                    break;
            }
        }
        adjustObjectValue();
    }

    private void adjustObjectValue(){
    email= email.replace("@yahoo.com","") + System.currentTimeMillis() + "@yahoo.com";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
