package com.smartlink.mac.demomvp.view.Activities;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.smartlink.mac.demomvp.R;
import com.smartlink.mac.demomvp.databinding.ActivityHomeScreenBinding;
import com.smartlink.mac.demomvp.model.HomeScreenPresenterImplementation;
import com.smartlink.mac.demomvp.model.Pojoclass;
import com.smartlink.mac.demomvp.view.AlertDialog.LoadingScreen;
import com.smartlink.mac.demomvp.view.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    ListView listView;
    ArrayList list;
    private ArrayAdapter<String> adapter;
    DialogFragment loadingScreen;
    private Call<List<Pojoclass>> call;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        inits(view);
        if (savedInstanceState == null) {
            setAdaptor();
            buildRetrofit(getActivity());

        }

        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            list = savedInstanceState.getStringArrayList("list");
            setAdaptor();

        }
    }

    private void setAdaptor() {
        adapter = null;
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }

    private void inits(View view) {
        ButterKnife.bind(this, view);
        list = new ArrayList();

    }

    public void buildRetrofit(Context context) {
        loadingScreen = LoadingScreen.getInstance();
        loadingScreen.show(getActivity().getSupportFragmentManager(), "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);

        call = api.getHeroes();

        call.enqueue(new Callback<List<Pojoclass>>() {
            @Override
            public void onResponse(Call<List<Pojoclass>> call, Response<List<Pojoclass>> response) {
                List<Pojoclass> heroList = response.body();


                for (int i = 0; i < heroList.size(); i++) {
                    list.add(heroList.get(i).getName());
                    adapter.notifyDataSetChanged();
                }

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
