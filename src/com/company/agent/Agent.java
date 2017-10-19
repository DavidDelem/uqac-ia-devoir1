package com.company.agent;

import com.company.agent.etatmental.EtatMental;
import com.company.utils.*;

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

    /* Observation de l'environement grâce aux capteurs */

    private void observeEnvironmentWithAllMySensors() {
        capteurs.detectDirts();
        capteurs.detectJewels();
    }

    /* Mise à jours des croyances de l'agent */

    private void updateMyState() {
        etatMental.updateMyBeliefs(capteurs.getPositionsDirtsList(), capteurs.getPositionsJewelsList());
    }

    /* Choix de l'action: intentions déterminée par l'état mental BDI à partir des croyances et des désirs */

    private void chooseAnAction() {
        etatMental.updateMyIntentions(this.position);
    }

    /* Réalisation de l'action via les effecteurs */

    private void justDoIt() {

        int n = 5;

        UpdateNbPointsEvent updateNbPointsEvent;
        List<Action> actionList = etatMental.getIntentions().getActionsList();


        /* Réalisation des actions */
        for(Action action: actionList) {
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            position = effecteurs.doAnAction(action, position);

            if((updateNbPointsEvent = updateNbPointsQueue.poll()) != null) {
                this.nbPoints += updateNbPointsEvent.getNbPoints();
            }

            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null, "updatePositionRobot"));
            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null,"updateContenuPiece"));
            updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(this.nbPoints), "updateAffichageNbPoints"));
        }
    }

}
