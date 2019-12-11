package com.tilismtech.kaisanapp.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tilismtech.kaisanapp.ui.fragments.InformationFragment;
import com.tilismtech.kaisanapp.ui.fragments.QRFragment;
import com.tilismtech.kaisanapp.utility.BottomNavigationviewHelper;
import com.tilismtech.kaisanapp.R;

public class QRActivity extends AppCompatActivity{

TextView informationButton,qrButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        inits();
    }

    private void inits() {
        casts();
        clicks();
        //        bottomNavWork();
    }

    private void clicks() {
    qrButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new QRFragment(), "QRFragment").addToBackStack("").commit();
        }
    }); informationButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new InformationFragment(), "InformationFragment").addToBackStack("").commit();
        }
    });
    }

    private void casts() {
        qrButton = (TextView) findViewById(R.id.scanQR);
        informationButton = (TextView) findViewById(R.id.information);

    }



    private void bottomNavWork() {
//            BottomNavigationView navView = findViewById(R.id.nav_view);
//
////            for (int i = 0; i < 5; i++) {
////                MenuItem item = navView.getMenu().getItem(i);
////
////                SpannableString spannableString = new SpannableString(item.getTitle());
////                spannableString.setSpan(new MenuSpanable(), 0, spannableString.length(), 0);
////                item.setTitle(spannableString);
////                Utils.applyFontToMenuItem(item, App.getInstance());
////
////            }
//
//
//            BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//                    = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.barcode:
//                            getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new QRFragment(), "QRFragment").commit();
//                            return true;
//                        case R.id.information:
//                            getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new InformationFragment(), "InformationFragment").commit();
//
//                            return true;
//                       }
//                    return false;
//                }
//            };
//
//
//            BottomNavigationviewHelper.disableShiftMode(navView);
//            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//            if (navView.getSelectedItemId() == R.id.barcode) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new QRFragment(), "QRFragment").commit();
//            }



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            getSupportFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

