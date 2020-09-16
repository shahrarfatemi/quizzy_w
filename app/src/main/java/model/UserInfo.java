package model;

import java.io.Serializable;

public class UserInfo extends User implements Serializable {
    String token;

    public UserInfo() {
    }


    public UserInfo(String name, String email, String _id, String createdAt, String updatedAt, String token) {
        super(name, email, _id, createdAt, updatedAt);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
