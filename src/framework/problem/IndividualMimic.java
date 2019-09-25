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
public class IndividualMimic {   
    
    public static final int N = 2;
    public double value[] = new double[N];     
    
    public IndividualMimic(){
        
    }
    
    public void initializeRND(){
        value[0] = 2 * Math.PI * new Random().nextDouble();
        value[1] = value[0] * 180.0 / Math.PI;
    }

    public void sample(double mean[], double stdDev[]) {
        do{
            value[0] = mean[0] + stdDev[0] * new Random().nextGaussian();
        }while(value[0] <= 0 || value[0] >= 2 * Math.PI);

        do{
            value[1] = mean[1] + stdDev[1] * new Random().nextGaussian();
        }while(value[1] <= 0 || value[1] >= 2 * 180);
    }
    
    public double evaluate() {
        double sum = 0;
        sum += 2 * Math.PI - Math.abs(Math.PI - value[0]);
        sum += (180 - Math.abs(180.0 - value[1]))/180.0;
        return sum;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < N; i++){
            str += value[i] + ", ";
        }
        str += "]";
        return str;
    }        
}
