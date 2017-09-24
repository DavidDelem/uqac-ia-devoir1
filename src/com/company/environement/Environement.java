package com.company.environement;

import com.company.utils.Event;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Environement extends Thread {

    private Piece[][] manoir;
    private ConcurrentLinkedQueue<Event> queue;

    public Environement(Piece[][] manoir, ConcurrentLinkedQueue<Event> queue) {
        this.manoir = manoir;
        this.queue = queue;
    }

    public void run() {

        initializeManoir();

        while (gameIsRunning()) {
            if (shouldThereBeANewDirtySpace()) generateDirt();
            if (shouldThereBeANewLostJewel()) generateJewel();

            try {
                TimeUnit.SECONDS.sleep(2);
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
        Random random = new Random();
        return random.nextDouble() < 0.7;
    }

    private Boolean shouldThereBeANewLostJewel() {
        Random random = new Random();
        return random.nextDouble() < 0.1;
    }

    private void generateDirt() {
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);
        manoir[i][j].setDirt(true);
        queue.add(new Event("dirt", i, j));
    }

    private void generateJewel() {
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);
        manoir[i][j].setJewel(true);
        queue.add(new Event("jewels", i, j));
    }

}
