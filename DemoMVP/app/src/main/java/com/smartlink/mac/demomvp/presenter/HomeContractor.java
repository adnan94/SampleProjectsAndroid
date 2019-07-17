package com.smartlink.mac.demomvp.presenter;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;

public interface HomeContractor {

    interface HomeScreenPresenter {

        void doLogout(Context context);

        void buildRetrofit(View view, Context context, FragmentManager manager);

        void nextActivity(View view,String name);
    }

    interface HomeScreenView {


        void loggedOut();

        void updateListView(String[] heroes);

        void nextActivity(String name);

    }
}
