package com.tilismtech.kaisanapp.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tilismtech.kaisanapp.R;

import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_SCAN_QR = 10000, REQUEST_CAMERA = 1000;
    ImageView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animate();
        scanQr();
    }

    private void animate() {

        layout = (ImageView)findViewById(R.id.iv);
        Animation zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        layout.startAnimation(zoom);
    }


    public void scanQr() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
                EasyPermissions.requestPermissions(this, getString(R.string.camera_perm_for_qr), REQUEST_CAMERA, Manifest.permission.CAMERA);
            }else{
                startDeley();
            }
        }
    }


    public void openQrScanActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), QRActivity.class);
        startActivityForResult(intent, REQUEST_SCAN_QR);
    }


    public void startDeley(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openQrScanActivity();
            }
        },3500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
            startDeley();
        }
    }

}
