package com.tilismtech.demoeasypermissions;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private int REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQr();
            }
        });
    }


    public void scanQr() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
                EasyPermissions.requestPermissions(this, getString(R.string.camera_perm_for_qr), REQUEST_CAMERA, Manifest.permission.CAMERA);
            }
        }
        openQrScanActivity();
    }

    private void openQrScanActivity() {
        Toast.makeText(MainActivity.this,"Opening Camera Activity", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        openQrScanActivity();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            permissionDialog(getResources().getString(R.string.scan_qr_code), getResources().getString(R.string.camera_perm_for_qr));
        } else {
            Toast.makeText(MainActivity.this, R.string.camera_perm_for_qr, Toast.LENGTH_SHORT).show();
        }
    }

    private void permissionDialog(String title, String body) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(body);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }
    }
}
