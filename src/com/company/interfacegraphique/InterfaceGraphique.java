package com.company.interfacegraphique;

import com.company.environement.Piece;
import com.company.utils.Event;

import java.util.concurrent.ConcurrentLinkedQueue;

public class InterfaceGraphique extends Thread {

    private Piece[][] manoir;
    private Fenetre fenetre;
    private ConcurrentLinkedQueue<Event> queue;

    public InterfaceGraphique(Piece[][] manoir, ConcurrentLinkedQueue<Event> queue) {
        this.manoir = manoir;
        this.queue = queue;
        fenetre = new Fenetre();
    }

    public void run() {
        Event event;

        while(true) {
            if((event = queue.poll()) != null) {
                if(event.getType() == "dirt") {
                    fenetre.updatePiece(event.getI(), event.getJ(), true, false);
                } else if(event.getType() == "jewels") {
                    fenetre.updatePiece(event.getI(), event.getJ(), false, true);
                }
            }
        }
    }
}
