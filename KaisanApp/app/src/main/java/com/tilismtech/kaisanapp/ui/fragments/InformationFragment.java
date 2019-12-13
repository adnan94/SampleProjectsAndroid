package com.tilismtech.kaisanapp.ui.fragments;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tilismtech.kaisanapp.R;
import com.tilismtech.kaisanapp.ui.activities.QRActivity;
import com.tilismtech.kaisanapp.ui.activities.TermsActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    Button terms;
    ImageView topBanner,flowPic,ultra_pic,tilism_icon;
    private TextView title;
    private ImageButton back;

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

        Toolbar toolbar = getActivity().findViewById(R.id.tool);
        title = (TextView)toolbar.findViewById(R.id.title);
        back = (ImageButton)toolbar.findViewById(R.id.back);


        back.setVisibility(View.VISIBLE);
        title.setText(getActivity().getString(R.string.information));




        terms = (Button)view.findViewById(R.id.terms);
        topBanner = (ImageView) view.findViewById(R.id.iv);
        flowPic = (ImageView) view.findViewById(R.id.iv2);
        ultra_pic = (ImageView) view.findViewById(R.id.ultra);
        tilism_icon = (ImageView) view.findViewById(R.id.tilism_icon);

        Glide.with(this).load(R.drawable.top_banner_info_screen).into(topBanner);
        Glide.with(this).load(R.drawable.pic_info).into(flowPic);
        Glide.with(this).load(R.drawable.ultra_pic).into(ultra_pic);
        Glide.with(this).load(R.drawable.tilism_icon_launch).into(tilism_icon);


        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TermsActivity.class));
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public void onResume() {
        ((QRActivity)getActivity()).hideViews();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        back.setVisibility(View.GONE);
        ((QRActivity)getActivity()).enableViews();
        super.onDestroy();
    }
}
