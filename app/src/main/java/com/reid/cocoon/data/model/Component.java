package com.reid.cocoon.data.model;

import java.io.Serializable;

public class Component implements Serializable {

    public Photo photo;

    public int type;

    @Override
    public String toString() {
        return "Component{" +
                "photo=" + photo +
                ", type='" + type + '\'' +
                '}';
    }
}
