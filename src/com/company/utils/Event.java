package com.company.utils;

public class Event {

    private String type;
    private int i;
    private int j;

    public Event(String type, int i, int j) {
        this.type = type;
        this.i = i;
        this.j = j;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
