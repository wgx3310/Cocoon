package com.reid.cocoon.data.model;

import java.io.Serializable;
import java.util.List;

public class Stats implements Serializable{

    public String id;
    public Stub downloads;
    public Stub views;
    public Stub likes;

    @Override
    public String toString() {
        return "Stats{" +
                "id='" + id + '\'' +
                ", downloads=" + downloads +
                ", views=" + views +
                ", likes=" + likes +
                '}';
    }

    public static class Stub implements Serializable{
        public int total;
        public Historical historical;

        @Override
        public String toString() {
            return "Stub{" +
                    "total=" + total +
                    ", historical=" + historical +
                    '}';
        }
    }

    public static class Historical implements Serializable{
        public int change;
        public String resolution;
        public int quantity;
        public List<Value> values;

        @Override
        public String toString() {
            return "Historical{" +
                    "change=" + change +
                    ", resolution='" + resolution + '\'' +
                    ", quantity=" + quantity +
                    ", values=" + values +
                    '}';
        }
    }

    public static class Value implements Serializable{
        public String date;
        public int value;

        @Override
        public String toString() {
            return "Value{" +
                    "date='" + date + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
