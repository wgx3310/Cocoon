package com.reid.cocoon.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Photo implements Serializable{
    public String id;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;
    public int width;
    public int height;
    public String color;

    @SerializedName("description")
    public String desc;
    public Urls urls;
    public Links links;

    @SerializedName("liked_by_user")
    public boolean likedByUser;

    public int likes;
    public User user;
    public Exif exif;
    public Location location;
    public List<Category> categories;

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", desc='" + desc + '\'' +
                ", urls=" + urls +
                ", links=" + links +
                ", likedByUser=" + likedByUser +
                ", likes=" + likes +
                ", user=" + user +
                ", exif=" + exif +
                ", location=" + location +
                ", categories=" + categories +
                '}';
    }
}
