package com.company.agent;

import com.company.agent.etatmental.EtatMental;
import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Agent extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<Position> queue;

    private EtatMental etatMental;
    private Capteurs capteurs;
    private Effecteurs effecteurs;
    private Position position;
    private int nbPoints;

    public Agent(Piece[][] manoir, ConcurrentLinkedQueue<Position> queue) {
        this.manoir = manoir;
        this.queue = queue;
        this.position = new Position(0,0);

        etatMental = new EtatMental();
        capteurs = new Capteurs(manoir);
        effecteurs = new Effecteurs(manoir);
    }

    public void run() {
        while(amIAlive()){
            observeEnvironmentWithAllMySensors();
            updateMyState();
            chooseAnAction();
            justDoIt(); // effecteur

            try {
                TimeUnit.SECONDS.sleep(2);
                //capteurs.detecterPoussieres();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean amIAlive() {
        return true;
    }

    private void observeEnvironmentWithAllMySensors() {
        capteurs.detectDirts();
        capteurs.detectJewels();
    }

    private void updateMyState() {
        etatMental.updateMyBeliefs(capteurs.getPositionsDirtsList(), capteurs.getPositionsJewelsList());
    }

    private void chooseAnAction() {
        //effecteurs.
    }

    private void justDoIt() {
        //effecteurs.
    }

}
