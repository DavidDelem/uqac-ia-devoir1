package com.company.agent.etatmental;

import com.company.utils.Action;
import com.company.utils.Node;
import com.company.utils.Position;
import com.company.utils.AStarPathFinder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

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

        List<Position> positionsList = dirtsList;
        positionsList.addAll(jewelsList);

        int bestManhattanDistance = 100;
        Position targetPos = new Position(0,0);
        for (Position positionElem : positionsList) {
            int newManhattanDistance = aStarPathFinder.manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
            if(newManhattanDistance < bestManhattanDistance) {
                bestManhattanDistance = newManhattanDistance;
                targetPos = positionElem;
            }
        }
        this.path = aStarPathFinder.AStarPathFinder(position,targetPos);
    }

    public List<Action> getActionsList() {
        return actionsList;
    }
}
