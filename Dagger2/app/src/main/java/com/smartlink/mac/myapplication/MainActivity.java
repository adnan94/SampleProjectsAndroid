package com.smartlink.mac.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    String eggs;

    @Inject
    Integer quantity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication())
                .getAppComponent()
                .inject(this);

        TextView eggsView = findViewById(R.id.text);
        eggsView.setText("Cooked the "+quantity+" "+eggs);
    }
}
