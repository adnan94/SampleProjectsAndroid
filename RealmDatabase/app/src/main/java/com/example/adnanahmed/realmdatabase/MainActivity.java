package com.example.adnanahmed.realmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Realm realm = Realm.getDefaultInstance(); // opens "myrealm.realm"



        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm.setDefaultConfiguration(config);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User();
                user.setName("Adnan Ahmed");
                user.setCity("Karachi");
                user.setCountry("Pakistan");
                user.setId("adnan_ahmed1");
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(user);
                    }
                });
            }
        });

    }


}
