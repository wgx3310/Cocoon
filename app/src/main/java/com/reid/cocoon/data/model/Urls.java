package com.reid.cocoon.data.model;

import java.io.Serializable;

public class Urls implements Serializable {
    public String raw;
    public String full;
    public String regular;
    public String small;
    public String thumb;

    @Override
    public String toString() {
        return "Urls{" +
                "raw='" + raw + '\'' +
                ", full='" + full + '\'' +
                ", regular='" + regular + '\'' +
                ", small='" + small + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
