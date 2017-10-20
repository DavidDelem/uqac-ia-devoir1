package com.company.agent.etatmental;

import com.company.utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 Croyances de l'agent
 */

public class Beliefs {

    private List<Position> positionsDirtsList;
    private List<Position> positionsJewelsList;

    public Beliefs() {
        this.positionsDirtsList = new ArrayList<>();
        this.positionsJewelsList = new ArrayList<>();
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

    public void setpositionsJewelsList(List<Position> positionsJewelsList) {
        this.positionsJewelsList = positionsJewelsList;
    }
}
