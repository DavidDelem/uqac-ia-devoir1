package com.company.agent.etatmental;

import com.company.utils.Position;
import java.util.List;

public class EtatMental {

    private Beliefs beliefs;
    private Desires desires;
    private Intentions intentions;

    public EtatMental() {
        this.beliefs = new Beliefs();
        this.desires = new Desires();
        this.intentions = new Intentions();
    }

    /* Mise à jours des croyances à partir des informations reçues */

    public void updateMyBeliefs(List<Position> positionsDirt, List<Position> positionJewels) {
        this.beliefs.setpositionsDirtsList(positionsDirt);
        this.beliefs.setpositionsJewelsListList(positionJewels);
    }

    /* Mise à jours des intentions à partir des croyances et des désirs */

    public void updateMyIntentions(Position position) {
        /* Si les désirs sont atteins, l'intention est de ne rien faire, on renvoi une liste vide */
        /* Si les désirs ne sont pas atteins, on détermine les actions pour les atteindre */

        if(!desires.iWantATotallyCleanMansion(beliefs.getpositionsDirtsList(), beliefs.getpositionsJewelsList())) {
            this.intentions.findPaths(position, beliefs.getpositionsDirtsList(), beliefs.getpositionsJewelsList());
        }
    }

    public Intentions getIntentions() {
        return intentions;
    }

    public Beliefs getBeliefs() {
        return beliefs;
    }

}
