package com.example.moprog_finalproject;

public class Article {
    String title;
    String desc;
    int img;

    public Article(String title, String desc, int img) {
        this.title = title;
        this.desc = desc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }
}
