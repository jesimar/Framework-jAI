package framework.problem;

import framework.problem.struct.TypeProblemSolved;
import framework.operator.stop.Stop;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class IndividualBinary extends Individual implements Comparable<IndividualBinary>{
    
    public static final int N = 10;
    
    public double INF_VALUE = 0;
    public double SUP_VALUE = 1;
    
    public int[] gene;
    private final TypeProblemSolved typeProblem;
    
    public IndividualBinary(TypeProblemSolved typeProblem){
        this.typeProblem = typeProblem;
        gene = new int[N];
    }
    
    @Override
    public void initializeRND(){
        for (int i = 0; i < N; i++){
            gene[i] = new Random().nextInt(2);
        }
    }
    
    @Override
    public double evaluate(){
        Stop.actualEvaluation++;
        if (typeProblem == TypeProblemSolved.ONE_MAX){
            return evaluateOneMax();
        }else if (typeProblem == TypeProblemSolved.BIN_INT){
            return evaluateBinMax();
        }else if (typeProblem == TypeProblemSolved.TRAP){
            return evaluateTrap();
        }else if (typeProblem == TypeProblemSolved.SENHA){
            return evaluateSenha();
        }else if (typeProblem == TypeProblemSolved.DECEPTIVE_3){
            return evaluate3Deceptive();
        }else if (typeProblem == TypeProblemSolved.APOSTILA_TIA){
            return evaluateApostilaTia();
        }
        return 0;
    }
    
    @Override
    public void copy(Individual ind){
        for (int i = 0; i < gene.length; i++){
            gene[i] = ((IndividualBinary)ind).gene[i];
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
    
    @Override
    public int compareTo(IndividualBinary newInd) {
        if (this.fitness < newInd.fitness) {
            return -1;
        }
        if (this.fitness > newInd.fitness) {
            return 1;
        }
        return 0;
    }
    
    //Solução: f(x) = N       [1, 1, 1, 1, ...]
    private int evaluateOneMax(){
        int sum = 0; 
        for (int i = 0; i < N; i++){
            sum += gene[i];
        }
        fitness = sum;
        return sum;
    }
    
    //Solução: f(x) = 2^N-1       [1, 1, 1, 1, ...]
    private int evaluateBinMax(){        
        int sum = 0;  
        for (int i = 0; i < N; i++){
            sum += Math.pow(2, N-i-1) * gene[i];
        }
        fitness = sum;
        return sum;
    }
    
    //Solução: f(x) = N       [1, 1, 1, 1, ...]
    private int evaluateTrap(){        
        int sum = 0;  
        for (int i = 0; i < N; i++){
            sum += gene[i];
        }
        if (sum == N){
            sum = N;
        }else {
            sum = N - 1 - sum;
        }
        fitness = sum;
        return sum;
    }
    
    //Solução: f(x) = 1       [1, 1, 1, 1, ...]
    private int evaluateSenha(){
        int sum = 0;          
        for (int i = 0; i < N; i++){
            sum += gene[i];
        }
        if (sum == N){
            sum = 1;
        }else {
            sum = 0;
        }
        fitness = sum;
        return sum;
    }
    
    //Solução: f(x) = 1    qualquer cadeia com somente dois uns ex: [1, 0, 1, 0, ... 0]
    private double evaluate3Deceptive(){        
        double sum = 0;  
        for (int i = 0; i < N; i++){
            sum += gene[i];
        }
        if (sum == 0){
            sum = 0.9;
        }else if (sum == 1){
            sum = 0.8;
        }else if (sum == 2){
            sum = 1.0;
        }else {
            sum = 0.0;
        }
        fitness = sum;
        return sum;
    }
    
    private double evaluateApostilaTia(){   
        //max f(x) = x * sen(10 * pi * x) + 1       com x ∈ [-1, 2]
        //f(x)* = 2.85027        com x = 1.85055
        double sum = 0;
        int max = 2;
        int min = -1;       
        for (int i = 0; i < N; i++){
            sum += Math.pow(2, N-i-1) * gene[i];
        }
        double x = min + (max - min) * sum/(Math.pow(2, N)-1);
        fitness = x * Math.sin(10*Math.PI*x) + 1;
        return fitness;
    }
              
}
