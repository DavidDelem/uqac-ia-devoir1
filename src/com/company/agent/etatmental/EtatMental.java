package com.company.agent.etatmental;

import com.company.utils.Position;

import java.util.List;

public class EtatMental {

    private Beliefs beliefs;
    private Desires desires;
    private Intentions intentions;

    public EtatMental() {
        this.beliefs = new Beliefs();
    }

    public void updateMyBeliefs(List<Position> positionsDirt, List<Position> positionJewels) {
        this.beliefs.setpositionsDirtsList(positionsDirt);
        this.beliefs.setpositionsJewelsListList(positionJewels);
    }


}
