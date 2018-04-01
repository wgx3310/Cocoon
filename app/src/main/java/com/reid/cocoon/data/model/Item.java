package com.reid.cocoon.data.model;

import java.io.Serializable;

public class Item implements Serializable {
    public String id;
    public String title;
    public String subtitle;
    public String url;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
