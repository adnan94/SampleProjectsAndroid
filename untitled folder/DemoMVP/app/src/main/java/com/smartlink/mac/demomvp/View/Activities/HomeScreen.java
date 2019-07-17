package com.smartlink.mac.demomvp.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.smartlink.mac.demomvp.Model.HomeScreenPresenterImplementation;
import com.smartlink.mac.demomvp.Model.Pojoclass;
import com.smartlink.mac.demomvp.Presenter.HomeScreenPresenter;
import com.smartlink.mac.demomvp.R;
import com.smartlink.mac.demomvp.View.HomeScreenView;

import java.util.List;

public class HomeScreen extends AppCompatActivity implements HomeScreenView {

    HomeScreenPresenter homescreenPresenter;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        homescreenPresenter = new HomeScreenPresenterImplementation(this);

        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button);

        homescreenPresenter.buildRetrofit(this,getSupportFragmentManager());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homescreenPresenter.doLogout(HomeScreen.this);
            }
        });





    }

    @Override
    public void loggedOut() {
        startActivity(new Intent(HomeScreen.this,MainActivity.class));
        finish();
    }

    @Override
    public void updateListView(String[] heroes) {
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

    }
}
