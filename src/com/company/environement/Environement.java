package com.company.environement;

import com.company.utils.Position;
import com.company.utils.SharedDatas;
import com.company.utils.UpdateInterfaceEvent;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Environement extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<UpdateInterfaceEvent> updateInterfaceQueue;
    private int nbPiecesPropres = 100;

    public Environement(SharedDatas sharedDatas) {
        this.manoir = sharedDatas.manoir;
        this.updateInterfaceQueue = sharedDatas.updateInterfaceQueue;
    }

    public void run() {

        initializeManoir();

        while (gameIsRunning()) {
            if (shouldThereBeANewDirtySpace()) {
                generateDirt();
            }
            if (shouldThereBeANewLostJewel()) {
                generateJewel();
            }

            updateMesurePerformance();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeManoir() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.manoir[i][j] = new Piece();
            }
        }
    }

    private Boolean gameIsRunning() {
        return true;
    }

    private Boolean shouldThereBeANewDirtySpace() {
        return new Random().nextDouble() < 0.65;
    }

    private Boolean shouldThereBeANewLostJewel() {
        return new Random().nextDouble() < 0.20;
    }

    private void generateDirt() {

        /* Détermination du placement de la position de la poussière */
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);

        /* Mise à jours du manoir */
        manoir[i][j].setDirt(true);
        /* Evénement pour indiquer à l'interface la piéce à mettre à jour */
        updateInterfaceQueue.add(new UpdateInterfaceEvent(new Position(i, j), null, "updateContenuPiece"));
    }

    private void generateJewel() {

        /* Détermination du placement de la position des bijoux */
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);

        /* Mise à jours du manoir */
        manoir[i][j].setJewel(true);
        /* Evénement pour indiquer à l'interface la piéce à mettre à jour */
        updateInterfaceQueue.add(new UpdateInterfaceEvent(new Position(i, j), null, "updateContenuPiece"));
    }

    private void updateMesurePerformance() {
        this.nbPiecesPropres = 100;

        int nbPoussieres = 0;
        int nbJewels = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(this.manoir[i][j].getJewel() || this.manoir[i][j].getDirt()) {
                    this.nbPiecesPropres--;

                    if(this.manoir[i][j].getJewel()) {
                        nbJewels++;
                    }
                    if(this.manoir[i][j].getDirt()) {
                        nbPoussieres++;
                    }
                }
            }
        }
        updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(nbPiecesPropres),"updateAffichageMesurePerf1"));
        updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(nbPoussieres),"updateAffichageMesurePerf2"));
        updateInterfaceQueue.add(new UpdateInterfaceEvent(null, String.valueOf(nbJewels),"updateAffichageMesurePerf3"));
    }

}
