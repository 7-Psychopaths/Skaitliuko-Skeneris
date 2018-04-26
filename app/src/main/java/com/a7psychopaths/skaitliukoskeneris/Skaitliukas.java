package com.a7psychopaths.skaitliukoskeneris;

public abstract class Skaitliukas {

    private int id;
    private double value;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    Skaitliukas(){

    }

}
