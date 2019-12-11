package com.tilismtech.demobindingadaptor;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.tilismtech.demobindingadaptor.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    User user;
ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("Zeeshan Ali");
        activityMainBinding.setUser(user);
        activityMainBinding.setLogin("Adnan Ahmed");
        activityMainBinding.setMainactivity(this);

        setupRecyclerView();
    }

    public void setupRecyclerView(){
      RecyclerView recyclerView = activityMainBinding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        getList();
        RecyclerBindingAdaptor recyclerBindingAdaptor = new RecyclerBindingAdaptor(this);
        RecyclerAdaptor adaptor = new RecyclerAdaptor(getList(), this,recyclerBindingAdaptor);
        recyclerView.setAdapter(adaptor);
    }

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://source.unsplash.com/random");
        return list;
    }


    public void clicked() {
        user.setName("Folio 3");
        Log.d(getPackageName().getClass().getSimpleName(), "Clicked");
    }


}
