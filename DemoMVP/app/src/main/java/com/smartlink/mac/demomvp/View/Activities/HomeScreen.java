package com.smartlink.mac.demomvp.view.Activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.smartlink.mac.demomvp.model.HomeScreenPresenterImplementation;
import com.smartlink.mac.demomvp.presenter.HomeContractor;
import com.smartlink.mac.demomvp.R;
import com.smartlink.mac.demomvp.databinding.ActivityHomeScreenBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity implements HomeContractor.HomeScreenView {

    HomeScreenPresenterImplementation homescreenPresenter;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.list)
    ListView listView;

    @BindView(R.id.proceed)
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_screen);
        ActivityHomeScreenBinding bind = DataBindingUtil.setContentView(this,R.layout.activity_home_screen);
        homescreenPresenter = new HomeScreenPresenterImplementation(this);
        bind.setName("Adnan Ahmed");
        bind.setAnyName(homescreenPresenter);

        inits();
    }

    private void inits() {
        ButterKnife.bind(this);

        homescreenPresenter.buildRetrofit(this, getSupportFragmentManager());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homescreenPresenter.doLogout(HomeScreen.this);
            }
        });


//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                homescreenPresenter.nextActivity(HomeScreen.this);
//            }
//        });
    }

    @Override
    public void loggedOut() {
        startActivity(new Intent(HomeScreen.this, MainActivity.class));
        finish();
        Toast.makeText(HomeScreen.this,"Logged Out",Toast.LENGTH_SHORT).show();
    }




    @Override
    public void updateListView(String[] heroes) {
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

    }

    @Override
    public void nextActivity(String name) {
        Toast.makeText(HomeScreen.this,"Clicked "+name,Toast.LENGTH_SHORT).show();


    }
}
