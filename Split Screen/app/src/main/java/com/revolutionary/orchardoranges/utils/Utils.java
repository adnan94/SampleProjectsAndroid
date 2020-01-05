package com.revolutionary.orchardoranges.utils; /**
 * Created by AdnanAhmed on 1/20/2018.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Keep;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.revolutionary.orchardoranges.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ali on 4/5/2017.
 */

@Keep
public class Utils {
    public static String name;
    public static String email;
    public static String address;
    public static String uid;
    public static boolean flagForRating = true, backstack = true, navBackstack = false;
    public static String type;
    public static String cnic;
    public static String cell;
    public static String status;
    public static String userImage;
    public static String password;
    private static Typeface typeface;
    public static boolean viewBookingVendor = true;
    public static Dialog dialog;





    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static void dismissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

//    public static void showProgressDialog(String email, Context context) {
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        View v = LayoutInflater.from(context).inflate(R.layout.alert_exit, null, false);
//        Button exit = (Button) v.findViewById(R.id.exit);
//        Button cancel = (Button) v.findViewById(R.id.cancel);
//        TextView number = (TextView) v.findViewById(R.id.number);
//        com.ijara.imechanic.Activities.Utils.relwayMedium(context, number);
//        com.ijara.imechanic.Activities.Utils.relwaySemiBold(context, cancel);
//        com.ijara.imechanic.Activities.Utils.relwaySemiBold(context, exit);
//        number.setText(email);
//        exit.setVisibility(View.GONE);
//        cancel.setVisibility(View.GONE);
//        alert.setView(v);
//        if (com.ijara.imechanic.Activities.Utils.type != null) {
//            dialog = alert.create();
//            exit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
//            cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//        }
//
//    }


    public static void alertToolTip(Context context, String m) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        final TextView message = new TextView(context);
        final SpannableString s =
                new SpannableString(m);
        Linkify.addLinks(s, Linkify.WEB_URLS);
        message.setPadding(10, 10, 10, 10);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        alertDialog.setView(message);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public static void alertToolTip(Context context, String m, int i) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(m);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public static String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedHours + "hr " + elapsedMinutes + "mins";
    }//        String time = diffInHours + "hr " + diffInMinutes +"mins";

    public static long hourDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedHours;
    }//        String time = diffInHours + "hr " + diffInMinutes +"mins";

    public static void setTypeFace(Context context, EditText editText) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        editText.setTypeface(typeface);
    }

    public static void setTypeFace(Context context, Button button) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-SemiBold.ttf");
        button.setTypeface(typeface);
    }

    public static void setTypeFace(Context context, CheckBox check) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        check.setTypeface(typeface);
    }

    public static void setTypeFace(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        textView.setTypeface(typeface);

    }


    public static void relwayRegular(Context context, Button textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Regular.otf");
        textView.setTypeface(typeface);

    }

    public static void relwayRegular(Context context, EditText textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Regular.otf");
        textView.setTypeface(typeface);

    }

    public static void relwayRegular(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Regular.otf");
        textView.setTypeface(typeface);

    }

    //
    public static void relwayMedium(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Medium.ttf");
        textView.setTypeface(typeface);

    }

    public static void relwayMedium(Context context, EditText textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Medium.ttf");
        textView.setTypeface(typeface);

    }

    public static void relwayMedium(Context context, Button textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Medium.ttf");
        textView.setTypeface(typeface);

    }


    public static void relwaySemiBold(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Semibold.otf");
        textView.setTypeface(typeface);

    }

    public static void relwaySemiBold(Context context, EditText textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Semibold.otf");
        textView.setTypeface(typeface);

    }

    public static void relwaySemiBold(Context context, Button textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Semibold.otf");
        textView.setTypeface(typeface);

    }


    public static void relwayLight(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Light.otf");
        textView.setTypeface(typeface);

    }

    public static void relwayLight(Context context, EditText textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Light.otf");
        textView.setTypeface(typeface);

    }

    public static void relwayLight(Context context, Button textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Light.otf");
        textView.setTypeface(typeface);

    }


    public static void relwayBold(Context context, TextView textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Bold.ttf");
        textView.setTypeface(typeface);

    }

    public static void relwayBold(Context context, EditText textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Bold.ttf");
        textView.setTypeface(typeface);

    }

    public static void relwayBold(Context context, Button textView) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Bold.ttf");
        textView.setTypeface(typeface);

    }


    public static void toast(String message, Context context) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

//    public static void applyFontToMenuItem(MenuItem mi, Context context) {
//        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Regular.ttf");
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
//    }

    public static void storePrefs(Context context, String duration, String distance, double lat, double lng) {
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
        spreferencesEditor.putString("duration", duration);
        spreferencesEditor.putString("distance", distance);
        spreferencesEditor.putString("lat", Double.toString(lat));
        spreferencesEditor.putString("lng", Double.toString(lng));
        spreferencesEditor.commit();
    }




    public static void snack(String message, Context context, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void log(String message) {
        Log.d("ABCD", message);
    }


    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public static void changeStatusNavColor(FragmentActivity context, String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (color.equalsIgnoreCase("normal")) {
                context.getWindow().setNavigationBarColor(ContextCompat.getColor(context, R.color.linen));
                context.getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorAccent));
            } else if (color.equalsIgnoreCase("orders")) {
                context.getWindow().setNavigationBarColor(ContextCompat.getColor(context, R.color.orders));
                context.getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.orders));
            }
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }


    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

        }
    }

    public static long getDate(int day, int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Long datee = calendar.getTime().getTime();
        return datee;
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static long getTime(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static String getDurationString(long seconds) {

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return hours + "hr " + minutes + "min ";
    }

    public static String formatDAte(Date date1) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = sdf.format(date1);
        return dateString;
    }

    public static String formatTime(long timeInMili) {
        String time;
        Date d = new Date(timeInMili);
        DateFormat formatter = new SimpleDateFormat("hh:mm:aa");
        time = formatter.format(d);
        return time;
    }

    public static String formatTime24(long timeInMili) {
        String time;
        Date d = new Date(timeInMili);
        DateFormat formatter = new SimpleDateFormat("HH:mm:aa");
        time = formatter.format(d);
        return time;
    }

    public static String formatDAteShort(Date date1) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String dateString = sdf.format(date1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date1.getTime());
        return dateString + " " + getMonthAttribute(calendar);
    }


    public static String getMonthAttribute(Calendar c) {
        int count = c.get(Calendar.MONTH);
        String month = "";
        switch (count) {
            case 0: {
                month = "Jan";
                break;
            }
            case 1: {
                month = "Feb";
                break;
            }
            case 2: {
                month = "Mar";
                break;
            }
            case 3: {
                month = "Apr";
                break;
            }
            case 4: {
                month = "May";
                break;
            }
            case 5: {
                month = "Jun";
                break;
            }
            case 6: {
                month = "Jul";
                break;
            }
            case 7: {
                month = "Aug";
                break;
            }
            case 8: {
                month = "Sep";
                break;
            }
            case 9: {
                month = "Oct";
                break;
            }
            case 10: {
                month = "Nov";
                break;
            }
            case 11: {
                month = "Dec";
                break;
            }
        }
        return month;
    }
}
