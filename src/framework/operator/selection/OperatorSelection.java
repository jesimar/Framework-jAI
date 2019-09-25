/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.operator.selection;

import framework.problem.IndividualB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class OperatorSelection {

    private final IndividualB pop[];
    private int SIZE_TOURNAMENT = 4;
    private int idTour = -1;
    
    private final double FACTOR_TRUNCATION_00SD = 50.00;
    private final double FACTOR_TRUNCATION_05SD = 30.85;
    private final double FACTOR_TRUNCATION_10SD = 15.87;
    private final double FACTOR_TRUNCATION_15SD = 6.68;
    private final double FACTOR_TRUNCATION_20SD = 2.28;
    private final double FACTOR_TRUNCATION_25SD = 0.62;    
    private double SIZE_TRUNCATION = FACTOR_TRUNCATION_10SD;

    private final Random rnd = new Random();

    private static List<Tupla> INTERVALS;

    public OperatorSelection(IndividualB pop[]) {
        this.pop = pop;
    }

    public IndividualB select(TypeSelection typeSelection) {
        if (typeSelection == TypeSelection.TOURNAMENT) {
            return selectTournament();
        } else if (typeSelection == TypeSelection.TRUNCATION) {
            return selectTruncation();
        } else if (typeSelection == TypeSelection.PROPORTIONAL) {
            return selectProportional();
        } else if (typeSelection == TypeSelection.TOURO_CRUZADOR) {
            idTour++;
            return selectTouroCruzador(idTour);
        } else if (typeSelection == TypeSelection.LINEAR_CLASSIFICATION) {
            return selectLinearClassification();
        }
        return null;
    }

    public IndividualB selectTournament(int sizeTournament) {
        SIZE_TOURNAMENT = sizeTournament;
        return selectTournament();
    }

    private IndividualB selectTournament() {
        int bestId = rnd.nextInt(pop.length);
        double bestInd = pop[bestId].fitness;
        for (int i = 0; i < SIZE_TOURNAMENT - 1; i++) {
            int newId = rnd.nextInt(pop.length);
            double newInd = pop[newId].fitness;
            if (newInd > bestInd) {
                bestInd = newInd;
                bestId = newId;
            }
        }
        return pop[bestId];
    }

    public IndividualB selectTruncation(double sizeTruncation) {
        SIZE_TRUNCATION = sizeTruncation;
        return selectTruncation();
    }
    
    private IndividualB selectTruncation() {
        Arrays.sort(pop);
        int value = (int) (SIZE_TRUNCATION * pop.length / 100.0);
        int idInd = rnd.nextInt(value) + 1;
        return pop[pop.length - idInd];
    }        
    
    private IndividualB selectProportional() {
        Arrays.sort(pop);
        int fitnessTotal = 0;
        for (int i = 0; i < pop.length; i++) {
            fitnessTotal += pop[i].fitness;
        }
        int rand = rnd.nextInt(fitnessTotal);
        int totalPartial = 0;
        int i = -1;
        do {
            i++;
            totalPartial += pop[i].fitness;
        } while (totalPartial < rand);
        return pop[i];
    }

    public IndividualB selectTouroCruzador() {
        idTour = -1;
        Arrays.sort(pop);
        return pop[pop.length - 1];
    }

    private IndividualB selectTouroCruzador(int i) {
        Arrays.sort(pop);
        return pop[i];
    }

    private IndividualB selectLinearClassification() {
        List<Tupla> inters = getLinearClassificationIntervals(pop.length);        
        Arrays.sort(pop);
        double val = rnd.nextDouble();
        int i;
        
        for (i = 0; i < inters.size(); i++) {
            Tupla t = inters.get(i);
            if (val >= t.begin && val < t.end) {
                break;
            }
        }
        return pop[i];
    }

    public static List<Tupla> getLinearClassificationIntervals(int popSize) {
        if (INTERVALS != null && !INTERVALS.isEmpty()) {
            return INTERVALS;
        }
        INTERVALS = new ArrayList<>();
        double previousEnd = 0;
        double posAnterior = 0;
        double n = ((popSize + 1) * popSize) / 2.0;
        
        for (int i = 1; i <= popSize; i++) {
            double end = ((i + posAnterior) / n);
            posAnterior += i;
            INTERVALS.add(new Tupla(previousEnd, end));
            previousEnd = end;
        }
        return INTERVALS;
    }

    public static class Tupla implements Comparable<Tupla> {
        public double begin;
        public double end;
        public Tupla(double begin, double end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public int compareTo(Tupla o) {
            return new Double(begin).compareTo(new Double(o.begin));
        }
    }
}
