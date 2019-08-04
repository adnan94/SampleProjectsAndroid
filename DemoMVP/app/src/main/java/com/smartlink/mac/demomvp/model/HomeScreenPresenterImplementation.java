package com.smartlink.mac.demomvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Toast;

import com.smartlink.mac.demomvp.presenter.HomeContractor;
import com.smartlink.mac.demomvp.view.AlertDialog.LoadingScreen;
import com.smartlink.mac.demomvp.view.Api;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.content.Context.MODE_PRIVATE;

public class HomeScreenPresenterImplementation implements HomeContractor.HomeScreenPresenter {
    HomeContractor.HomeScreenView homescreenView;
    final DialogFragment loadingScreen;



    public HomeScreenPresenterImplementation(HomeContractor.HomeScreenView homescreenView) {
        this.homescreenView = homescreenView;
        this.loadingScreen = LoadingScreen.getInstance();
    }

    @Override
    public void doLogout(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user-data", MODE_PRIVATE).edit();
        editor.clear().commit();
        homescreenView.loggedOut();
    }


    public String getName(){
        return "Adnan";
    }


    public void buildRetrofit(Context context, FragmentManager manager) {
        loadingScreen.show(manager, "Loading List");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Pojoclass>> call = api.getHeroes();

        call.enqueue(new Callback<List<Pojoclass>>() {
            @Override
            public void onResponse(Call<List<Pojoclass>> call, Response<List<Pojoclass>> response) {
                List<Pojoclass> heroList = response.body();

                ArrayList heroes = new ArrayList();

                for (int i = 0; i < heroList.size(); i++) {
                    heroes.add(heroList.get(i).getName());
                }
                homescreenView.updateListView(heroes);
                loadingScreen.dismiss();
            }


            @Override
            public void onFailure(Call<List<Pojoclass>> call, Throwable t) {
                loadingScreen.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void nextActivity(View view,String name) {
        homescreenView.nextActivity(name);
//        context.startActivity(new Intent(context, NewActivity.class));
    }
}
