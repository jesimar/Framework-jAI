package framework.method.ga;

import framework.operator.crossover.OperatorMultCrossover;
import framework.operator.selection.TypeSelection;
import framework.operator.crossover.TypeCrossover;
import framework.operator.initialization.OperatorInitialization;
import framework.operator.initialization.TypeInitialization;
import framework.operator.selection.OperatorSelection;
import framework.problem.Individual;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;

/**
 * AGsr - Algoritmo Genetico Seleto Recombinativo Com Multiplos pais Efetuando o crossover.
 * @author Jesimar da Silva Arantes
 */
public class AGsrMultiCros {
    private final int SIZE_POPULATION = 1000;    
    private final int SIZE_GENERATION = 50;
    private final int SIZE_MULT_CROSSOVER = 4;
    
    private final Individual pop[] = new Individual[SIZE_POPULATION]; 
    private final Individual popAux[] = new Individual[SIZE_POPULATION];                  
    private final OperatorSelection opSelection;
    private final OperatorMultCrossover opMultCrossover;
    private final OperatorInitialization opInitialization;
    
    private final TypeInitialization typeInitialization = TypeInitialization.RANDOM;
    private final TypeSelection typeSelection = TypeSelection.TOURNAMENT;
    private final TypeCrossover typeCrossover = TypeCrossover.UNIFORM;
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.APOSTILA_TIA;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.CONTINUOUS;
        
    public AGsrMultiCros(){
        opInitialization = new OperatorInitialization(pop, typeProblem, typeRepresentation);
        opSelection = new OperatorSelection(pop, typeProblemMaxMin);
        opMultCrossover = new OperatorMultCrossover(typeProblem, typeRepresentation);
    }
    
    public void exec(){
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }
        //printGeneration(generation);        
        while (generation < SIZE_GENERATION){
            for (int i = 0; i < pop.length; i++){
                Individual indPai[] = new Individual[SIZE_MULT_CROSSOVER];
                for (int j = 0; j < SIZE_MULT_CROSSOVER; j++){
                    indPai[j] = opSelection.select(typeSelection);
                }
                Individual indChild;
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
            //printGeneration(generation);
        }
        printGeneration(generation);
    }
    
    public double averageFitness(Individual[] pop){
        double sum = 0;
        for (Individual individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    } 
    
    public double getFitnessBest(Individual pop[]){
        return getBestIndividual(pop).fitness;
    }
    
    public Individual getBestIndividual(Individual pop[]){
        Individual best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            Individual newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (typeProblemMaxMin == TypeProblemMaxMin.MAXIMIZATION){
                if (fitnessNewInd > fitnessBest){
                    best = newInd;
                    fitnessBest = fitnessNewInd;
                }
            }else if (typeProblemMaxMin == TypeProblemMaxMin.MINIMIZATION){
                if (fitnessNewInd < fitnessBest){
                    best = newInd;
                    fitnessBest = fitnessNewInd;
                }
            }
        }
        return best;
    }
   
    public void printGeneration(int generation){
        System.out.println("----Generation["+ generation + "]----");
        for (int i = 0; i < pop.length; i++){                        
            System.out.println(pop[i].toString() + " fitness: " + pop[i].fitness);            
        }
        System.out.println("Best Individual: " + getBestIndividual(pop));        
        System.out.println("Best Fitness   : " + getFitnessBest(pop));
        System.out.println("Media Fitness: " + averageFitness(pop));
    }   
}
