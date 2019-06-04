package com.example.churmo.choys;

import org.json.JSONObject;

public class Usuarios {

    private String id,
            first_name,
            last_name,
            email,
            avatar;

    public Usuarios(JSONObject jsonObject) {

        this.id = jsonObject.optString("id");
        this.first_name = jsonObject.optString("first_name");
        this.last_name = jsonObject.optString("last_name");
        this.email = jsonObject.optString("email");
        this.avatar = jsonObject.optString("avatar");
    }

    public Usuarios(String id, String first_name, String last_name, String email, String avatar) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
