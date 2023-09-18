package com.example.myapplication

import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


//import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity(), EasyPermissions.RationaleCallbacks,EasyPermissions.PermissionCallbacks {

    var rationalShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myapplication.R.layout.activity_main)
//
        val perms =
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
//
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
        } else {

//            Request 1
            // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(
//                this, "Required Permission",
//                0, *perms
//            )

//            Request 2
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, 0, *perms)
                    .setRationale("Location Request for konnect app")
                    .setPositiveButtonText("ok")
                    .setNegativeButtonText("Cancel")
                    .build()
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults!!)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

        var deniedForever = false;

        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                //denied
                if(rationalShown)
                Log.d("Adnan", "Denied")
                else
                    Log.d("Adnan", "First time Denied")

            } else {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    //allowed
                    Log.d("Adnan", "Granted")
                } else {
                    //set to never ask again
                    Log.d("Adnan", "Set to never asked again")
                    deniedForever = true;
                }
            }
            if (deniedForever) {
                //show custom dialog and on click that dialog go to settings
                Log.d("Adnan", "Denied forever")
            }
        }


    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d("Adnan", "Rational Accepted")
        rationalShown = true
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d("Adnan", "Rational Denied")
    }
}