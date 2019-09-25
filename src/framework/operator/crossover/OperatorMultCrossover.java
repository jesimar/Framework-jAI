/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.operator.crossover;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class OperatorMultCrossover {
    
    private final Random rnd = new Random();
    
    public OperatorMultCrossover(){
        
    }
    
    public IndividualB crossover(IndividualB ind1, IndividualB ind2, 
            TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2);
        }
        return null;
    }        
    
    public IndividualB crossover(IndividualB ind1, IndividualB ind2, IndividualB ind3, 
            TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2, ind3);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2, ind3);
        }
        return null;
    }
    
    public IndividualB crossover(IndividualB ind1, IndividualB ind2, IndividualB ind3, 
            IndividualB ind4, TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2, ind3, ind4);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2, ind3, ind4);
        }
        return null;
    }
    
    private IndividualB crossoverUniform(IndividualB ind1, IndividualB ind2){
        IndividualB indChild = new IndividualB();        
        for (int i = 0; i < IndividualB.N; i++){
            indChild.value[i] = (rnd.nextBoolean() ? ind1.value[i] : ind2.value[i]);
        }
        return indChild;
    }
    
    private IndividualB crossoverUniform(IndividualB ind1, IndividualB ind2, 
            IndividualB ind3){
        IndividualB indChild = new IndividualB();        
        for (int i = 0; i < IndividualB.N; i++){
            int value;
            int rand = rnd.nextInt(3);
            if (rand == 0){
                value = ind1.value[i];
            }else if (rand == 1){
                value = ind2.value[i];
            }else {
                value = ind3.value[i];
            }
            indChild.value[i] = value;
        }
        return indChild;
    }
    
    private IndividualB crossoverUniform(IndividualB ind1, IndividualB ind2, 
            IndividualB ind3, IndividualB ind4){
        IndividualB indChild = new IndividualB();        
        for (int i = 0; i < IndividualB.N; i++){
            int value;
            int rand = rnd.nextInt(4);
            if (rand == 0){
                value = ind1.value[i];
            }else if (rand == 1){
                value = ind2.value[i];
            }else if (rand == 2){
                value = ind3.value[i];
            }else {
                value = ind4.value[i];
            }
            indChild.value[i] = value;
        }
        return indChild;
    }
    
    private IndividualB crossoverOnePoint(IndividualB ind1, IndividualB ind2){
        IndividualB indChild = new IndividualB();
        int pointCut = 1 + rnd.nextInt(IndividualB.N - 1);
        for (int i = 0; i < pointCut; i++){
            indChild.value[i] = ind1.value[i];
        }
        for (int i = pointCut; i < IndividualB.N; i++){
            indChild.value[i] = ind2.value[i];
        }
        return indChild;
    }        
    
    private IndividualB crossoverOnePoint(IndividualB ind1, IndividualB ind2, 
            IndividualB ind3){        
        //Não implementado ainda
        return null;
    }
    
    private IndividualB crossoverOnePoint(IndividualB ind1, IndividualB ind2, 
            IndividualB ind3, IndividualB ind4){        
        //Não implementado ainda
        return null;
    }        
    
}
