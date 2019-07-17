package com.smartlink.mac.demomvp.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.smartlink.mac.demomvp.Presenter.HomeScreenPresenter;
import com.smartlink.mac.demomvp.Presenter.LoginPresenter;
import com.smartlink.mac.demomvp.View.AlertDialog.LoadingScreen;
import com.smartlink.mac.demomvp.View.Api;
import com.smartlink.mac.demomvp.View.HomeScreenView;
import com.smartlink.mac.demomvp.View.LoginView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class HomeScreenPresenterImplementation implements HomeScreenPresenter {
    HomeScreenView homescreenView;
    final DialogFragment loadingScreen;

    public HomeScreenPresenterImplementation(HomeScreenView homescreenView) {
        this.homescreenView = homescreenView;
        this.loadingScreen = LoadingScreen.getInstance();
    }

    @Override
    public void doLogout(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user-data", MODE_PRIVATE).edit();
        editor.clear().commit();
        homescreenView.loggedOut();
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

                String[] heroes = new String[heroList.size()];

                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }

                //displaying the string array into listview
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


}
