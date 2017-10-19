package com.company.agent.etatmental;

import com.company.utils.Action;
import com.company.utils.Position;
import com.company.utils.AStarPathFinder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Intentions {

    private AStarPathFinder aStarPathFinder;
    private List<Position> path;
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
            int newManhattanDistance = manhattanDistance(position.getJ(), position.getI(), positionElem.getJ(), positionElem.getI());
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

    private int manhattanDistance(int x1, int y1, int x2, int y2){
        return abs(x2 - x1) + abs(y2 - y1);
    }
}
