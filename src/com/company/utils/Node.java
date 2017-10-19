package com.company.utils;

public class Node {
    Position position; // Node position
    int gCost; // Cost from start position to considered node
    int hCost; // Cost from considered node to target position
    int fCost; // Sum of gCost and hCost
    Node parent; // Parent node

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

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

    public Node getParent() { return parent; }

    public void setParent(Node parent) { this.parent = parent; }

}
