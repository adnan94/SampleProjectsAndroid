package com.example.adnanahmed.butterknifewithkotlin;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.iv)
    ImageView iv;
    @BindColor(R.color.redd)
    int red;
    @BindDrawable(R.mipmap.football)
    Drawable football;
    @BindDimen(R.dimen.height_imagee)
    float height;
    @BindString(R.string.name)
    String name;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.showFragment)
    Button showFragment;


    @OnClick(R.id.button2)
    void editText() {
        title.setText("Vote ko izzat doo !");
    }

    @OnClick(R.id.button3)
    void showPicture() {
        setPic();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setTextSize(height);
            }
        });
        showFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.root, new BlankFragment(), "").commit();
            }
        });

    }

    public void setPic() {
        iv.setImageDrawable(football);
    }
}