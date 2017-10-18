package com.company.utils;

import javafx.util.Pair;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Node {
    int gCost; // Cost from start position to considered node
    int hCost; // Cost considered node to target position
    int fCost; // Sum of gCost and hCost
    Position parent; // Parent node

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() { return hCost; }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() { return fCost; }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public Position getParent() { return parent; }

    public void setParent(Position parent) { this.parent = parent; }
}
