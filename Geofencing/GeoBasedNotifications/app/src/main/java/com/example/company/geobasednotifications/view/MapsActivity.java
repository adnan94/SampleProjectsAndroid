package com.example.company.geobasednotifications.view;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.company.geobasednotifications.R;
import com.example.company.geobasednotifications.viewmodel.MapsActivityViewModel;
import com.example.company.geobasednotifications.view.fragments.ChooseRadiusDialog;
import com.example.company.geobasednotifications.utils.DialogHelper;
import com.example.company.geobasednotifications.utils.MapUiHelper;
import com.example.company.geobasednotifications.utils.NotificationHelper;
import com.example.company.geobasednotifications.utils.SharedPrefUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, MapsActivityView, GoogleMap.OnMarkerClickListener {

    @BindView(R.id.root_layout)
    FrameLayout root;

    private GoogleMap mMap;
    private Location lastKnownLocation;
    private Snackbar snackbar;
    private Circle circle;
    private Marker centerMarker;
    private Marker userMarker;
    MapsActivityViewModel presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        inits();
    }

    private void inits() {
        ButterKnife.bind(this);
        presenter = ViewModelProviders.of(this)
                .get(MapsActivityViewModel.class);
        presenter.init(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startListenForLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopListenLocationUpdates();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.setOnMarkerClickListener(this);
        checkForLocationCoords();
        snackbar = DialogHelper.getSnack(getString(R.string.add_your_marker), snackbar, root, getApplicationContext());
        addOnMapLongPress();

    }


    private void addOnMapLongPress() {
        mMap.setOnMapLongClickListener(latLng -> {
            if (latLng.equals(userMarker.getPosition())) {
                Toast.makeText(this, R.string.you_are_already_here, Toast.LENGTH_LONG).show();
            } else {
                if (circle != null) {
                    circle.remove();
                }

                centerMarker = MapUiHelper.getCenterMarker(mMap, centerMarker, latLng);
                snackbar = DialogHelper.getSnack(getString(R.string.add_your_circle), snackbar, root, getApplicationContext());
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(centerMarker)) {
            ChooseRadiusDialog dialog = ChooseRadiusDialog.newInstance(this::drawCircle);
            if (!dialog.isAdded()) {
                dialog.show(getSupportFragmentManager(), "");
            }
            snackbar = DialogHelper.getSnack(getString(R.string.tou_will_get_notification), snackbar, root, getApplicationContext());
            return true;
        } else return false;
    }

    private void drawCircle(String string) {
        double radius = Double.valueOf(string);
        LatLng latLng = presenter.getLatLngFromMarker(centerMarker);
        circle = MapUiHelper.getCircle(mMap, circle, latLng, radius);
        SharedPrefUtils.setMarkerIsInside(isMarkerInside());
    }

    private void checkForLocationCoords() {
        if (hasLocationPermissions()) {
            presenter.getBestLastKnownLocation();
        } else {
            askForPermissions();
        }
    }

    private void askForPermissions() {
        ActivityCompat.requestPermissions(MapsActivity.this,
                new String[]{
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION


                },
                1);
    }

    private boolean hasLocationPermissions() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return false;
        } else {
            return true;
        }
    }

    private void startListenForLocationUpdates() {
        if (hasLocationPermissions()) {
            presenter.startListenLocationUpdates();
        } else {
            askForPermissions();
        }
    }


    @Override
    public void showDialogSwitchOnGps() {
        DialogHelper.buildAlertMessageNoGps(this);
    }

    @Override
    public void setLastKnownLocation(Location lastLoc) {
        if (lastLoc != null) {
            this.lastKnownLocation = lastLoc;
            if (mMap != null) {
                animate(lastLoc);
            }
        }
    }

    @Override
    public void locationChanged(Location changedLoc) {
        lastKnownLocation = changedLoc;
        setUserMarker(lastKnownLocation);
        if (SharedPrefUtils.getMarkerIsInside() != isMarkerInside() && circle != null) {
            sendNotification();
            SharedPrefUtils.setMarkerIsInside(isMarkerInside());
        }
    }

    private void animate(Location location) {
        LatLng latLng = presenter.getLatLngFromLocation(location);
        userMarker = MapUiHelper.getUserMarker(mMap, userMarker, latLng);
    }

    private void setUserMarker(Location currentBestLocation) {
        LatLng latLng = presenter.getLatLngFromLocation(currentBestLocation);
        userMarker = MapUiHelper.getUserMarker(mMap, userMarker, latLng);
        MapUiHelper.moveCamers(mMap, latLng);
    }

    private boolean isMarkerInside() {
        return userMarker != null && circle != null && presenter.isMarkerInsideCircle(userMarker, circle);
    }

    private void sendNotification() {
        String title;
        String message;
        if (isMarkerInside()) {
            title = getString(R.string.title_you_are_inside);
            message = getString(R.string.message_you_are_inside);
        } else {
            title = getString(R.string.title_you_are_outside);
            message = getString(R.string.message_you_are_outside);
        }
        NotificationHelper.sendNotification(title, message);
        SharedPrefUtils.setMarkerIsInside(isMarkerInside());
    }

    @OnClick(R.id.find_my_loc)
    void click() {
        LatLng latLng = presenter.getLatLngFromMarker(userMarker);
        MapUiHelper.animateCamera(mMap, latLng);
    }
}
