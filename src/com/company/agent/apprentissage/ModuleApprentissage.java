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
        this.nbIterationsMax = 10;
        this.nbIterationsTotales = 0;
        this.frequenceExplorationCourante = FrequencesExploration.TOTALE;
        this.performancesMap = new HashMap<>();

        performancesMap.put(FrequencesExploration.TOTALE, null);
        performancesMap.put(FrequencesExploration.MOITIE, null);
        performancesMap.put(FrequencesExploration.UNQUART, null);
    }

    public List<Action> decideWhereToStopActions(List<Action> actionsList) {

        /* Si on est très proche, on y va dans tout les cas */
        if(actionsList.size() < 4) {
            return actionsList;
        }

        /* Sinon, on ne fait pas toutes les actions */
        switch (frequenceExplorationCourante) {
            case TOTALE:
                return actionsList;
            case MOITIE:
                return  actionsList.subList(0, (actionsList.size() + 1) / 2);
            case UNQUART:
                return  actionsList.subList(0, (actionsList.size() + 1) / 4);
            default:
                return actionsList;
        }
    }

    public void updatePerformance(FrequencesExploration frequencesExploration, int performance) {
        performancesMap.put(frequencesExploration, performance);
    }

    public void chooseBestFrequenceExploration() {

        int bestFrequence = performancesMap.get(FrequencesExploration.TOTALE);

        for (Map.Entry<FrequencesExploration, Integer> entry : performancesMap.entrySet()) {
            if(entry.getValue() == null) {
                frequenceExplorationCourante = entry.getKey();
                break;
            }
            if(entry.getValue() >= bestFrequence) {
                frequenceExplorationCourante = entry.getKey();
            }
        }

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
