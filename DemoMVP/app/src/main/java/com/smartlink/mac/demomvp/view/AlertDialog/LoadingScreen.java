package com.smartlink.mac.demomvp.view.AlertDialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartlink.mac.demomvp.R;

public class LoadingScreen extends DialogFragment {

    public static LoadingScreen getInstance() {
        return new LoadingScreen();
    }

    TextView title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.LoadingScreenTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_screen, container, false);
        TextView text = (TextView) view.findViewById(R.id.textView);
        text.setText(getTag().toString());

        return view;
    }
}
