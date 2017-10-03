package com.company.agent.etatmental;

import com.company.environement.Piece;

public class Desires {

    // Je veux que le manoir soit totalement propre et sans bijoux

    public Boolean iWantATotallyCleanMansion(Piece[][] manoir) {

        Boolean mansionIsClean = true;

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(manoir[i][j].getDirt() || manoir[i][j].getJewel()) {
                   mansionIsClean = false;
                   break;
                }
            }
        }
        return mansionIsClean;
    }

}
