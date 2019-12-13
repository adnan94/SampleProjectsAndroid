package com.tilismtech.kaisanapp.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tilismtech.kaisanapp.ui.fragments.InformationFragment;
import com.tilismtech.kaisanapp.ui.fragments.QRFragment;
import com.tilismtech.kaisanapp.utility.BottomNavigationviewHelper;
import com.tilismtech.kaisanapp.R;

public class QRActivity extends AppCompatActivity{

    private MenuItem mi;
    private TextView information;
    private TextView title;
    private ImageView arrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        inits();
    }

    private void inits() {
        Toolbar toolbar = findViewById(R.id.tool);
        information =(TextView) findViewById(R.id.information);
        title =(TextView) findViewById(R.id.title);
        arrow =(ImageView) findViewById(R.id.arrow);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new QRFragment(), "QRFragment").commit();


        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new InformationFragment(), "QRFragment").addToBackStack(null).commit();

            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new InformationFragment(), "QRFragment").addToBackStack(null).commit();

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    public void enableViews(){
        if(mi!=null){
            information.setEnabled(true);
            arrow.setEnabled(true);
            mi.setVisible(true);
            information.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);
            title.setText("");

        }
    }

    public void hideViews(){
        information.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
    }


    @Override
    protected void onPause() {

        super.onPause();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            getSupportFragmentManager().popBackStack();
        }else if(item.getItemId()== R.id.action_settings) {
            mi.setVisible(false);
            getSupportFragmentManager().beginTransaction().replace(R.id.contentpanel, new InformationFragment(), "InformationFragment").addToBackStack("").commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolmenu, menu);
        mi = menu.getItem(0);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

