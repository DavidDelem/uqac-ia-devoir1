package com.company.utils;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.abs;

public class AStarPathFinder {

    Node start;
    Position target;
    ArrayList<Position> path;
    Map<Position, Node> openList;
    Map<Position, Node> closedList;

    public ArrayList<Position> AStarPathFinder (Position startPos, Position targetPos) {
        target = targetPos;
        //start.parent.setI(0);
        //start.parent.setJ(0);
        Position current = new Position(startPos.getI(),startPos.getJ());

        openList.put(current,start);
        addToClosedList(current);
        addAdjacentNode(current);

        while(!((current.getI() == target.getI()) && (current.getJ() == target.getJ())) && (!openList.isEmpty())) {
            // on cherche le meilleur noeud de la liste ouverte, on sait qu'elle n'est pas vide donc il existe
            current = bestNode(openList);

            addToClosedList(current);
            addAdjacentNode(current);
        }

        if ((current.getI() == target.getI()) && (current.getJ() == target.getJ())){
            findPath();
        }else{
            /* pas de solution */
        }
        return path;
    }

    private void addToClosedList(Position pos){
        Node node = openList.get(pos);
        closedList.get(pos).gCost=node.gCost;
        closedList.get(pos).hCost=node.hCost;
        closedList.get(pos).fCost=node.fCost;
        closedList.get(pos).parent=node.parent;

        openList.remove(pos);
    }

    private void addAdjacentNode(Position current) {
        Node tmp = new Node();
        for (int j=current.getJ()-1;j<=current.getJ()+1;j++) {
            if (j<0 || j>10) { continue; } // Outside map = forget
            for (int i=current.getI()-1;i<=current.getI()+1;j++) {
                if (i<0 || i>10) { continue; } // Outside map = forget
                if (j==current.getJ() && i==current.getI()) { continue; } // Current node = forget
                // Diagonal cases = forget
                if (j==current.getJ()-1 && i==current.getI()-1) { continue; }
                if (j==current.getJ()-1 && i==current.getI()+1) { continue; }
                if (j==current.getJ()+1 && i==current.getI()-1) { continue; }
                if (j==current.getJ()+1 && i==current.getI()+1) { continue; }

                Position adjacentPos = new Position(i,j);
                if (!alreadyInList(adjacentPos,closedList)) {
                    tmp.gCost = closedList.get(current).gCost + manhattanDistance(j,i,current.getJ(),current.getI());
                    tmp.hCost = manhattanDistance(j,i,target.getJ(),target.getI());
                    tmp.fCost = tmp.gCost + tmp.hCost;
                    tmp.parent = current;

                    if (alreadyInList(adjacentPos, openList)){
                        /* already in open list, compare costs */
                        if (tmp.fCost < openList.get(adjacentPos).fCost){
                            openList.get(adjacentPos).gCost=tmp.gCost;
                            openList.get(adjacentPos).hCost=tmp.hCost;
                            openList.get(adjacentPos).fCost=tmp.fCost;
                            openList.get(adjacentPos).parent=tmp.parent;
                        }
                    } else {
                        /* not in open list, add to it */
                        openList.get(adjacentPos).gCost=tmp.gCost;
                        openList.get(adjacentPos).hCost=tmp.hCost;
                        openList.get(adjacentPos).fCost=tmp.fCost;
                        openList.get(adjacentPos).parent=tmp.parent;
                    }
                }
            }
        }
    }

    // Calculate distance between positions (x1,y1) and (x2,y2)
    private int manhattanDistance(int x1, int y1, int x2, int y2){
        return abs(x2 - x1) + abs(y2 - y1);
    }

    // Test if node is already in a list
    private boolean alreadyInList(Position pos, Map<Position, Node> list){
        boolean found = false;
        for(Map.Entry<Position, Node> entry : list.entrySet()) {
            Position key = entry.getKey();
            if (key == pos) { found = true; }
        }
        return found;
    }

    private Position bestNode(Map<Position, Node> list){
        int bestFCost = list.get(list.keySet().toArray()[0]).fCost;
        Position bestNode = new Position(list.keySet().iterator().next().getI(),list.keySet().iterator().next().getJ());

        for(Map.Entry<Position, Node> entry : list.entrySet()) {
            Position key = entry.getKey();
            Node value = entry.getValue();
            if (value.fCost < bestFCost) {
                bestFCost = value.fCost;
                bestNode = key;
            }
        }

        return bestNode;
    }

    private void findPath() {
        /* l'arrivée est le dernier élément de la liste fermée */
        Node tmp = closedList.get(target);

        Position n = new Position(target.getI(),target.getJ());
        Position prec = new Position(tmp.parent.getI(),tmp.parent.getJ());
        path.add(n);

        while (prec != new Position(start.parent.getI(),start.parent.getJ())){
            n.setI(prec.getI());
            n.setJ(prec.getJ());
            path.add(n);

            tmp = closedList.get(tmp.parent);
            prec.setI(tmp.parent.getI());
            prec.setJ(tmp.parent.getJ());
        }
    }
}
