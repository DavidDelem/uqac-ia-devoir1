package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;

public class Effecteurs {

    private Piece[][] manoir;

    public Effecteurs(Piece[][] manoir) {
        this.manoir = manoir;
    }

    //  execute l'action

    public void cleanDirt(Position position) {
        manoir[position.getI()][position.getJ()].setDirt(true);
    }

    public void collectJewels(Position position) {
        manoir[position.getI()][position.getJ()].setJewel(false);
    }

    public Position deplacementGauche(Position position) {
        Position newPosition = position;
        if (position.getI()-1 >= 0) {
            newPosition.setI(position.getI()-1);
        }
        return newPosition;
    }

    public Position deplacementDroite(Position position) {
        Position newPosition = position;
        if (position.getI()+1 < 10) {
            newPosition.setI(position.getI()+1);
        }
        return newPosition;
    }

    public Position deplacementHaut(Position position) {
        Position newPosition = position;
        if (position.getJ()-1 >= 0) {
            newPosition.setJ(position.getJ()-1);
        }
        return newPosition;
    }

    public Position deplacementBas(Position position) {
        Position newPosition = position;
        if (position.getJ()+1 < 10) {
            newPosition.setJ(position.getJ()+1);
        }
        return newPosition;
    }
}
