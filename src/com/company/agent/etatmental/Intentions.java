package com.company.agent.etatmental;

import com.company.utils.Action;
import com.company.utils.Node;
import com.company.utils.Position;
import com.company.agent.exploration.AStarPathFinder;

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

    /* Détermination des intentions par exploration */

    public void findPaths(Position position, List<Position> dirtsList, List<Position> jewelsList) {

        actionsList.clear();

        int bestManhattanDistance = 1000;
        int newManhattanDistance = 0;

        Position targetPos = new Position(0,0);

        /* On détermine le bijou ou la poussière la plus proche */
        for (Position positionElem : dirtsList) {
            newManhattanDistance = aStarPathFinder.manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
            if(newManhattanDistance < bestManhattanDistance) {
                bestManhattanDistance = newManhattanDistance;
                targetPos = positionElem;
            }
        }

        for (Position positionElem : jewelsList) {
            newManhattanDistance = aStarPathFinder.manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
            if(newManhattanDistance < bestManhattanDistance) {
                bestManhattanDistance = newManhattanDistance;
                targetPos = positionElem;
            }
        }
        /* Appel de l'algorithme d'exploration informée */
        this.path = aStarPathFinder.AStarPathFinder(position,targetPos);
        /* Définition des intentions de l'agent en se basant sur le chemin tracé par l'exploration */
        this.defineActions(dirtsList, jewelsList);
    }

    /* Transformation du chemin déterminé suite à l'exploration en une suite d'actions */
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
