package com.example.mvvmwithdatabinding.viewmodel;

import android.databinding.BaseObservable;

import com.example.mvvmwithdatabinding.R;
import com.example.mvvmwithdatabinding.model.User;

public class UserModel extends BaseObservable {

    private String userEmail;
    private String userPassword;
    private String userEmailHint;
    private String userPasswordHint;
    private User user;



    public UserModel(User user) {

        this.userEmailHint = user.userEmailHint;
        this.userPasswordHint  =user.userPasswordHint;

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        notifyPropertyChanged(R.id.edt_user_email);
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(R.id.edt_user_pass);
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
