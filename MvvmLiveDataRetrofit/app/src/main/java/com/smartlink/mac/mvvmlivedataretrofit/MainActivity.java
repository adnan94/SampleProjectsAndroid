package com.smartlink.mac.mvvmlivedataretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartlink.mac.mvvmlivedataretrofit.adapters.AdopterPlaces;
import com.smartlink.mac.mvvmlivedataretrofit.model.PlacesModel;
import com.smartlink.mac.mvvmlivedataretrofit.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdopterPlaces adaptor;
    MainViewModel mainViewModel;
    ProgressDialog progressDialog;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mainViewModel.init();

        casts();
        createRecyclerView();
        observePlacesdata();
        observeUpdating();
    }

    private void observeUpdating() {
        mainViewModel.isUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                    recyclerView.smoothScrollToPosition(mainViewModel.getPlaces().getValue().size()-1);

                }
            }
        });
    }

    private void createRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adaptor = new AdopterPlaces(mainViewModel.getPlaces().getValue(), this);
        recyclerView.setAdapter(adaptor);
    }

    private void observePlacesdata() {
        mainViewModel.getPlaces().observe(this, new Observer<List<PlacesModel>>() {
            @Override
            public void onChanged(List<PlacesModel> placesModels) {
                adaptor.notifyDataSetChanged();

            }
        });

    }

    public void casts(){
        button = (Button)findViewById(R.id.addMore);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.addNewValue(new PlacesModel("https://source.unsplash.com/random","HongKong"));

            }
        });
    }
}
