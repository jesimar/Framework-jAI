/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.operator.mutation;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class OperatorMutation {
    
    private final Random rnd = new Random();
    
    public OperatorMutation(){
        
    }
    
    public void mutation(IndividualB individual, TypeMutation typeMutation){
        if (typeMutation == TypeMutation.UNIFORM){
            mutationUniform(individual);
        }else if (typeMutation == TypeMutation.LIMIT){
            mutationLimit(individual);
        }else if (typeMutation == TypeMutation.CREEP){
            mutationCreep(individual);
        }
    }
    
    private void mutationUniform(IndividualB ind){
        int index = rnd.nextInt(IndividualB.N);
        ind.value[index] = ind.value[index] == 0 ? 1: 0;
    }
    
    private void mutationLimit(IndividualB ind){
                    
    }
    
    private void mutationCreep(IndividualB ind){
        
    }
    
}
