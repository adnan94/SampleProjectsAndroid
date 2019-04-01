package com.example.mac.firebasecloudmessaging;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mac.firebasecloudmessaging.Volleyy.SingletonClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button register, logout, login, sent;
    EditText textField;
    FirebaseAuth aut;
    private String token;
    String urlArray = "https://us-central1-demofcm-d104a.cloudfunctions.net/helloWorld";
    RequestQueue singleTonRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
    }

    private void inits() {
        singleTonRequestQueue = SingletonClass.getInstance().getRequestQueue();

        aut = FirebaseAuth.getInstance();
        if (aut.getCurrentUser() != null) {
            FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");

        }

        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        logout = (Button) findViewById(R.id.logout);
        sent = (Button) findViewById(R.id.sent);

        textField = (EditText) findViewById(R.id.textField);
        clicks();
    }

    private void clicks() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aut.createUserWithEmailAndPassword(textField.getText().toString(), "123456")
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    Toast.makeText(MainActivity.this, "Sucessfully Registered !", Toast.LENGTH_SHORT).show();

                                    getInstanceId();
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aut.signInWithEmailAndPassword(textField.getText().toString(), "123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        FirestoreUtil.getCurrentUser
                        if (task.isSuccessful()) {

                            setUpdateInctanceToken();
                            FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");
                            Toast.makeText(MainActivity.this, "Logged In !", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(MainActivity.this, "Logged In Failed ! " + task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("pushNotifications");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                aut.signOut();
            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (token != null) {
                    Message message = new Message("" + textField.getText().toString(), "Demo FCM !", null, token);
                    FirebaseDatabase.getInstance().getReference().child("messages").push().setValue(message);
//                    new GetUrlContentTask().execute("https://us-central1-demofcm-d104a.cloudfunctions.net/helloWorld");
                    post();
                } else {
                    Toast.makeText(MainActivity.this, "Token is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void post() {
        final HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("token", token);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                urlArray, new JSONObject(postParams),
                new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_LONG).show();

                            if (response.getBoolean("status")) {
                                Log.e("TAG", "");
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        singleTonRequestQueue.add(jsonObjReq);
    }


    private void getInstanceId() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        FirebaseUser user = aut.getCurrentUser();
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", user.getUid());
                        map.put("email", user.getEmail());
                        map.put("token", token);
                        FirebaseDatabase.getInstance().getReference().child("AppData").child("Users").child(user.getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "Sucessfully Data Saved !", Toast.LENGTH_SHORT).show();

                            }
                        });
                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("TAG", token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setUpdateInctanceToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        FirebaseUser user = aut.getCurrentUser();

                        FirebaseDatabase.getInstance().getReference().child("AppData").child("Users").child(user.getUid()).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "Sucessfully Updated token !", Toast.LENGTH_SHORT).show();
                                Log.d("Token", token);
                            }
                        });
                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("TAG", token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("pushNotifications");
        super.onDestroy();
    }


}
