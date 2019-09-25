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
public class OperatorCrossover {
    
    private final Random rnd = new Random();
    
    public OperatorCrossover(){
        
    }
    
    public IndividualB crossover(IndividualB ind1, IndividualB ind2, TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2);
        }else if (typeCrossover == TypeCrossover.TWO_POINT){
            return crossoverTwoPoint(ind1, ind2);
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
    
    private IndividualB crossoverTwoPoint(IndividualB ind1, IndividualB ind2){
        //Não implementado ainda
        return null;
    }
    
}
