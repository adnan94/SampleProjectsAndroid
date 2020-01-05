package com.revolutionary.orchardoranges.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.revolutionary.orchardoranges.R;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        getSupportActionBar().hide();


    }


}
