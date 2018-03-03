package com.reid.cocoon.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Exif implements Serializable {
//            "make": "Canon",
//            "model": "Canon EOS 40D",
//            "exposure_time": "0.011111111111111112",
//            "aperture": "4.970854",
//            "focal_length": "37",
//            "iso": 100

    public String make;
    public String model;

    @SerializedName("exposure_time")
    public String exposureTime;
    public String aperture;

    @SerializedName("focal_length")
    public String focalLength;
    public int ios;

    @Override
    public String toString() {
        return "Exif{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", exposureTime='" + exposureTime + '\'' +
                ", aperture='" + aperture + '\'' +
                ", focalLength='" + focalLength + '\'' +
                ", ios=" + ios +
                '}';
    }
}
