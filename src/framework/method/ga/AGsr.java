package framework.method.ga;

import framework.operator.crossover.OperatorCrossover;
import framework.operator.initialization.OperatorInitialization;
import framework.operator.selection.OperatorSelection;
import framework.operator.initialization.TypeInitialization;
import framework.operator.selection.TypeSelection;
import framework.operator.crossover.TypeCrossover;
import framework.problem.otimization.Individual;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeRepresentation;

/**
 * AGsr - Algoritmo Genetico Seleto Recombinativo.
 * @author Jesimar da Silva Arantes
 */
public class AGsr {
    private final int SIZE_POPULATION = 1000;  
    private final int SIZE_GENERATION = 50;
    
    private final Individual pop[] = new Individual[SIZE_POPULATION]; 
    private final Individual popAux[] = new Individual[SIZE_POPULATION]; 
            
    private final OperatorSelection opSelection;
    private final OperatorInitialization opInitialization;
    private final OperatorCrossover opCrossover;
    
    private final TypeSelection typeSelection = TypeSelection.TOURNAMENT;
    private final TypeInitialization typeInitialization = TypeInitialization.RANDOM;
    private final TypeCrossover typeCrossover = TypeCrossover.UNIFORM;
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.COSINE_MIXTURE_PROBLEM;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.CONTINUOUS;
    
    public AGsr(){
        opInitialization = new OperatorInitialization(pop, typeProblem, typeRepresentation);
        opSelection = new OperatorSelection(pop, typeProblemMaxMin);
        opCrossover = new OperatorCrossover(typeProblem, typeRepresentation);
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
                Individual indPai1 = opSelection.select(typeSelection);
                Individual indPai2 = opSelection.select(typeSelection);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
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
    
    /** 
        Ideia do Algoritmo SeletoRecombinativo.
        1. Uma população de tamanho fixo;
        2. Um Operador de seleção dos indivíduos que irão reproduzir;
        3. A nova população é composta somente pelos filhos gerados;
        4. A reprodução é realizada somente pelo operador de recombinação.
     */
}
