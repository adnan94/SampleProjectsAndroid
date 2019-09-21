package com.example.company.geobasednotifications.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.company.geobasednotifications.R;

public class DialogHelper {

    public static void buildAlertMessageNoGps(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.switch_on_gps_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, (dialog, id) ->
                {
                    activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    dialog.dismiss();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static Snackbar getSnack(String text, Snackbar snackbar, FrameLayout rootLayout, Context context) {
        if (snackbar == null) {
            snackbar = Snackbar.make(rootLayout, text, Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen));
            TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextSize(14);
            tv.setAllCaps(false);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.white));
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        } else {
            snackbar.setText(text);
        }
        return snackbar;
    }
}
