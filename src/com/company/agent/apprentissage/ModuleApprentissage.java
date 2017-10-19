package com.company.agent.apprentissage;

import com.company.utils.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleApprentissage {

    private int nbIterationsMax;
    private int nbIterationsTotales;
    private FrequencesExploration frequenceExplorationCourante;
    Map<FrequencesExploration, Integer> performancesMap;

    public ModuleApprentissage() {
        this.nbIterationsMax = 5;
        this.nbIterationsTotales = 0;
        this.frequenceExplorationCourante = FrequencesExploration.TOTALE;
        this.performancesMap = new HashMap<>();

        performancesMap.put(FrequencesExploration.TOTALE, 0);
        performancesMap.put(FrequencesExploration.TROISQUART, 0);
        performancesMap.put(FrequencesExploration.MOITIE, 0);
        performancesMap.put(FrequencesExploration.UNQUART, 0);
    }

    public List<Action> decideWhereToStopActions(List<Action> actionsList) {
        switch (frequenceExplorationCourante) {
            case TOTALE:
                return actionsList;
            case TROISQUART:
                return  actionsList.subList(0, (actionsList.size() / 4 + 1) * 3);
            case MOITIE:
                return  actionsList.subList(0, actionsList.size() / 2 + 1);
            case UNQUART:
                return  actionsList.subList(0, actionsList.size() / 4 + 1);
            default:
                return actionsList;
        }
    }

    public void updatePerformance(FrequencesExploration frequencesExploration, int performance) {
        performancesMap.put(frequencesExploration, performance);
    }

    public void incrementeNbIterationTotales() {
        this.nbIterationsTotales++;
    }

    public int getNbIterationsMax() {
        return nbIterationsMax;
    }

    public void setNbIterationsMax(int nbIterationsMax) {
        this.nbIterationsMax = nbIterationsMax;
    }

    public int getnbIterationsTotales() {
        return nbIterationsTotales;
    }

    public void setNbIterationsTotales(int nbIterationsTotales) {
        this.nbIterationsTotales = nbIterationsTotales;
    }

    public FrequencesExploration getFrequenceExplorationCourante() {
        return frequenceExplorationCourante;
    }

    public void setFrequenceExploration(FrequencesExploration frequenceExplorationCourante) {
        this.frequenceExplorationCourante = frequenceExplorationCourante;
    }
}
