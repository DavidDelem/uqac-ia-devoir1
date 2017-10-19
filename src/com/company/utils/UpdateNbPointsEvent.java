package com.company.utils;

public class UpdateNbPointsEvent {

    int nbPoints;

    public UpdateNbPointsEvent(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }
}
