package com.tilismtech.kaisanapp.ui.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.tilismtech.kaisanapp.R;

public class QRSucessActivity extends AppCompatActivity {

    ImageView failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrsucess);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(getString(R.string.result));
        failed = (ImageView) findViewById(R.id.failed);

   if(getIntent().getIntExtra("status",0) == 0){
       Glide.with(this).load(R.drawable.verified).into(failed);
   }else if(getIntent().getIntExtra("status",0) == -1){
       Glide.with(this).load(R.drawable.sorry).into(failed);
   }else if(getIntent().getIntExtra("status",0) == -2){
       Glide.with(this).load(R.drawable.failed).into(failed);
   }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
