package com.reid.cocoon.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Collection implements Serializable {
    public String id;
    public String title;
    public String description;

    @SerializedName("published_at")
    public String publishedAt;

    @SerializedName("updated_at")
    public String updatedAt;
    public boolean curated;

    @SerializedName("cover_photo")
    public Photo coverPhoto;
    public User user;
    public Links links;
    @SerializedName("total_photos")
    public int totalPhotos;

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", curated=" + curated +
                ", coverPhoto=" + coverPhoto +
                ", user=" + user +
                ", links=" + links +
                ", totalPhotos=" + totalPhotos +
                '}';
    }
}
