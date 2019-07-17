package com.smartlink.mac.demomvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.smartlink.mac.demomvp.presenter.LoginPresenter;
import com.smartlink.mac.demomvp.view.AlertDialog.LoadingScreen;
import com.smartlink.mac.demomvp.view.LoginView;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenterImplementation implements LoginPresenter {
    LoginView loginView;
    final DialogFragment loadingScreen ;


    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
        this.loadingScreen = LoadingScreen.getInstance();

    }

    @Override
    public void signIn(String email, String password, FragmentManager manager) {


        if (email.isEmpty() || password.isEmpty()) {

            loginView.validationError();

        } else {
            loadingScreen.show(manager, "Please wait...");
            android.os.Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (email.equalsIgnoreCase("adnan@gmail.com") && password.equalsIgnoreCase("123456")) {
                        loginView.onSucess();
                    } else {
                        loginView.onError();
                    }
                    loadingScreen.dismiss();

                }
            }, 3000);


        }


    }

    @Override
    public void loginSucess(String email, String password, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user-data", MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }


}
