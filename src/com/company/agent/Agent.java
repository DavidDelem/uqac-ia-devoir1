package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Agent extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<Position> queue;
    private Capteurs capteurs;
    private Effecteurs effecteurs;
    private int i;
    private int j;
    private int nbPoints;

    public Agent(Piece[][] manoir, ConcurrentLinkedQueue<Position> queue) {
        this.manoir = manoir;
        this.queue = queue;
        this.i = 0;
        this.j = 0;

        capteurs = new Capteurs(manoir);
        effecteurs = new Effecteurs(manoir);
    }

    public void run() {
        while(amIAlive()){
//            ObserveEnvironmentWithAllMySensors() // capteur
//            UpdateMyState()
//            ChooseAnAction()
//            justDoIt() // effecteur

            try {
                TimeUnit.SECONDS.sleep(2);
                capteurs.detecterPoussieres();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean amIAlive() {
        return true;
    }

}
