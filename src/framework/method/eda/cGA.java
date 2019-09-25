/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.eda;

import framework.problem.IndividualB;
import framework.util.IO;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class cGA {
    
    private final Random rnd = new Random();
    private final int N = 50;//Precisão e velocidade do ajuste da probabilidade
    
    private IndividualB indWinner = new IndividualB();
    private IndividualB indLoser = new IndividualB();
    
    public cGA(){
        
    }        
    
    private void initProbability(Double probability[]){
        for (int i = 0 ; i < probability.length; i++){
            probability[i] = 0.5;
        }
    }
    
    private IndividualB generate(Double probability[]){
        IndividualB ind = new IndividualB();
        for (int i = 0 ; i < ind.value.length; i++){
            ind.value[i] = rnd.nextDouble() < probability[i] ? 1 : 0;
        }
        return ind;
    }
    
    private void compete(IndividualB indA, IndividualB indB){
        if (indA.evaluate() > indB.evaluate()){
            indWinner = indA;
            indLoser = indB;
        }else{
            indWinner = indB;
            indLoser = indA;
        }
    }
    
    private void updateProbability(Double probability[]){
        for (int i = 0; i < probability.length; i++){
            if (indWinner.value[i] != indLoser.value[i]){
                if (indWinner.value[i] == 1){
                    probability[i] += 1.0/N;
                }else{
                    probability[i] -= 1.0/N;
                }
            }
        }
    }
    
    private boolean checkConverged(Double probability[]){
        for (Double prob : probability) {
            if (prob > 0.0 && prob < 1.0) {
                return false;
            }
        }
        return true;
    }
    
    public void exec(){
        Double probability[] = new Double[IndividualB.N];
        initProbability(probability);
        System.out.print("Probability: ");
        IO.print(probability);
        int cont = 0;
        do{
            IndividualB indA = generate(probability);        
            System.out.print("Individual A: ");
            System.out.println(indA.toString());

            IndividualB indB = generate(probability);        
            System.out.print("Individual B: ");
            System.out.println(indA.toString());

            compete(indA, indB);

            System.out.print("winner: ");
            System.out.println(indWinner.toString());

            System.out.print("loser : ");
            System.out.println(indLoser.toString());

            updateProbability(probability);
            cont++;
            System.out.print("probability: ");
            IO.print(probability);
        }while(!checkConverged(probability));      
        System.out.println("Contador: " + cont);
        System.out.print("Solution Winner: ");
        System.out.println(indWinner.toString());              
        System.out.println("Best Fitness   : " + indWinner.fitness);
    }
    
}
