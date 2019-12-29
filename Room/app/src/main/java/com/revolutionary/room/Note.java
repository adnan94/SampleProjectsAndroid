package com.revolutionary.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class Note {



    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    @NonNull
    public String getCountry() {
        return country;
    }

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "note")
    private String mNote;

    @NonNull
    @ColumnInfo(name = "city")
    private String city;

    @NonNull
    @ColumnInfo(name = "country")
    private String country;




    public Note(@NonNull String id, @NonNull String mNote, @NonNull String city, @NonNull String country) {
        this.id = id;
        this.mNote = mNote;
        this.city = city;
        this.country = country;
    }
}
