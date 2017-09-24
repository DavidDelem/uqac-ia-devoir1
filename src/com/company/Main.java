package com.company;

import com.company.agent.Agent;
import com.company.environement.Environement;
import com.company.interfacegraphique.InterfaceGraphique;
import com.company.utils.Position;
import com.company.utils.SharedDatas;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {

        SharedDatas sharedDatas = new SharedDatas();
        ConcurrentLinkedQueue<Position> queue = new ConcurrentLinkedQueue<Position>();

        /* Thread de l'environement */
        Environement environement = new Environement(sharedDatas.manoir, queue);
        /* Thread de l'agent */
        Agent agent = new Agent(sharedDatas.manoir, queue);
        /* Thread de l'interface graphique */
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique(sharedDatas.manoir, queue);

        /* Lancement des threads */
        environement.start();
        agent.start();
        interfaceGraphique.start();
    }
}
