/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.problem;

import java.util.Random;

/**
 *
 * @author jesimar
 */
public class IndividualDE extends aIndividual implements Comparable<IndividualDE>{   
    
    private final Random rnd = new Random();
    
    public final double INF_VALUE = -1;
    public final double SUP_VALUE = 2;
    
    public static final int N = 2;
    public double value[];   
    
    public double fitness = -1;
    
    public IndividualDE(){
        value = new double[N];
    }    
    
    @Override
    public void initializeRND(){
        for (int i = 0; i < N; i++){
            value[i] = INF_VALUE + (SUP_VALUE - INF_VALUE) * rnd.nextDouble();
        }
    }   
    
    @Override
    public double evaluate() {
        //f (x, y ) = xsin(4πx) − ysin(4πy + π) + 1, x,y ∈ [−1, 2].
        double PI = Math.PI;
        double sum = value[0] * Math.sin(4*PI*value[0]) - value[1] * Math.sin(4*PI*value[1] + PI) + 1;        
        fitness = sum;
        return sum;
    }
    
    @Override
    public void copy(IndividualB ind){
        for (int i = 0; i < value.length; i++){
            value[i] = ind.value[i];
        }
    }
    
    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < value.length; i++){
            str += value[i] + " ";
        }
        str += "]";
        return str;
    }
    
    @Override
    public int compareTo(IndividualDE newInd) {
        if (this.fitness < newInd.fitness) {
            return -1;
        }
        if (this.fitness > newInd.fitness) {
            return 1;
        }
        return 0;
    }
   
}
