package com.reid.cocoon.data.bean;

import java.io.Serializable;


public class Location implements Serializable {
//            "city": "Montreal",
//            "country": "Canada",
//            "position": {
//                "latitude": 45.4732984,
//                "longitude": -73.6384879
//            }

    public String city;
    public String country;
    public Position position;

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", position=" + position +
                '}';
    }
}
