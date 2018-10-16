package com.expence.adnanmac.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String urlObject = "http://www.blueappsoftware.in/android/downloadcode/objectfile.json";
    String urlArray = "https://simplifiedcoding.net/demos/marvel/";
    RequestQueue singleTonRequestQueue;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singleTonRequestQueue = SingletonClass.getInstance().getRequestQueue();
        jsonObjectParsing(urlObject);
    }

    public void stringReqMethod(String url) {
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String data = s.toString().substring(0, 300);
                Toast.makeText(MainActivity.this, "data" + data, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        singleTonRequestQueue.add(strReq);
    }

    //
    public void jsonObjectParsing(String url) {
        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject objJson = jsonObject.getJSONObject("rates");
                    String get = objJson.getString("AED");
                    Toast.makeText(MainActivity.this, "This Is the Result ::" + get, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "no network" + volleyError, Toast.LENGTH_SHORT).show();
            }
        });
        singleTonRequestQueue.add(obj);
    }

    //
//
    public void jsonArrayParsing(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String ID = jsonObject.getString("id");
                        String body = jsonObject.getString("body");
                        data = "Title : " + title + "\n" + "Body : " + body + "\n" + "Id : " + ID + "\n";
                        Toast.makeText(MainActivity.this, "This Is the Result ::" + data, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Log.d("TAG", "This Is The Response : " + jsonArray.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        singleTonRequestQueue.add(jsonArrayRequest);
    }
//
//    public void alert(String title, String message) {
//        AlertDialog.Builder ab = new AlertDialog.Builder(this);
//        ab.setCancelable(true);
//        ab.setTitle(title);
//        ab.setMessage(message);
//        ab.show();
//    }
}
