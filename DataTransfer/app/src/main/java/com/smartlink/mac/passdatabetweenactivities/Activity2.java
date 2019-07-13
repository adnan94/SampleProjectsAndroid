package com.smartlink.mac.passdatabetweenactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    Button back;
  Communicator comm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Toast.makeText(this, getIntent().getStringExtra("text"), Toast.LENGTH_SHORT).show();

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.showMessage("Got It Comm");

                Intent i = new Intent();
                i.putExtra("text", "Got Back !");
                setResult(0, i);
                finish();

            }
        });
    }

}
