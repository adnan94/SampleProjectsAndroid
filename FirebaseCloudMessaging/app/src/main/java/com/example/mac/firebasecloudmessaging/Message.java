package com.example.mac.firebasecloudmessaging;


public class Message {
    private String text;
    private String name;
    private String photoUrl;
private String token;

    public Message() {
    }


    public Message(String text, String name, String photoUrl, String token) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

