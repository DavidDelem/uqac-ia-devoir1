package com.company.agent.etatmental;

import com.company.utils.Position;

import java.util.List;

public class EtatMental {

    private Beliefs beliefs;
    private Desires desires;
    private Intentions intentions;

    public EtatMental() {
        this.beliefs = new Beliefs();
        this.intentions = new Intentions();
    }

    public void updateMyBeliefs(List<Position> positionsDirt, List<Position> positionJewels) {
        this.beliefs.setpositionsDirtsList(positionsDirt);
        this.beliefs.setpositionsJewelsListList(positionJewels);
    }

    public List<Position> updateMyIntentions(Position position) {
        List<Position> path = this.intentions.findPaths(position, beliefs.getpositionsDirtsList());
        return path;
    }

    public Beliefs getBeliefs() {
        return beliefs;
    }

    public void setBeliefs(Beliefs beliefs) {
        this.beliefs = beliefs;
    }
}
