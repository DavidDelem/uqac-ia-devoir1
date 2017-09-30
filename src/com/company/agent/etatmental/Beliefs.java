package com.company.agent.etatmental;

import com.company.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Beliefs {

    private List<Position> positionsDirtsList;
    private List<Position> positionsJewelsList;

    public Beliefs() {
        this.positionsDirtsList = new ArrayList<>();
        this.positionsJewelsList = new ArrayList<>();
    }

    public boolean thereIsDirt() {
        return !positionsDirtsList.isEmpty();
    }

    public boolean thereIsJewels() {
        return !positionsJewelsList.isEmpty();
    }

    public List<Position> getpositionsDirtsList() {
        return positionsDirtsList;
    }

    public void setpositionsDirtsList(List<Position> positionsDirtsList) {
        this.positionsDirtsList = positionsDirtsList;
    }

    public List<Position> getpositionsJewelsList() {
        return positionsJewelsList;
    }

    public void setpositionsJewelsListList(List<Position> positionsJewelsList) {
        this.positionsJewelsList = positionsJewelsList;
    }
}
