package com.smartlink.mac.mvvmlivedataretrofit.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.smartlink.mac.mvvmlivedataretrofit.model.PlacesModel;
import com.smartlink.mac.mvvmlivedataretrofit.repository.PlacesRepository;

import java.util.List;


public class MainViewModel extends ViewModel {

    private MutableLiveData<List<PlacesModel>> places;
    private PlacesRepository placeRepo;
    private MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();



    public void init() {
        if (places != null) {
            return;
        }
        placeRepo = PlacesRepository.getInstance();
        places = placeRepo.getPlaces();
    }

    public LiveData<List<PlacesModel>> getPlaces() {
        return places;
    }


    public void addNewValue(final PlacesModel place){
        isUpdated.setValue(true);

        AsyncTask<Void,Void,Void> async = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                isUpdated.setValue(false);
                List<PlacesModel> currentValues = places.getValue();
                currentValues.add(place);
                places.postValue(currentValues);
                isUpdated.postValue(false);
                        super.onPostExecute(aVoid);
            }
        }.execute();
    }


    public LiveData<Boolean> isUpdating(){
        return isUpdated;
    }


}
