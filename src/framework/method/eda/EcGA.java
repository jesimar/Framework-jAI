/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.eda;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class EcGA {
    
    private final Random rnd = new Random();
    private final int N = 100; //tamanho da população
    
    private final int S = 3;//tamanho do torneio 
    private final double rateCrossover = 0.8;    
    
    private final IndividualB pop[] = new IndividualB[N];
    
    public EcGA(){
        
    }   
    
    public void exec(){        
        
    }
    
    private void initialize(){
        for (int i = 0 ; i < N; i++){
            pop[i] = new IndividualB();
            pop[i].initializeRND();
        }
    }
    
    private double evaluate(IndividualB ind){        
        return ind.evaluate();
    }
    
}
