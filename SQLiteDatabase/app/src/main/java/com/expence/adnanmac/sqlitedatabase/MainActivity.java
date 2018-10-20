package com.expence.adnanmac.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDB db = new MyDB(this);
        db.createRecords("1237", "Adnan Ahmed");
        getData(db);
    }

    private void getData(MyDB db) {
        ArrayList<String> list = new ArrayList();
        Cursor mCursor = db.selectRecords();
        while (mCursor.moveToNext()) {
            String name = mCursor.getString(1);
            String id = mCursor.getString(0);
            list.add(name);
        }
        for (int i = 0; i < list.size(); i++) {
            Log.d("ANDROID-AP", list.get(i));
        }
        db.getDatabase().close();

    }
}
