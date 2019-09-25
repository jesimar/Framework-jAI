/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.grasp;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class Grasp {
    
    private final Random rnd = new Random();
    private final int SIZE_GRASP = 100;
    private final int SIZE_LOCAL_SEARCH = 20;
    private final int SIZE_POPULATION = 10;
    
    private final IndividualB solution[] = new IndividualB[SIZE_POPULATION];

    public Grasp() {
        
    }
    
    public void exec(){
        IndividualB bestSolution = new IndividualB();
        for (int i = 0; i < SIZE_POPULATION; i++){            
            solution[i] = grasp();
            System.out.println("Individual: " + solution[i].toString());
            System.out.println("Evaluate: " + solution[i].evaluate());
            updateSolution(solution[i], bestSolution);
        }
        System.out.println("Best Individual: " + bestSolution.toString());
        System.out.println("Evaluate: " + bestSolution.evaluate());
    }  
    
    private IndividualB grasp(){
        IndividualB bestSolution = new IndividualB();
        for (int i = 0; i < SIZE_GRASP; i++){
            IndividualB ind = greedyRandomizedConstruction();
            ind = localSearch(ind);
            updateSolution(ind, bestSolution);
        }
        return bestSolution;
    }    
    
    private IndividualB greedyRandomizedConstruction(){
        IndividualB ind = new IndividualB();
        //da para melhorar
        ind.initializeRND();
        return ind;
    }
    
    private IndividualB localSearch(IndividualB sol){
        for (int i = 0; i < SIZE_LOCAL_SEARCH; i++){
            int rndValue = rnd.nextInt(sol.value.length);
            IndividualB newInd = new IndividualB();
            newInd.copy(sol);
            newInd.value[rndValue] = newInd.value[rndValue] == 0 ? 1 : 0;
            if (newInd.evaluate() > sol.evaluate()){
                sol.value = newInd.value;
            }
        }
        return sol;
    }
    
    private void updateSolution(IndividualB sol, IndividualB bestSolution){        
        if (sol.evaluate() > bestSolution.evaluate()){
            bestSolution.value = sol.value;
        }        
    }
    
}
