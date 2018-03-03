package com.reid.cocoon.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Category implements Serializable {
    public int id;
    public String title;
    @SerializedName("photo_count")
    public int photoCount;
    public Links links;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", photoCount=" + photoCount +
                ", links=" + links +
                '}';
    }
}
