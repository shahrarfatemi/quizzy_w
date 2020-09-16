package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {

    public User(String name, String email, String _id, String createdAt, String updatedAt) {
        this.name = name;
        this.email = email;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public User() {
    }

    String name;

    String email;

    String _id;

    String createdAt;

    String updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() throws Exception {
        Date createDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(createdAt);
        return createDate;
    }

    public Date getUpdateDate() throws Exception {
        Date upateDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(updatedAt);
        return upateDate;
    }

}
