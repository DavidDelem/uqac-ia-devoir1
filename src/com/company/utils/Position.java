package com.company.utils;

/**
 Définit une position à partir de coordonnées i et j
 */

public class Position {

    private int i;
    private int j;

    public Position(int i, int j) {
        this.i = i; // Rows
        this.j = j; // Columns
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
