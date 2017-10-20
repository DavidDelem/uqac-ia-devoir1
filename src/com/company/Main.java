package com.company;

import com.company.agent.Agent;
import com.company.environement.Environement;
import com.company.interfacegraphique.InterfaceGraphique;
import com.company.utils.Position;
import com.company.utils.SharedDatas;
import com.company.utils.UpdateInterfaceEvent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 Programme principal
 */

public class Main {

    public static void main(String[] args) {

        SharedDatas sharedDatas = new SharedDatas();

        /* Thread de l'environement */
        Environement environement = new Environement(sharedDatas);
        /* Thread de l'agent */
        Agent agent = new Agent(sharedDatas);
        /* Thread de l'interface graphique */
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique(sharedDatas);

        /* Lancement des threads */
        environement.start();
        agent.start();
        interfaceGraphique.start();
    }
}
