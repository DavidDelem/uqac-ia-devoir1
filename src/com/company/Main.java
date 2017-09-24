package com.company;

import com.company.environement.Environement;
import com.company.interfacegraphique.InterfaceGraphique;
import com.company.utils.Event;
import com.company.utils.SharedDatas;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {

        SharedDatas sharedDatas = new SharedDatas();
        ConcurrentLinkedQueue<Event> queue = new ConcurrentLinkedQueue<Event>();

        Environement environement = new Environement(sharedDatas.manoir, queue);
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique(sharedDatas.manoir, queue);

        environement.start();
        interfaceGraphique.start();
    }
}
