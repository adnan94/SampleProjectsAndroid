package com.example.mvvmwithdatabinding.model;

public class User {

    private String userEmail;
    private String userPassword;
    public String userEmailHint;
    public String userPasswordHint;


    public User(String userEmail, String userPassword, String userEmailHint, String userPasswordHint) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userEmailHint = userEmailHint;
        this.userPasswordHint = userPasswordHint;
    }

    public User(String userEmailHint, String userPasswordHint) {
        this.userEmailHint = userEmailHint;
        this.userPasswordHint = userPasswordHint;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmailHint() {
        return userEmailHint;
    }

    public void setUserEmailHint(String userEmailHint) {
        this.userEmailHint = userEmailHint;
    }

    public String getUserPasswordHint() {
        return userPasswordHint;
    }

    public void setUserPasswordHint(String userPasswordHint) {
        this.userPasswordHint = userPasswordHint;
    }
}
