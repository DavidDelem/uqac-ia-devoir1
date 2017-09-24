package com.company.interfacegraphique;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.concurrent.ConcurrentLinkedQueue;

public class InterfaceGraphique extends Thread {

    private Piece[][] manoir;
    private Fenetre fenetre;
    private ConcurrentLinkedQueue<Position> queue;

    public InterfaceGraphique(Piece[][] manoir, ConcurrentLinkedQueue<Position> queue) {
        this.manoir = manoir;
        this.queue = queue;
        fenetre = new Fenetre();
    }

    public void run() {
        Position position;

        while(true) {
            if((position = queue.poll()) != null) {
                int i = position.getI();
                int j = position.getJ();
                fenetre.updatePiece(i, j, manoir[i][j].getDirt(), manoir[i][j].getJewel());
            }
        }
    }
}
