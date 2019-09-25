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
public class IndividualC extends aIndividual{   
    
    private final Random rnd = new Random();
    
    private final TypeModal typeModal = TypeModal.BI_MODAL;
    private final double INF_VALUE = 0;
    private final double SUP_VALUE = 5;
    public final double INTER_VALUE = 2.5;    
    
    public static final int N = 1;
    public double value[];   
    
    public double fitness = -1;
    
    public IndividualC(){
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
        double sum = 0;
        double diff = (SUP_VALUE - INF_VALUE);
        double PHI = 1.61803398;
        if (typeModal == TypeModal.MONO_MODAL){            
            for (int i = 0; i < IndividualC.N; i++) {
                sum += diff - Math.abs(Math.PI - value[i]);
            }
        }else if (typeModal == TypeModal.BI_MODAL){
            for (int i = 0; i < IndividualC.N; i++) {
                sum += Math.max(diff - Math.abs(Math.PI - value[i]), 
                        diff - Math.abs(PHI - value[i]));
            }
        }
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
        
    public void sampleMonoModal(double mean[], double stdDev[]) {
        for (int i = 0; i < N; i++){
            do{
                value[i] = mean[i] + stdDev[i] * rnd.nextGaussian();
            }while(value[i] <= INF_VALUE || value[i] >= SUP_VALUE);
        }
    }
    
    public void sampleBiModal(double mean[][], double stdDev[][]) {
        if (rnd.nextBoolean()){
            for (int i = 0; i < N; i++){
                do{
                    value[i] = mean[0][i] + stdDev[0][i] * rnd.nextGaussian();
                }while(value[i] <= INF_VALUE || value[i] >= SUP_VALUE);
            }
        }else{
            for (int i = 0; i < N; i++){
                do{
                    value[i] = mean[1][i] + stdDev[1][i] * rnd.nextGaussian();
                }while(value[i] <= INF_VALUE || value[i] >= SUP_VALUE);
            }
        }
    }
}
