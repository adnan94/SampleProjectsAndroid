package com.tilismtech.kaisanapp.ui.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.tilismtech.kaisanapp.R;
import com.tilismtech.kaisanapp.data.Api;
import com.tilismtech.kaisanapp.data.App;
import com.tilismtech.kaisanapp.ui.activities.QRActivity;
import com.tilismtech.kaisanapp.ui.activities.QRSucessActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class QRFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {

private QRCodeReaderView qrCodeReaderView;
int count = 0;
ProgressDialog progressDialog;
public  String BASE_URL = "https://www.tilismtechservices.com/adminportal/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr, container, false);
        init(view);
        return view;


}

    private void init(View view) {
            initQR(view);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        getActivity().setTitle("Scan");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");



    }

    private void initQR(View view) {

        qrCodeReaderView = (QRCodeReaderView) view.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();


    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        if(text!=null && !text.isEmpty() && count  == 1) {
            if (text.startsWith("http://itna.me/uc/")) {
                checkForQR(text);
            } else if(!text.startsWith("http://itna.me/uc/")){
                count = 0;
                failedScreen(-2);
                qrCodeReaderView.stopCamera();

            }
        }else{
            ++count;
        }
    }

    private void checkForQR(String text) {
        String url_id = text.substring(text.lastIndexOf("/")+1,text.length());
        buildRetrofitCall(url_id);
        qrCodeReaderView.stopCamera();
        count = 0;


    }



    private void buildRetrofitCall(String id) {
        progressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api = retrofit.create(Api.class);

        Call<Integer> call = api.getData(id);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                progressDialog.dismiss();
                int status = Integer.parseInt(response.body().toString());
switch(status){
    case 0:
        failedScreen(status);
        break;
    case -1:
        failedScreen(status);
        break;
         case -2:
             failedScreen(status);
        break;


}

            }


            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }

    public QRFragment() {
        // Required empty public constructor
    }


    public void failedScreen(int status){
        Intent i = new Intent(getActivity(), QRSucessActivity.class);
        i.putExtra("status",status);
          startActivity(i);
}


    @Override
    public void onDestroy() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        super.onDestroy();
    }
}
