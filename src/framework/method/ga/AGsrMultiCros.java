/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.ga;

import framework.problem.IndividualB;
import framework.operator.crossover.OperatorMultCrossover;
import framework.operator.selection.TypeSelection;
import framework.operator.crossover.TypeCrossover;
import framework.operator.initialization.OperatorInitialization;
import framework.operator.initialization.TypeInitialization;
import framework.operator.selection.OperatorSelection;

/**
 * AGsr - Algoritmo Genetico Seleto Recombinativo Com Multiplos pais Efetuando o crossover.
 * @author jesimar
 */
public class AGsrMultiCros {
    private final int SIZE_POPULATION = 50;    
    private final int SIZE_GENERATION = 50;
    private final int SIZE_MULT_CROSSOVER = 2;
    
    private final IndividualB pop[] = new IndividualB[SIZE_POPULATION]; 
    private final IndividualB popAux[] = new IndividualB[SIZE_POPULATION];                  
    private final OperatorSelection opSelection;
    private final OperatorMultCrossover opMultCrossover;
    private final OperatorInitialization opInitialization;
    
    private final TypeInitialization typeInitialization = TypeInitialization.RANDOM;
    private final TypeSelection typeSelection = TypeSelection.TRUNCATION;
    private final TypeCrossover typeCrossover = TypeCrossover.UNIFORM;
        
    public AGsrMultiCros(){
        opInitialization = new OperatorInitialization(pop);
        opSelection = new OperatorSelection(pop);
        opMultCrossover = new OperatorMultCrossover();
    }
    
    public void exec(){
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }
        printGeneration(generation);        
        while (generation < SIZE_GENERATION){
            for (int i = 0; i < pop.length; i++){
                IndividualB indPai[] = new IndividualB[SIZE_MULT_CROSSOVER];
                for (int j = 0; j < SIZE_MULT_CROSSOVER; j++){
                    indPai[j] = opSelection.select(typeSelection);
                }
                IndividualB indChild;
                if (SIZE_MULT_CROSSOVER == 2){
                    indChild = opMultCrossover.crossover(indPai[0], indPai[1], 
                            typeCrossover);
                }else if (SIZE_MULT_CROSSOVER == 3){
                    indChild = opMultCrossover.crossover(indPai[0], indPai[1], 
                            indPai[2], typeCrossover);
                }else if (SIZE_MULT_CROSSOVER == 4){
                    indChild = opMultCrossover.crossover(indPai[0], indPai[1], 
                            indPai[2], indPai[3], typeCrossover);
                }
                indChild.evaluate();
                popAux[i] = indChild;
            } 
            for (int i = 0; i < pop.length; i++){
                pop[i] = popAux[i];
            }
            generation++;
            printGeneration(generation);
        }
    }
    
    public double averageFitness(IndividualB[] pop){
        double sum = 0;
        for (IndividualB individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    } 
    
    public double getFitnessBest(IndividualB pop[]){
        return getBest(pop).fitness;
    }
    
    public IndividualB getBest(IndividualB pop[]){
        IndividualB best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            IndividualB newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (fitnessNewInd > fitnessBest){
                best = newInd;
                fitnessBest = fitnessNewInd;
            }
        }
        return best;
    }
    
    
   
    public void printGeneration(int generation){
        System.out.println("----Generation["+ generation + "]----");
        for (int i = 0; i < pop.length; i++){                        
            System.out.println(pop[i].toString() + " fitness: " + pop[i].fitness);            
        }
        System.out.println("Best Individual: " + getBest(pop));        
        System.out.println("Best Fitness   : " + getFitnessBest(pop));
        System.out.println("Media Fitness: " + averageFitness(pop));
    }   
}
