package framework.method.heuristics;

import framework.problem.Individual;
import framework.problem.IndividualBinary;
import framework.problem.IndividualContinuous;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class GerarETestar {
    
    private final int SIZE_EVALUATE = 1000000;
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.COSINE_MIXTURE_PROBLEM;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.CONTINUOUS;

    public GerarETestar() {
        
    }
    
    public void exec(){
        Individual bestSolution = initialize();
        for (int i = 0; i < SIZE_EVALUATE; i++){
            Individual ind = initialize();
            printInfo(ind);
            updateSolution(ind, bestSolution);
        }
        System.out.println("Best Individual: " + bestSolution.toString());
        System.out.println("Evaluate: " + bestSolution.evaluate());
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

    private Individual initialize(){
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
    
    public void printInfo(Individual ind){
        System.out.println("Individual: " + ind.toString());
        System.out.println("Evaluate: " + ind.evaluate());
    }
    
    /** 
        Ideia do Algoritmo Gerar e Testar.
        1. Cria uma solução;
        2. Cria uma nova solução e a compara com a solução anterior;
        3. Guarda a melhor solução das duas; 
        4. Repete o processo até atingir o número de avaliações.
     */
}
