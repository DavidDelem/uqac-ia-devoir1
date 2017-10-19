package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.*;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Effecteurs {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<UpdateNbPointsEvent> updateNbPointsQueue;
    private ConcurrentLinkedQueue<UpdateInterfaceEvent> updateInterfaceQueue;

    public Effecteurs(SharedDatas sharedDatas) {
        this.manoir = sharedDatas.manoir;
        this.updateNbPointsQueue = sharedDatas.updateNbPointsQueue;
        this.updateInterfaceQueue = sharedDatas.updateInterfaceQueue;
    }

    public Position doAnAction(Action action, Position position) {
        switch (action) {
            case BAS:
                position = deplacementBas(position);
                break;
            case HAUT:
                position = deplacementHaut(position);
                break;
            case DROITE:
                position = deplacementDroite(position);
                break;
            case GAUCHE:
                position = deplacementGauche(position);
                break;
            case NETTOYER:
                cleanDirt(position);
                break;
            case RAMASSER:
                collectJewels(position);
                break;
            default:
                break;
        }
        return position;
    }

    public void cleanDirt(Position position) {
        if(manoir[position.getI()][position.getJ()].getDirt()) {
            manoir[position.getI()][position.getJ()].setDirt(false);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(10));
        }
        if(manoir[position.getI()][position.getJ()].getJewel()) {
            manoir[position.getI()][position.getJ()].setJewel(false);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-20));
        }
    }

    public void collectJewels(Position position) {
        if(manoir[position.getI()][position.getJ()].getJewel()) {
            manoir[position.getI()][position.getJ()].setJewel(false);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(20));
        }
    }

    public Position deplacementGauche(Position position) {
        Position newPosition = position;
        if (position.getI()-1 >= 0) {
            newPosition.setI(position.getI()-1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    public Position deplacementDroite(Position position) {
        Position newPosition = position;
        if (position.getI()+1 < 10) {
            newPosition.setI(position.getI()+1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    public Position deplacementHaut(Position position) {
        Position newPosition = position;
        if (position.getJ()-1 >= 0) {
            newPosition.setJ(position.getJ()-1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    public Position deplacementBas(Position position) {
        Position newPosition = position;
        if (position.getJ()+1 < 10) {
            newPosition.setJ(position.getJ()+1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }
}
