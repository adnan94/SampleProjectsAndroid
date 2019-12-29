package com.revolutionary.democonstrintslayout;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doAsync((String s) -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void doAsync(final SucessListener listener) {
        AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                listener.sucess("Do in background");
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                listener.sucess("Post Execute");
                super.onPostExecute(s);
            }
        };
        asyncTask.execute();
    }
}

interface SucessListener {
    void sucess(String str);
}