package framework.problem.otimization;

import framework.problem.struct.TypeModal;
import framework.problem.struct.TypeProblemSolved;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class IndividualC extends Individual{   
    
    private final Random rnd = new Random();
    
    private final TypeModal typeModal = TypeModal.BI_MODAL;
    private final double INF_VALUE = 0;
    private final double SUP_VALUE = 5;
    public final double INTER_VALUE = 2.5;    
    
    public static final int N = 1;
    public double[] gene;
    private final TypeProblemSolved typeProblem;
    
    public IndividualC(TypeProblemSolved typeProblem){
        this.typeProblem = typeProblem;
        gene = new double[N];
    }    
    
    @Override
    public void initializeRND(){
        for (int i = 0; i < N; i++){
            gene[i] = INF_VALUE + (SUP_VALUE - INF_VALUE) * rnd.nextDouble();
        }
    }   
    
    @Override
    public double evaluate() {
        double sum = 0;
        double diff = (SUP_VALUE - INF_VALUE);
        double PHI = 1.61803398;
        if (typeModal == TypeModal.MONO_MODAL){            
            for (int i = 0; i < IndividualC.N; i++) {
                sum += diff - Math.abs(Math.PI - gene[i]);
            }
        }else if (typeModal == TypeModal.BI_MODAL){
            for (int i = 0; i < IndividualC.N; i++) {
                sum += Math.max(diff - Math.abs(Math.PI - gene[i]), 
                        diff - Math.abs(PHI - gene[i]));
            }
        }
        fitness = sum;
        return sum;
    }
    
    @Override
    public void copy(Individual ind){
        for (int i = 0; i < gene.length; i++){
            gene[i] = ((IndividualC)ind).gene[i];
        }
    }
    
    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < gene.length; i++){
            str += gene[i] + " ";
        }
        str += "]";
        return str;
    } 
        
    public void sampleMonoModal(double mean[], double stdDev[]) {
        for (int i = 0; i < N; i++){
            do{
                gene[i] = mean[i] + stdDev[i] * rnd.nextGaussian();
            }while(gene[i] <= INF_VALUE || gene[i] >= SUP_VALUE);
        }
    }
    
    public void sampleBiModal(double mean[][], double stdDev[][]) {
        if (rnd.nextBoolean()){
            for (int i = 0; i < N; i++){
                do{
                    gene[i] = mean[0][i] + stdDev[0][i] * rnd.nextGaussian();
                }while(gene[i] <= INF_VALUE || gene[i] >= SUP_VALUE);
            }
        }else{
            for (int i = 0; i < N; i++){
                do{
                    gene[i] = mean[1][i] + stdDev[1][i] * rnd.nextGaussian();
                }while(gene[i] <= INF_VALUE || gene[i] >= SUP_VALUE);
            }
        }
    }
}
