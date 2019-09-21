package com.example.company.geobasednotifications.view;

import android.location.Location;


public interface MapsActivityView   {
    void setLastKnownLocation(Location lastKnownLocation);
    void showDialogSwitchOnGps();
    void locationChanged(Location currentBestLocation);
}
