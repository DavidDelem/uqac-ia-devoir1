package com.company.agent;

import com.company.agent.etatmental.EtatMental;
import com.company.environement.Piece;
import com.company.utils.Action;
import com.company.utils.Position;
import com.company.utils.UpdateInterfaceEvent;

import java.util.ArrayList;
import java.util.List;
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
        List<Action> actionList = new ArrayList<Action>();

        for(Action action: actionList) {

            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.nbPoints--;

            switch (action) {
                case BAS:
                    position = effecteurs.deplacementBas(position);
                    queue.add(new UpdateInterfaceEvent(position, "updatePositionRobot"));
                    break;
                case HAUT:
                    position = effecteurs.deplacementHaut(position);
                    queue.add(new UpdateInterfaceEvent(position, "updatePositionRobot"));
                    break;
                case DROITE:
                    position = effecteurs.deplacementDroite(position);
                    queue.add(new UpdateInterfaceEvent(position, "updatePositionRobot"));
                    break;
                case GAUCHE:
                    position = effecteurs.deplacementGauche(position);
                    queue.add(new UpdateInterfaceEvent(position, "updatePositionRobot"));
                    break;
                case NETTOYER:
                    effecteurs.cleanDirt(position);
                    queue.add(new UpdateInterfaceEvent(position, "updateContenuPiece"));
                    break;
                case RAMASSER:
                    effecteurs.collectJewels(position);
                    queue.add(new UpdateInterfaceEvent(position, "updateContenuPiece"));
                    break;
                default:
                    break;
            }
        }
    }

}
