package com.reid.cocoon.data.bean;

import java.io.Serializable;


public class Position implements Serializable {

//                "latitude": 45.4732984,
//                "longitude": -73.6384879

    public float latitude;
    public float longitude;

    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
