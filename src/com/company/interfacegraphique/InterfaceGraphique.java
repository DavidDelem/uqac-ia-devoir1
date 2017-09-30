package com.company.interfacegraphique;

import com.company.environement.Piece;
import com.company.utils.Position;
import com.company.utils.UpdateInterfaceEvent;

import java.util.concurrent.ConcurrentLinkedQueue;

public class InterfaceGraphique extends Thread {

    private Piece[][] manoir;
    private Fenetre fenetre;
    private ConcurrentLinkedQueue<UpdateInterfaceEvent> queue;

    public InterfaceGraphique(Piece[][] manoir, ConcurrentLinkedQueue<UpdateInterfaceEvent> queue) {
        this.manoir = manoir;
        this.queue = queue;
        fenetre = new Fenetre();
    }

    public void run() {
        UpdateInterfaceEvent updateInterfaceEvent;
        int i, j;

        while(true) {
            if((updateInterfaceEvent = queue.poll()) != null) {

                i = updateInterfaceEvent.getPosition().getI();
                j = updateInterfaceEvent.getPosition().getJ();

                switch (updateInterfaceEvent.getTypeEvent()) {
                    case "updateContenuPiece":
                        fenetre.updatePiece(i, j, manoir[i][j].getDirt(), manoir[i][j].getJewel());
                        break;
                    case "updatePositionRobot":
                        fenetre.updatePositionRobot(i, j);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
