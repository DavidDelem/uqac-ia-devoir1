package com.company.agent;

import com.company.agent.etatmental.EtatMental;
import com.company.environement.Piece;
import com.company.utils.Position;
import com.company.utils.UpdateInterfaceEvent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Agent extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<UpdateInterfaceEvent> queue;

    private EtatMental etatMental;
    private Capteurs capteurs;
    private Effecteurs effecteurs;
    private Position position;
    private int nbPoints;

    public Agent(Piece[][] manoir, ConcurrentLinkedQueue<UpdateInterfaceEvent> queue) {
        this.manoir = manoir;
        this.queue = queue;
        this.position = new Position(0,0);

        etatMental = new EtatMental();
        capteurs = new Capteurs(manoir);
        effecteurs = new Effecteurs(manoir);
    }

    public void run() {

//        try {
//            TimeUnit.SECONDS.sleep(2);
//            //capteurs.detecterPoussieres();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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
        // Utilisation des CAPTEURS
        capteurs.detectDirts();
        capteurs.detectJewels();
    }

    private void updateMyState() {
        // Mise à jours des BELIEFS de l'état mental BDI
        etatMental.updateMyBeliefs(capteurs.getPositionsDirtsList(), capteurs.getPositionsJewelsList());
    }

    private void chooseAnAction() {
        // INTENTION déterminée par l'état mental BDI à partir des BELIEFS et des DESIRES
    }

    private void justDoIt() {
        // Utilisation des EFFECTEURS
        position = effecteurs.deplacementBas(position);
        queue.add(new UpdateInterfaceEvent(position, "updatePositionRobot"));
    }

}
