package com.smartlink.mac.demomvp.view.Activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartlink.mac.demomvp.model.LoginPresenterImplementation;
import com.smartlink.mac.demomvp.presenter.LoginPresenter;
import com.smartlink.mac.demomvp.R;
import com.smartlink.mac.demomvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.button)
    Button login;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPresenter = new LoginPresenterImplementation(this);
        ButterKnife.bind(this);
        clicks();
    }


    public void clicks() {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
