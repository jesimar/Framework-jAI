package framework.method.ga;

import framework.problem.IndividualBinary;
import framework.problem.struct.TypeProblemSolved;
import java.util.Random;

/**
 * FALTA TERMINAR 
 * @author Jesimar da Silva Arantes
 */
public class PSO {
    
    private final int SIZE_POPULATION = 10;
    private final int SIZE_GENERATION = 10;
    private final int SIZE_TOURNAMENT = 6;
    private final Random rnd = new Random();
    private final IndividualBinary pop[] = new IndividualBinary[SIZE_POPULATION];
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.ONE_MAX;
    
    public PSO(){
        
    }
    
    private void initialize(){
        for (int j = 0; j < SIZE_POPULATION; j++){       
            pop[j] = new IndividualBinary(typeProblem);
            pop[j].initializeRND();
        }
    }    

    private IndividualBinary selectTournament() {
        int bestId = rnd.nextInt(SIZE_POPULATION);
        double bestInd = pop[bestId].evaluate();
        for (int j = 0; j < SIZE_TOURNAMENT - 1; j++) {
            int newId = rnd.nextInt(SIZE_POPULATION);
            double newInd = pop[newId].evaluate();
            if (newInd > bestInd) {
                bestInd = newInd;
                bestId = newId;
            }
        }
        return pop[bestId];
    }
    
    public void exec(){
        initialize();
        for (int i = 0; i < SIZE_GENERATION; i++){
            IndividualBinary ind1 = selectTournament();
            IndividualBinary ind2 = selectTournament();
            IndividualBinary ind3 = selectTournament();
            System.out.println(ind1.toString() + "   eval: " + ind1.evaluate());
            System.out.println(ind2.toString() + "   eval: " + ind2.evaluate());
            System.out.println(ind3.toString() + "   eval: " + ind3.evaluate());
        }
    }        
}
