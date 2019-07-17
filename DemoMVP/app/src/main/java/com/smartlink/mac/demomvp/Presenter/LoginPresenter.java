package com.smartlink.mac.demomvp.presenter;

import android.content.Context;
import androidx.fragment.app.FragmentManager;

public interface LoginPresenter {

    void signIn(String email, String password , FragmentManager manager);


    void loginSucess(String email, String password , Context context);
}