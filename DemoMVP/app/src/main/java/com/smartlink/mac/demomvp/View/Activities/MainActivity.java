package com.smartlink.mac.demomvp.View.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.smartlink.mac.demomvp.Model.LoginPresenterImplementation;
import com.smartlink.mac.demomvp.Presenter.LoginPresenter;
import com.smartlink.mac.demomvp.R;
import com.smartlink.mac.demomvp.View.LoginView;


public class MainActivity extends AppCompatActivity implements LoginView {

    EditText email, password;
    Button login;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casts();
        loginPresenter = new LoginPresenterImplementation(this);
//        ButterKnife.bind(this);

    }


    public void casts() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signIn(email.getText().toString(), password.getText().toString(),getSupportFragmentManager());
            }
        });

    }

    @Override
    public void onError() {
        Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSucess() {
        Toast.makeText(MainActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
        loginPresenter.loginSucess(email.getText().toString(), password.getText().toString(),MainActivity.this);
        startActivity(new Intent(MainActivity.this, HomeScreen.class));
        finish();
    }

    @Override
    public void validationError() {
        Toast.makeText(MainActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
    }
}
