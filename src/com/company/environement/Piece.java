package com.company.environement;

/**
 Description d'une piéce
 */

public class Piece {

    private Boolean dirt;
    private Boolean jewel;

    public Piece() {
        this.dirt = false;
        this.jewel = false;
    }

    public Boolean getDirt() {
        return dirt;
    }

    public void setDirt(Boolean dirt) {
        this.dirt = dirt;
    }

    public Boolean getJewel() {
        return jewel;
    }

    public void setJewel(Boolean jewel) {
        this.jewel = jewel;
    }
}
