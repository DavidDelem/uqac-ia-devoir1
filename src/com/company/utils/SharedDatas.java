package com.company.utils;

import com.company.environement.Piece;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SharedDatas {
    public volatile Piece[][] manoir = new Piece[10][10];
    public volatile ConcurrentLinkedQueue<UpdateInterfaceEvent> updateInterfaceQueue = new ConcurrentLinkedQueue<>();
    public volatile ConcurrentLinkedQueue<UpdateNbPointsEvent> updateNbPointsQueue = new ConcurrentLinkedQueue<>();
}
