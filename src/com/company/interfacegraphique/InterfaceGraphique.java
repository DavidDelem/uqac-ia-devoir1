package com.company.interfacegraphique;

import com.company.environement.Piece;
import com.company.utils.SharedDatas;
import com.company.utils.UpdateInterfaceEvent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 Gestion de la mise à jours de l'interface graphique
 */

public class InterfaceGraphique extends Thread {

    private Piece[][] manoir;
    private Fenetre fenetre;
    private ConcurrentLinkedQueue<UpdateInterfaceEvent> updateInterfaceQueue;

    /* Initialisation de l'interface */

    public InterfaceGraphique(SharedDatas sharedDatas) {
        this.manoir = sharedDatas.manoir;
        this.updateInterfaceQueue = sharedDatas.updateInterfaceQueue;
        fenetre = new Fenetre();
    }

    /* Boucle infinie afin d'écouter les événements émis par les autres threads
       et mettre à jours l'interface en conséquence
     */
    public void run() {
        UpdateInterfaceEvent updateInterfaceEvent;
        int i, j;

        while(true) {
            if((updateInterfaceEvent = updateInterfaceQueue.poll()) != null) {
                switch (updateInterfaceEvent.getTypeEvent()) {
                    case "updateContenuPiece":
                        i = updateInterfaceEvent.getPosition().getI();
                        j = updateInterfaceEvent.getPosition().getJ();
                        fenetre.updatePiece(i, j, manoir[i][j].getDirt(), manoir[i][j].getJewel());
                        break;
                    case "updatePositionRobot":
                        i = updateInterfaceEvent.getPosition().getI();
                        j = updateInterfaceEvent.getPosition().getJ();
                        fenetre.updatePositionRobot(i, j);
                        break;
                    case "updateAffichageMesurePerf1":
                        fenetre.updateNbPiecesPropresLabel(updateInterfaceEvent.getInformations());
                        break;
                    case "updateAffichageMesurePerf2":
                        fenetre.updateNbPoussieresLabel(updateInterfaceEvent.getInformations());
                        break;
                    case "updateAffichageMesurePerf3":
                        fenetre.updateNbJewelsLabel(updateInterfaceEvent.getInformations());
                        break;
                    case "updateAffichageNbPoints":
                        fenetre.updateNbPoints(updateInterfaceEvent.getInformations());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
