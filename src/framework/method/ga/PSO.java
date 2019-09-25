/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.ga;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class PSO {
    
    private final int SIZE_POPULATION = 10;
    private final int SIZE_GENERATION = 10;
    private final int SIZE_TOURNAMENT = 6;
    private final Random rnd = new Random();
    private final IndividualB pop[] = new IndividualB[SIZE_POPULATION];
    
    public PSO(){
        
    }
    
    private void initialize(){
        for (int j = 0; j < SIZE_POPULATION; j++){       
            pop[j] = new IndividualB();
            pop[j].initializeRND();
        }
    }    

    private IndividualB selectTournament() {
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
            IndividualB ind1 = selectTournament();
            IndividualB ind2 = selectTournament();
            IndividualB ind3 = selectTournament();
            System.out.println(ind1.toString() + "   eval: " + ind1.evaluate());
            System.out.println(ind2.toString() + "   eval: " + ind2.evaluate());
            System.out.println(ind3.toString() + "   eval: " + ind3.evaluate());
        }
    }        
}
