package com.smartlink.mac.demomvp.Presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public interface HomeScreenPresenter {

    void doLogout(Context context);
    void buildRetrofit(Context context, FragmentManager manager);
}