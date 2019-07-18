package com.smartlink.mac.mvvmlivedataretrofit.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartlink.mac.mvvmlivedataretrofit.model.PlacesModel;

import java.util.ArrayList;
import java.util.List;

public class PlacesRepository {
    private static PlacesRepository instance;
    private ArrayList<PlacesModel> list = new ArrayList<>();

    public static PlacesRepository getInstance() {
        if (instance == null) {
            instance = new PlacesRepository();
        }
        return instance;
    }



    public MutableLiveData<List<PlacesModel>> getPlaces() {

        final MutableLiveData<List<PlacesModel>> placesData =
                new MutableLiveData<>();
        setPlaces();
        placesData.setValue(list);
        return placesData;

    }



    public void setPlaces() {

        list.add(new PlacesModel("https://source.unsplash.com/random", "London"));
        list.add(new PlacesModel("https://source.unsplash.com/random", "Paris"));
        list.add(new PlacesModel("https://source.unsplash.com/random", "New York"));
        list.add(new PlacesModel("https://source.unsplash.com/random", "Australia"));

    }
}
