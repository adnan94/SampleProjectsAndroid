package com.smartlink.mac.mvvmlivedataretrofit.model;

public class PlacesModel {
    String picUrl;
    String name;

    public PlacesModel(String picUrl, String name) {
        this.picUrl = picUrl;
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
