package model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    String token;
    User user;
    public UserInfo() {
    }


    public UserInfo(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
