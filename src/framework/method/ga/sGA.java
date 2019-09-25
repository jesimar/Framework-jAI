/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.ga;

import framework.problem.IndividualB;
import framework.operator.initialization.TypeInitialization;
import framework.operator.crossover.OperatorCrossover;
import framework.operator.selection.TypeSelection;
import framework.operator.initialization.OperatorInitialization;
import framework.operator.crossover.TypeCrossover;
import framework.operator.mutation.OperatorMutation;
import framework.operator.mutation.TypeMutation;
import framework.operator.selection.OperatorSelection;

/**
 * AGsr - Algoritmo Genetico Seleto Recombinativo.
 * @author jesimar
 */
public class sGA {
    
    private final int SIZE_POPULATION = 100;  
    private final int SIZE_GENERATION = 50;
    
    private final IndividualB pop[] = new IndividualB[SIZE_POPULATION]; 
    private final IndividualB popAux[] = new IndividualB[SIZE_POPULATION]; 
                
    private final OperatorSelection opSelection;
    private final OperatorInitialization opInitialization;
    private final OperatorCrossover opCrossover;
    private final OperatorMutation opMutation;
    
    private final TypeSelection typeSelection = TypeSelection.TOURNAMENT;
    private final TypeInitialization typeInitialization = TypeInitialization.RANDOM;
    private final TypeCrossover typeCrossover = TypeCrossover.UNIFORM;
    private final TypeMutation typeMutation = TypeMutation.UNIFORM;
    
    public sGA(){
        opSelection = new OperatorSelection(pop);
        opInitialization = new OperatorInitialization(pop);
        opCrossover = new OperatorCrossover();
        opMutation = new OperatorMutation();
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
                IndividualB indPai1 = opSelection.select(typeSelection);
                IndividualB indPai2 = opSelection.select(typeSelection);
                IndividualB indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                opMutation.mutation(indChild, typeMutation);
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
    
    /** 
        Ideia do Algoritmo Genético Padrão.
        1. Uma população de tamanho fixo;
        2. População gerada aleatóriamente;
        3. Um Operador de seleção dos indivíduos que irão reproduzir;
        4. Cruzamento entre os indivíduos gerados;
        5. Mutação do indivíduo filho;
        6. A nova população é composta somente pelos filhos gerados;
        7. Melhor indivíduo é retornado;
     */
}
