package com.reid.cocoon.data.bean;

import java.io.Serializable;

public class ProfileImage implements Serializable{
    public String small;
    public String medium;
    public String large;

    @Override
    public String toString() {
        return "ProfileImage{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                '}';
    }
}
