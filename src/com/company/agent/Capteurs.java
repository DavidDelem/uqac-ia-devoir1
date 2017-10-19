package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;
import com.company.utils.SharedDatas;

import java.util.ArrayList;
import java.util.List;

/**
 Capteurs de l'agent aspirateur
 */

public class Capteurs {

    private Piece[][] manoir;
    private List<Position> positionsDirtsList;
    private List<Position> positionsJewelsList;

    public Capteurs(SharedDatas sharedDatas) {
        this.manoir = sharedDatas.manoir;
        this.positionsDirtsList = new ArrayList<>();
        this.positionsJewelsList = new ArrayList<>();
    }

    /* Détection des poussières */

    public void detectDirts() {
        this.positionsDirtsList.clear();

        /* Détection des nouvelles positions des poussières */
        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j] != null && manoir[i][j].getDirt()) {
                    this.positionsDirtsList.add(new Position(i,j));
                }
            }
        }
    }

    /* Détection des bijoux */

    public void detectJewels() {
        this.positionsJewelsList.clear();
        /* Détection des nouvelles positions des bijoux */
        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j] != null && manoir[i][j].getJewel()) {
                    this.positionsJewelsList.add(new Position(i,j));
                }
            }
        }
    }

    public List<Position> getPositionsDirtsList() {
        return positionsDirtsList;
    }

    public List<Position> getPositionsJewelsList() {
        return positionsJewelsList;
    }
}
