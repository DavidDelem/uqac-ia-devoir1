package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Capteurs {

    private Piece[][] manoir;

    public Capteurs(Piece[][] manoir) {
        this.manoir = manoir;
    }

    public List<Position> detectDirt() {

        List<Position> positionsDirt = new ArrayList<>();

        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j].getDirt()) {
                    positionsDirt.add(new Position(i,j));
                }
            }
        }
        return positionsDirt;
    }

    public List<Position> detectJewels() {

        List<Position> positionsJewels = new ArrayList<>();

        for (int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j].getDirt()) {
                    positionsJewels.add(new Position(i,j));
                }
            }
        }
        return positionsJewels;
    }
}
