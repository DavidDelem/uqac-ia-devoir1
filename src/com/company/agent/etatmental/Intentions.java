package com.company.agent.etatmental;

import com.company.utils.Action;
import com.company.utils.Node;
import com.company.utils.Position;
import com.company.utils.AStarPathFinder;

import java.util.ArrayList;
import java.util.List;

public class Intentions {

    private AStarPathFinder aStarPathFinder;
    private List<Node> path;
    private List<Action> actionsList;

    public Intentions() {
        this.aStarPathFinder = new AStarPathFinder();
        this.path = new ArrayList<>();
        this.actionsList = new ArrayList<>();
    }

    public void findPaths(Position position, List<Position> dirtsList, List<Position> jewelsList) {

        actionsList.clear();
//        List<Position> positionsList = dirtsList;
//        positionsList.addAll(jewelsList);

        int bestManhattanDistance = 1000;
        Position targetPos = new Position(0,0);

        for (Position positionElem : dirtsList) {
            int newManhattanDistance = aStarPathFinder.manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
            if(newManhattanDistance < bestManhattanDistance) {
                bestManhattanDistance = newManhattanDistance;
                targetPos = positionElem;
            }
        }

        for (Position positionElem : jewelsList) {
            int newManhattanDistance = aStarPathFinder.manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
            if(newManhattanDistance < bestManhattanDistance) {
                bestManhattanDistance = newManhattanDistance;
                targetPos = positionElem;
            }
        }

        this.path = aStarPathFinder.AStarPathFinder(position,targetPos);
        this.defineActions(dirtsList,jewelsList);
    }

    private void defineActions(List<Position> dirtsList, List<Position> jewelsList) {
        for (int i=0;i<path.size()-1;i++) {
            int abscissa = path.get(i+1).getPosition().getJ()-path.get(i).getPosition().getJ();
            int ordinate = path.get(i+1).getPosition().getI()-path.get(i).getPosition().getI();

            if (abscissa < 0) actionsList.add(Action.GAUCHE);
            else if (abscissa > 0) actionsList.add(Action.DROITE);

            if (ordinate < 0) actionsList.add(Action.HAUT);
            else if (ordinate > 0) actionsList.add(Action.BAS);

            if (i+1 == path.size()-1) {
                if (containsPosition(path.get(i+1).getPosition(),dirtsList) && !containsPosition(path.get(i+1).getPosition(),jewelsList)) {
                    actionsList.add(Action.NETTOYER);
                } else if (containsPosition(path.get(i+1).getPosition(),jewelsList) && !containsPosition(path.get(i+1).getPosition(),dirtsList)) {
                    actionsList.add(Action.RAMASSER);
                } else if (containsPosition(path.get(i+1).getPosition(),dirtsList) && containsPosition(path.get(i+1).getPosition(),jewelsList)) {
                    actionsList.add(Action.NETTOYERETRAMASSER);
                }
            }
        }
    }

    private boolean containsPosition(Position position,List<Position> list) {
        for (Position index : list) {
            if (position.getJ()==index.getJ() && position.getI()==index.getI()) { return true; }
        }
        return false;
    }

    public List<Action> getActionsList() {
        return actionsList;
    }
}
