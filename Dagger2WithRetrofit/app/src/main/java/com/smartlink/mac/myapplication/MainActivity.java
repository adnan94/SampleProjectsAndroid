package com.smartlink.mac.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.smartlink.mac.myapplication.Retrofit.Api;
import com.smartlink.mac.myapplication.Retrofit.Pojoclass;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    String eggs;

    @Inject
    Integer quantity;

@Inject
Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication())
                .getAppComponent()
                .inject(this);

        //inject from refrigratorClass
        TextView eggsView = findViewById(R.id.text);
        eggsView.setText("Cooked the "+quantity+" "+eggs);

        buildRetrofit();

    }


    private void buildRetrofit() {
        Call<List<Pojoclass>> call = api.getHeroes();

        call.enqueue(new Callback<List<Pojoclass>>() {
            @Override
            public void onResponse(Call<List<Pojoclass>> call, Response<List<Pojoclass>> response) {
                List<Pojoclass> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                    Log.d("Names--",heroList.get(i).getName());
                }

            }


            @Override
            public void onFailure(Call<List<Pojoclass>> call, Throwable t) {
                Log.d("Error","Marvel Error"+t.getLocalizedMessage());
            }
        });

    }

}
