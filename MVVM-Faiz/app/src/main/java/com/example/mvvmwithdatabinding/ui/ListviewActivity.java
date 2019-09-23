package com.example.mvvmwithdatabinding.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mvvmwithdatabinding.R;
import com.example.mvvmwithdatabinding.adapter.NewsListAdapter;
import com.example.mvvmwithdatabinding.databinding.ActivityListviewBinding;
import com.example.mvvmwithdatabinding.databinding.ActivityMainBinding;
import com.example.mvvmwithdatabinding.viewmodel.NewsModel;

import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {

    ActivityListviewBinding activityListviewBinding;
    private NewsModel newsModel;
    private ArrayList<NewsModel> newsModelArrayList;
    private NewsListAdapter newsListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_listview);

        activityListviewBinding = DataBindingUtil.setContentView(this,R.layout.activity_listview);
        newsModel = new NewsModel();
        newsModelArrayList = newsModel.getNewsModelArray();

        newsListAdapter = new NewsListAdapter(ListviewActivity.this,newsModelArrayList);
        activityListviewBinding.listview.setAdapter(newsListAdapter);
    }
}
