package com.tilismtech.kaisanapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tilismtech.kaisanapp.R;
import com.tilismtech.kaisanapp.ui.activities.TermsActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    Button terms;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View  view = inflater.inflate(R.layout.fragment_information, container, false);
      inits(view);
        return view;
    }

    private void inits(View view) {
        getActivity().setTitle("Information");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        terms = (Button)view.findViewById(R.id.terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TermsActivity.class));
            }
        });

    }


    @Override
    public void onDestroy() {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        super.onDestroy();
    }
}
