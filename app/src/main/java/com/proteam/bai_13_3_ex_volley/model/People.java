package com.proteam.bai_13_3_ex_volley.model;

import com.google.gson.annotations.SerializedName;

public class People {

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @SerializedName("phone")
    private Phone phone;

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


    public People() {
    }

}