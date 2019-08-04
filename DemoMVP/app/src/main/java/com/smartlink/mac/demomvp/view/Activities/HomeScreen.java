package com.smartlink.mac.demomvp.view.Activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.res.Configuration;
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

import java.util.ArrayList;

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

    ArrayList list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeScreenBinding bind = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);
        inits(bind);
        if (savedInstanceState == null) {
            setAdaptor();
            homescreenPresenter.buildRetrofit(this, getSupportFragmentManager());
        }
    }

    private void setAdaptor() {
        adapter = null;
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", list);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        list = savedInstanceState.getStringArrayList("list");
        setAdaptor();

    }


    private void inits(ActivityHomeScreenBinding bind) {
        ButterKnife.bind(this);
        homescreenPresenter = new HomeScreenPresenterImplementation(this);
        bind.setName("Adnan Ahmed");
        bind.setAnyName(homescreenPresenter);
        list = new ArrayList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homescreenPresenter.doLogout(HomeScreen.this);
            }
        });

    }

    @Override
    public void loggedOut() {
        startActivity(new Intent(HomeScreen.this, MainActivity.class));
        finish();
        Toast.makeText(HomeScreen.this, "Logged Out", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateListView(ArrayList heroes) {
        list.addAll(heroes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void nextActivity(String name) {
        Toast.makeText(HomeScreen.this, "Clicked " + name, Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        homescreenPresenter.destroyCalled();
    }
}
