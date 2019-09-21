package com.example.company.geobasednotifications.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.company.geobasednotifications.utils.App;
import com.example.company.geobasednotifications.view.MapsActivityView;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MapsActivityViewModel extends ViewModel implements LocationListener {

    private LocationManager locationManager;
    private String gpsProvider;
    private String netProvider;
    private static final int FIVE_SECONDS = 1000 * 5;
    private static final int ONE_MINUTE = 1000 * 60;
    MapsActivityView mapsActivityView;

    public MapsActivityViewModel() {
        Context context = App.getAppContext();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        gpsProvider = LocationManager.GPS_PROVIDER;
        netProvider = LocationManager.NETWORK_PROVIDER;
    }


    public void init(MapsActivityView mapsActivityView) {
        this.mapsActivityView = mapsActivityView;
    }

    @SuppressLint("MissingPermission")
    public void getBestLastKnownLocation() {
        Location networkLocation = locationManager.getLastKnownLocation(netProvider);
        Location gpslocation = locationManager.getLastKnownLocation(gpsProvider);
        if (networkLocation == null && gpslocation == null) {
            mapsActivityView.showDialogSwitchOnGps();
            return;
        }
        if (gpslocation == null) {

            sendLocation(networkLocation);
            return;
        }
        if (networkLocation == null) {
            sendLocation(gpslocation);
            return;
        }
        if (netProviderEnabled() && gpsProviderEnabled()) {
            long old = System.currentTimeMillis() - ONE_MINUTE;
            boolean gpsIsOld = (gpslocation.getTime() < old);
            boolean networkIsOld = (networkLocation.getTime() < old);
            if (!gpsIsOld) {
                sendLocation(gpslocation);
                return;
            } else if (!networkIsOld) {
                sendLocation(networkLocation);
                return;
            }
            if (gpslocation.getTime() > networkLocation.getTime()) {
                sendLocation(gpslocation);
            } else {
                sendLocation(networkLocation);
            }
        } else if (netProviderEnabled() && !gpsProviderEnabled()) {
            sendLocation(networkLocation);
        } else if (!netProviderEnabled() && gpsProviderEnabled()) {
            sendLocation(gpslocation);
        } else {
            mapsActivityView.showDialogSwitchOnGps();
        }
    }

    private void sendLocation(Location location) {
        mapsActivityView.setLastKnownLocation(location);
    }

    @SuppressLint("MissingPermission")
    public void startListenLocationUpdates() {
        if (netProviderEnabled()) {
            locationManager.requestLocationUpdates(netProvider, FIVE_SECONDS, 1, this);
        } else if (!netProviderEnabled() && gpsProviderEnabled()) {
            locationManager.requestLocationUpdates(gpsProvider, FIVE_SECONDS, 1, this);
        } else {
            mapsActivityView.showDialogSwitchOnGps();
        }
    }

    public void stopListenLocationUpdates() {
        locationManager.removeUpdates(this);
    }

    private boolean netProviderEnabled() {
        return locationManager.isProviderEnabled(netProvider);
    }

    private boolean gpsProviderEnabled() {
        return locationManager.isProviderEnabled(gpsProvider);
    }

    public LatLng getLatLngFromMarker(Marker marker) {
        if (marker != null) {
            return new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        } else {
            return new LatLng(0.0, 0.0);
        }
    }

    public LatLng getLatLngFromLocation(Location location) {
        if (location != null) {
            return new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            return new LatLng(0.0, 0.0);
        }
    }

    public boolean isMarkerInsideCircle(Marker marker, Circle circle) {
        float[] distance = new float[2];
        boolean result;
        Location.distanceBetween(marker.getPosition().latitude, marker.getPosition().longitude,
                circle.getCenter().latitude, circle.getCenter().longitude, distance);
        result = !(distance[0] > circle.getRadius());
        return result;
    }

    /**
     * LocationListener methods
     **/

    @Override
    public void onLocationChanged(Location location) {
        mapsActivityView.locationChanged(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        locationManager.removeUpdates(this);
        startListenLocationUpdates();
    }

    @Override
    public void onProviderDisabled(String s) {
        locationManager.removeUpdates(this);
        startListenLocationUpdates();
    }
}
