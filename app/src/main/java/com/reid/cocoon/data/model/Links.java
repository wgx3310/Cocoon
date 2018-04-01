package com.reid.cocoon.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Links implements Serializable {
    public String self;
    public String html;
    public String photos;
    public String likes;
    public String portfolio;
    public String following;
    public String followers;
    public String download;

    @SerializedName("download_location")
    public String downloadLoaction;

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                ", html='" + html + '\'' +
                ", photos='" + photos + '\'' +
                ", likes='" + likes + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", following='" + following + '\'' +
                ", followers='" + followers + '\'' +
                ", download='" + download + '\'' +
                ", downloadLoaction='" + downloadLoaction + '\'' +
                '}';
    }
}
