package com.company.agent;

import com.company.agent.apprentissage.FrequencesExploration;
import com.company.agent.apprentissage.ModuleApprentissage;
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
    private ModuleApprentissage moduleApprentissage;

    private Position position;
    private int nbPoints;

    public Agent(SharedDatas sharedDatas) {
        this.updateInterfaceQueue = sharedDatas.updateInterfaceQueue;
        this.updateNbPointsQueue = sharedDatas.updateNbPointsQueue;
        this.position = new Position(0,0);

        this.etatMental = new EtatMental();
        this.capteurs = new Capteurs(sharedDatas);
        this.effecteurs = new Effecteurs(sharedDatas);
        this.moduleApprentissage = new ModuleApprentissage();
    }

    public void run() {

        while(amIAlive()){
            observeEnvironmentWithAllMySensors();
            updateMyState();
            if (etatMental.getBeliefs().getpositionsDirtsList() != null || etatMental.getBeliefs().getpositionsJewelsList() != null) {
                if (!etatMental.getBeliefs().getpositionsDirtsList().isEmpty() || !etatMental.getBeliefs().getpositionsJewelsList().isEmpty()) {
                    chooseAnAction();
                }
            }
            justDoIt();

            /* Apprentissage */

//            moduleApprentissage.incrementeNbIterationTotales();
//
//            if(moduleApprentissage.getNbIterationsMax() == moduleApprentissage.getnbIterationsTotales()) {
//                /* Enregistrement du nombre de points obtenus avec cette fréquence d'exploration */
//                moduleApprentissage.updatePerformance(moduleApprentissage.getFrequenceExplorationCourante(), nbPoints);
//                /* Détermination de la nouvelle fréquence d'exploration à utiliser (possiblement la même) */
//                moduleApprentissage.setFrequenceExploration(FrequencesExploration.MOITIE);
//                /* Réinitialisation du nombre d'itérations */
//                moduleApprentissage.setNbIterationsTotales(0);
//            }
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

        FrequencesExploration frequencesExploration = moduleApprentissage.getFrequenceExplorationCourante();

        UpdateNbPointsEvent updateNbPointsEvent;
        List<Action> actionList = etatMental.getIntentions().getActionsList();
        actionList = moduleApprentissage.decideWhereToStopActions(actionList);


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
