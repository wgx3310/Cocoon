package com.reid.cocoon.data.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{
    public List<Component> components;

    @Override
    public String toString() {
        return "Data{" +
                "components=" + components +
                '}';
    }
}
