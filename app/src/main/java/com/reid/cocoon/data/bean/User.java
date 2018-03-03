package com.reid.cocoon.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable{
    public String id;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("username")
    public String userName;
    public String name;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("twitter_username")
    public String twitterUserName;

    @SerializedName("portfolio_url")
    public String portfolioUrl;

    public String bio;

    public String location;

    public Links links;

    @SerializedName("profile_image")
    public ProfileImage profileImage;

    @SerializedName("total_likes")
    public int totalLikes;

    @SerializedName("total_photos")
    public int totalPhotos;

    @SerializedName("total_collections")
    public int totalCollections;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", twitterUserName='" + twitterUserName + '\'' +
                ", portfolioUrl='" + portfolioUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", links=" + links +
                ", profileImage=" + profileImage +
                ", totalLikes=" + totalLikes +
                ", totalPhotos=" + totalPhotos +
                ", totalCollections=" + totalCollections +
                '}';
    }
}
