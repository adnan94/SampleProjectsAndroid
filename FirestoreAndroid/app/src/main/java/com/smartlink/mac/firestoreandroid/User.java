package com.smartlink.mac.firestoreandroid;

import java.util.ArrayList;

public class User {
    int born;
    String last;
    String first;
    ArrayList<String> list;

    public User(int born, String last, String first) {
        this.born = born;
        this.last = last;
        this.first = first;
    }

    public User(int born, String last, String first, ArrayList<String> list) {
        this.born = born;
        this.last = last;
        this.first = first;
        this.list = list;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public User() {
    }
}
