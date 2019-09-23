package com.example.mvvmwithdatabinding.viewmodel;

import com.example.mvvmwithdatabinding.model.News;

import java.util.ArrayList;

public class NewsModel {

    private String title;
    private String desc;
    private News news;

    public NewsModel() {
    }

    public NewsModel(News news) {
        this.title = news.title;
        this.desc = news.desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<NewsModel> getNewsModelArray(){

        ArrayList<NewsModel> newsModels= new ArrayList<>();
        newsModels.add(new NewsModel(new News("First","this is news")));
        newsModels.add(new NewsModel(new News("Second","this is news")));
        newsModels.add(new NewsModel(new News("Third","this is news")));
        newsModels.add(new NewsModel(new News("Fourth","this is news")));
        newsModels.add(new NewsModel(new News("Fifth","this is news")));

     return newsModels;
    }
}
