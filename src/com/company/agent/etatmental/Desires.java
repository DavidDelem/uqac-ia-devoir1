package com.company.agent.etatmental;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.List;

/**
 Désirs de l'agent aspirateur
 */

public class Desires {

    public Desires() {

    }

    /* Désir: Je veux que le manoir soit totalement propre et sans bijoux */

    public Boolean iWantATotallyCleanMansion(List<Position> positionsDirtsList, List<Position> positionsJewelsList) {

        Boolean mansionIsClean = false;

        if(positionsDirtsList.isEmpty() && positionsJewelsList.isEmpty()) {
            mansionIsClean = true;
        }

        return mansionIsClean;
    }

}
