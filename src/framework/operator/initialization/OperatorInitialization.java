/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.operator.initialization;

import framework.problem.IndividualB;

/**
 *
 * @author jesimar
 */
public class OperatorInitialization {
    
    private final IndividualB pop[];
   
    public OperatorInitialization(IndividualB pop[]){
        this.pop = pop;
    }
    
    public void initialize(TypeInitialization typeInitialization){
        if (typeInitialization == TypeInitialization.RANDOM){
            initializeRND();
        }
    }
    
    private void initializeRND(){
        for (int i = 0 ; i < pop.length; i++){
            pop[i] = new IndividualB();
            pop[i].initializeRND();
        }
    }    
}
