package com.company.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class AStarPathFinder {

    List<Position> path;
    Map<Position, Node> openList;
    Map<Position, Node> closedList;

    public List<Position> AStarPathFinder(Position startPos, Position targetPos) {
        openList = new HashMap<>();
        closedList = new HashMap<>();
        path = new ArrayList<>();

        Position current = new Position(startPos.getI(),startPos.getJ());

        Node start = new Node();
        Position startParent = new Position(0,0);
        start.setgCost(0);
        start.sethCost(manhattanDistance(targetPos.getI(),targetPos.getJ(),startPos.getI(),startPos.getJ()));
        start.setfCost(start.getgCost()+start.gethCost());
        start.setParent(startParent);


        openList.put(current,start);
        addToClosedList(current);
        addAdjacentNode(current,targetPos);

        while(!((current.getI() == targetPos.getI()) && (current.getJ() == targetPos.getJ())) && (!openList.isEmpty())) {
            // on cherche le meilleur noeud de la liste ouverte, on sait qu'elle n'est pas vide donc il existe
            current = bestNode(openList);

            addToClosedList(current);
            addAdjacentNode(current,targetPos);
        }

        if ((current.getI() == targetPos.getI()) && (current.getJ() == targetPos.getJ())){
            findPath(start,targetPos);
        }else{
            /* pas de solution */
        }
        return path;
    }

    private void addToClosedList(Position pos){
        Node node = openList.get(pos);
        closedList.put(pos,node);
        openList.remove(pos);
    }

    private void addAdjacentNode(Position current, Position targetPos) {
        Node tmp = new Node();
        for (int j=current.getJ()-1;j<=current.getJ()+1;j++) {
            if (j<0 || j>10) { continue; } // Outside map = forget
            for (int i=current.getI()-1;i<=current.getI()+1;i++) {
                if (i<0 || i>10) { continue; } // Outside map = forget
                if (j==current.getJ() && i==current.getI()) { continue; } // Current node = forget
                // Diagonal cases = forget
                if (j==current.getJ()-1 && i==current.getI()-1) { continue; }
                if (j==current.getJ()-1 && i==current.getI()+1) { continue; }
                if (j==current.getJ()+1 && i==current.getI()-1) { continue; }
                if (j==current.getJ()+1 && i==current.getI()+1) { continue; }

                Position adjacentPos = new Position(i,j);
                if (!alreadyInList(adjacentPos,closedList)) {
                    tmp.setgCost(closedList.get(current).getgCost() + manhattanDistance(i,j,current.getI(),current.getJ()));
                    tmp.sethCost(manhattanDistance(i,j,targetPos.getI(),targetPos.getJ()));
                    tmp.setfCost(tmp.getgCost()+tmp.gethCost());
                    tmp.setParent(current);

                    if (alreadyInList(adjacentPos, openList)){
                        /* already in open list, compare costs */
                        if (tmp.getfCost() < openList.get(adjacentPos).getfCost()){
                            openList.put(adjacentPos,tmp);
                        }
                    } else {
                        /* not in open list, add to it */
                        openList.put(adjacentPos,tmp);
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
        int bestFCost = list.get(list.keySet().toArray()[0]).getfCost();
        Position bestNode = new Position(list.keySet().iterator().next().getI(),list.keySet().iterator().next().getJ());

        for(Map.Entry<Position, Node> entry : list.entrySet()) {
            Position key = entry.getKey();
            Node value = entry.getValue();
            if (value.getfCost() < bestFCost) {
                bestFCost = value.getfCost();
                bestNode = key;
            }
        }

        return bestNode;
    }

    private void findPath(Node start, Position targetPos) {
        /* l'arrivée est le dernier élément de la liste fermée */
        Node tmp = closedList.get(targetPos);

        Position n = new Position(targetPos.getI(),targetPos.getJ());
        Position prec = new Position(tmp.getParent().getI(),tmp.getParent().getJ());
        path.add(n);

        while (prec != new Position(start.getParent().getI(),start.getParent().getJ())){
            n.setI(prec.getI());
            n.setJ(prec.getJ());
            path.add(n);

            tmp = closedList.get(tmp.getParent());
            prec.setI(tmp.getParent().getI());
            prec.setJ(tmp.getParent().getJ());
        }
    }
}
