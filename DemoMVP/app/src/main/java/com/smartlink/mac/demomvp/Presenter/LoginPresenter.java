package com.smartlink.mac.demomvp.Presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public interface LoginPresenter {

    void signIn(String email, String password , FragmentManager manager);


    void loginSucess(String email, String password , Context context);
}