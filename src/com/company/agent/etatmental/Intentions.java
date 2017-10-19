package com.company.agent.etatmental;

import com.company.utils.Position;
import com.company.utils.AStarPathFinder;

import java.util.List;

public class Intentions {

    private AStarPathFinder aStarPathFinder;
    private List<Position> path;

    public Intentions() {
        this.aStarPathFinder = new AStarPathFinder();
    }

    public List<Position> findPaths(Position position, List<Position> dirtsList) {
        Position targetPos = dirtsList.get(0);
        path = aStarPathFinder.AStarPathFinder(position,targetPos);
        System.out.println("lol");
        return path;
    }
}
