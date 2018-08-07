package com.proteam.bai_13_3_ex_volley.model;

import com.google.gson.annotations.SerializedName;

public class Phone {

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @SerializedName("home")
    private String home;

    @SerializedName("mobile")
    private String mobile;

    public Phone() {
    }

}