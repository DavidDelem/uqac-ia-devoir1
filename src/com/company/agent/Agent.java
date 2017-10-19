package com.company.agent;

import com.company.agent.etatmental.EtatMental;
import com.company.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Agent extends Thread {

    private ConcurrentLinkedQueue<UpdateInterfaceEvent> updateInterfaceQueue;
    private ConcurrentLinkedQueue<UpdateNbPointsEvent> updateNbPointsQueue;

    private EtatMental etatMental;
    private Capteurs capteurs;
    private Effecteurs effecteurs;
    private Position position;
    private int nbPoints;

    public Agent(SharedDatas sharedDatas) {
        this.updateInterfaceQueue = sharedDatas.updateInterfaceQueue;
        this.updateNbPointsQueue = sharedDatas.updateNbPointsQueue;
        this.position = new Position(0,0);

        etatMental = new EtatMental();
        capteurs = new Capteurs(sharedDatas);
        effecteurs = new Effecteurs(sharedDatas);
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
            if (etatMental.getBeliefs().getpositionsDirtsList() != null) {
                if (!etatMental.getBeliefs().getpositionsDirtsList().isEmpty()) {
                    chooseAnAction();
                }
            }
            justDoIt(); // effecteur
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
        List<Position> path = etatMental.updateMyIntentions(this.position);
    }

    private void justDoIt() {
        // Utilisation des EFFECTEURS
        // On boucle sur la liste d'actions (haut bas droite gauche ramasser nettoyer) renvoyée par l'algo d'exploration

        // Liste d'actions a générer suite au résultat de l'exploration informée ou non informée
        // Test

        UpdateNbPointsEvent updateNbPointsEvent;
        List<Action> actionList = new ArrayList<Action>();

        for(Action action: actionList) {

            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            position = effecteurs.doAnAction(action, position);
            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null, "updatePositionRobot"));
            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null,"updateContenuPiece"));

            if((updateNbPointsEvent = updateNbPointsQueue.poll()) != null) {
                this.nbPoints += updateNbPointsEvent.getNbPoints();
            }

            updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(this.nbPoints), "updateAffichageNbPoints"));
        }
    }

}
