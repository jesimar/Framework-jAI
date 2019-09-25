/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.get;

import framework.problem.IndividualB;

/**
 *
 * @author jesimar
 */
public class GerarETestar {
    
    private final int SIZE_EVALUATE = 100;    

    public GerarETestar() {
        
    }    
    
    public void exec(){
        IndividualB bestSolution = new IndividualB();
        for (int i = 0; i < SIZE_EVALUATE; i++){
            IndividualB ind = new IndividualB();
            ind.initializeRND();
            System.out.println("Individual: " + ind.toString());
            System.out.println("Evaluate: " + ind.evaluate());
            updateSolution(ind, bestSolution);
        }
        System.out.println("Best Individual: " + bestSolution.toString());
        System.out.println("Evaluate: " + bestSolution.evaluate());
    }       
    
    private void updateSolution(IndividualB sol, IndividualB bestSolution){        
        if (sol.evaluate() > bestSolution.evaluate()){
            bestSolution.value = sol.value;
        }        
    }
    
}
