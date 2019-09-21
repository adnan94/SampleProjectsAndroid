package com.revolutionary.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.revolutionary.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        user = new User("Zeeshan Ali");
        activityMainBinding.setUser(user);
        activityMainBinding.setLogin("Adnan Ahmed");
        activityMainBinding.setMainactivity(this);
    }


    public void clicked() {
        user.setName("Folio 3");
        Log.d(getPackageName().getClass().getSimpleName(), "Clicked");
    }


}
