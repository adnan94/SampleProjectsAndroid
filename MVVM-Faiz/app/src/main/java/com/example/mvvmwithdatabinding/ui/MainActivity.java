package com.example.mvvmwithdatabinding.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmwithdatabinding.R;
import com.example.mvvmwithdatabinding.commands.UserLogin;
import com.example.mvvmwithdatabinding.databinding.ActivityMainBinding;
import com.example.mvvmwithdatabinding.model.User;
import com.example.mvvmwithdatabinding.viewmodel.UserModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserModel userModel = new UserModel(new User("Email","Password"));
        activityMainBinding.setLogin(userModel);
        //this is not a good approach but for beginner this is of (FAIZ REHMAN)

        activityMainBinding.setUserLoginEvent(new UserLogin() {
            @Override
            public void onClickLogin() {
                Toast.makeText(MainActivity.this,""+activityMainBinding.getLogin().getUserEmail(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,ListviewActivity.class));
            }
        });

    }
}
