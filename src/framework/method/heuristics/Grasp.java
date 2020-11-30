package framework.method.heuristics;

import framework.problem.otimization.Individual;
import framework.problem.otimization.IndividualBinary;
import framework.problem.otimization.IndividualContinuous;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class Grasp {
    
    private final Random rnd = new Random();
    
    private final int SIZE_GRASP = 100;
    private final int SIZE_LOCAL_SEARCH = 20;
    private final int SIZE_POPULATION = 10;
    
    private final Individual pop[] = new Individual[SIZE_POPULATION];
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.COSINE_MIXTURE_PROBLEM;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.CONTINUOUS;

    public Grasp() {
        
    }
    
    public void exec(){
        Individual bestSolution = grasp();
        for (int i = 0; i < SIZE_POPULATION; i++){            
            pop[i] = grasp();
            printInfo(pop[i]);
            updateSolution(pop[i], bestSolution);
        }
        System.out.println("Best Individual: " + bestSolution.toString());
        System.out.println("Evaluate: " + bestSolution.evaluate());
    }  
    
    private Individual grasp(){
        if (typeRepresentation == TypeRepresentation.BINARY){
            Individual bestSolution = new IndividualBinary(typeProblem);
            for (int i = 0; i < SIZE_GRASP; i++){
                Individual ind = greedyRandomizedConstruction();
                ind = localSearch(ind);
                updateSolution(ind, bestSolution);
            }
            return bestSolution;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            Individual bestSolution = new IndividualContinuous(typeProblem);
            for (int i = 0; i < SIZE_GRASP; i++){
                Individual ind = greedyRandomizedConstruction();
                ind = localSearch(ind);
                updateSolution(ind, bestSolution);
            }
            return bestSolution;
        }
        return null;
    }    
    
    private Individual greedyRandomizedConstruction(){
        if (typeRepresentation == TypeRepresentation.BINARY){
            Individual ind = new IndividualBinary(typeProblem);
            ind.initializeRND();
            return ind;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            Individual ind = new IndividualContinuous(typeProblem);
            ind.initializeRND();
            return ind;
        }
        return null;
    }
    
    private Individual localSearch(Individual sol){
        if (typeRepresentation == TypeRepresentation.BINARY){    
            for (int i = 0; i < SIZE_LOCAL_SEARCH; i++){
                int rndValue = rnd.nextInt(IndividualBinary.N);
                IndividualBinary newInd = new IndividualBinary(typeProblem);
                newInd.copy(sol);
                newInd.gene[rndValue] = newInd.gene[rndValue] == 0 ? 1 : 0;
                updateSolution(newInd, sol);
            }
            return sol;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            for (int i = 0; i < SIZE_LOCAL_SEARCH; i++){
                int rndValue = rnd.nextInt(IndividualContinuous.N);
                IndividualContinuous newInd = new IndividualContinuous(typeProblem);
                newInd.copy(sol);
                double inf = ((IndividualContinuous)newInd).INF_VALUE;
                double sup = ((IndividualContinuous)newInd).SUP_VALUE;
                double intervalPertubation = 10.0;
                double pertubation = ((rnd.nextDouble() - 0.5) * (sup - inf)/intervalPertubation);
                double vf = ((IndividualContinuous)newInd).gene[rndValue] + pertubation;
                if (vf >= inf && vf <= sup){
                    ((IndividualContinuous)newInd).gene[rndValue] = vf;
                }else if (vf < inf){
                    ((IndividualContinuous)newInd).gene[rndValue] = inf;
                }else {
                    ((IndividualContinuous)newInd).gene[rndValue] = sup;
                }
                updateSolution(newInd, sol);
            }
            return sol;
        }
        return null;
    }
    
    private void updateSolution(Individual sol, Individual bestSolution){
        if (typeProblemMaxMin == TypeProblemMaxMin.MAXIMIZATION){
            if (sol.evaluate() > bestSolution.evaluate()){
                if (typeRepresentation == TypeRepresentation.BINARY){
                    ((IndividualBinary)bestSolution).gene = ((IndividualBinary)sol).gene;
                }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
                    ((IndividualContinuous)bestSolution).gene = ((IndividualContinuous)sol).gene;
                }
            }
        }else if (typeProblemMaxMin == TypeProblemMaxMin.MINIMIZATION){
            if (sol.evaluate() < bestSolution.evaluate()){
                if (typeRepresentation == TypeRepresentation.BINARY){
                    ((IndividualBinary)bestSolution).gene = ((IndividualBinary)sol).gene;
                }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
                    ((IndividualContinuous)bestSolution).gene = ((IndividualContinuous)sol).gene;
                }
            }
        }
    }
    
    public void printInfo(Individual ind){
        System.out.println("Individual: " + ind.toString());
        System.out.println("Evaluate: " + ind.evaluate());
    }
    
}
