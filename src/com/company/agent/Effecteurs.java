package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.*;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 Effecteurs de l'agent aspirateur
 */

public class Effecteurs {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<UpdateNbPointsEvent> updateNbPointsQueue;

    public Effecteurs(SharedDatas sharedDatas) {
        this.manoir = sharedDatas.manoir;
        this.updateNbPointsQueue = sharedDatas.updateNbPointsQueue;
    }

    /* Réalisation de l'action demandée */

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

    /* Nettoyage de la poussière  */

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

    /* Récupération des bijoux  */

    public void collectJewels(Position position) {
        if(manoir[position.getI()][position.getJ()].getJewel()) {
            manoir[position.getI()][position.getJ()].setJewel(false);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(20));
        }
    }

    /* Déplacement du robot vers la gauche  */

    public Position deplacementGauche(Position position) {
        Position newPosition = position;
        if (position.getI()-1 >= 0) {
            newPosition.setI(position.getI()-1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    /* Déplacement du robot vers la droite  */

    public Position deplacementDroite(Position position) {
        Position newPosition = position;
        if (position.getI()+1 < 10) {
            newPosition.setI(position.getI()+1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    /* Déplacement du robot vers le haut */

    public Position deplacementHaut(Position position) {
        Position newPosition = position;
        if (position.getJ()-1 >= 0) {
            newPosition.setJ(position.getJ()-1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }

    /* Déplacement du robot vers le bas  */

    public Position deplacementBas(Position position) {
        Position newPosition = position;
        if (position.getJ()+1 < 10) {
            newPosition.setJ(position.getJ()+1);
            updateNbPointsQueue.add(new UpdateNbPointsEvent(-1));
        }
        return newPosition;
    }
}
