package com.company.agent;

import com.company.environement.Piece;
import com.company.utils.Position;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Agent extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<Position> queue;
    private Capteurs capteurs;
    private Effecteurs effecteurs;
    private int i;
    private int j;

    public Agent(Piece[][] manoir, ConcurrentLinkedQueue<Position> queue) {
        this.manoir = manoir;
        this.queue = queue;
        this.i = 0;
        this.j = 0;
    }

    public void run() {
        while(amIAlive()){
//            ObserveEnvironmentWithAllMySensors() // capteur
//            UpdateMyState()
//            ChooseAnAction()
//            justDoIt() // effecteur
        }
    }

    private Boolean amIAlive() {
        return true;
    }

}
