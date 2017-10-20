package com.company.agent;

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

            // J'observe l'environement
            observeEnvironmentWithAllMySensors();
            // Je met à jour mon état interne
            updateMyState();

            // Je choisi une action et je l'execute
            if (etatMental.getBeliefs().getpositionsDirtsList() != null || etatMental.getBeliefs().getpositionsJewelsList() != null) {
                if (!etatMental.getBeliefs().getpositionsDirtsList().isEmpty() || !etatMental.getBeliefs().getpositionsJewelsList().isEmpty()) {
                    chooseAnAction();
                    justDoIt();
                    moduleApprentissage.incrementeNbIterationTotales();
                }
            }


            /* Apprentissage */

            /* Au bout d'un certain nombre d'itérations, on va adapter la fréquence d'exploration */
            if(moduleApprentissage.getNbIterationsMax() == moduleApprentissage.getnbIterationsTotales()) {
                /* Enregistrement du nombre de points obtenus avec cette fréquence d'exploration */
                moduleApprentissage.updatePerformance(moduleApprentissage.getFrequenceExplorationCourante(), nbPoints);
                /* Détermination de la nouvelle fréquence d'exploration à utiliser (possiblement la même) */
                moduleApprentissage.chooseBestFrequenceExploration();
                /* Réinitialisation du nombre d'itérations */
                moduleApprentissage.setNbIterationsTotales(0);
            }
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

        UpdateNbPointsEvent updateNbPointsEvent;

        /* Récupération de la liste des actions */
        List<Action> actionList = etatMental.getIntentions().getActionsList();
        /* Décision du nombre réel d'actions à effectuer avant la prochaine exploration */
        actionList = moduleApprentissage.decideWhereToStopActions(actionList);

        /* Réalisation des actions */

        for(Action action: actionList) {

            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* Utilisation des effecteurs */
            position = effecteurs.doAnAction(action, position);

            /* Mise à jours du nombre de points */
            if((updateNbPointsEvent = updateNbPointsQueue.poll()) != null) {
                this.nbPoints += updateNbPointsEvent.getNbPoints();
            }

            /* Envoi d'événements à l'interface graphique pour l'informer de changement */
            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null, "updatePositionRobot"));
            updateInterfaceQueue.add(new UpdateInterfaceEvent(position, null,"updateContenuPiece"));
            updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(this.nbPoints), "updateAffichageNbPoints"));
        }
    }

}
