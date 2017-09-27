package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Capteurs {

    private Piece[][] manoir;
    private List<Position> positionsDirtsList;
    private List<Position> positionsJewelsList;

    public Capteurs(Piece[][] manoir) {
        this.manoir = manoir;
        this.positionsDirtsList = new ArrayList<>();
        this.positionsJewelsList = new ArrayList<>();
    }

    public void detectDirts() {
        /* Suppression des anciennes positions des poussières */
        this.positionsDirtsList.clear();

        /* Ajout des nouvelles positions des poussières */
        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j].getDirt()) this.positionsDirtsList.add(new Position(i,j));
            }
        }
    }

    public void detectJewels() {
        /* Suppression des anciennes positions des bijoux */
        this.positionsJewelsList.clear();

        /* Ajout des nouvelles positions des bijoux */
        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j].getDirt()) this.positionsJewelsList.add(new Position(i,j));
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
