package com.revolutionary.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class User extends BaseObservable {

    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        notifyChange();
    }
}
