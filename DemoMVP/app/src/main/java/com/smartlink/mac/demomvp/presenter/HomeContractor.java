package com.smartlink.mac.demomvp.presenter;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public interface HomeContractor {

    interface HomeScreenPresenter {

        void doLogout(Context context);

        void buildRetrofit(Context context, FragmentManager manager);

        void nextActivity(View view,String name);

        void destroyCalled();


    }

    interface HomeScreenView {


        void loggedOut();

        void updateListView(ArrayList heroes);

        void nextActivity(String name);


    }
}
