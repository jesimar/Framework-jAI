/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.problem;

import framework.operator.stop.Stop;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class IndividualB extends aIndividual implements Comparable<IndividualB>{
    
    public static final TypeProblem typeProblem = TypeProblem.ONE_MAX;
    public static final int N = 64;    
    
    public int value[];
    
    public double fitness = -1;
    
    public IndividualB(){
        value = new int[N];
    }
    
    @Override
    public void initializeRND(){
        for (int i = 0; i < N; i++){
            value[i] = new Random().nextInt(2);
        }
    }
    
    @Override
    public double evaluate(){
        Stop.actualEvaluation++;
        if (typeProblem == TypeProblem.ONE_MAX){
            return evaluateOneMax();
        }else if (typeProblem == TypeProblem.BIN_INT){
            return evaluateBinMax();
        }else if (typeProblem == TypeProblem.TRAP){
            return evaluateTrap();
        }else if (typeProblem == TypeProblem.SENHA){
            return evaluateSenha();
        }else if (typeProblem == TypeProblem.DECEPTIVE_3){
            return evaluate3Deceptive();
        }else if (typeProblem == TypeProblem.APOSTILA_TIA){
            return evaluateApostilaTia();
        }else if (typeProblem == TypeProblem.APOSTILA_DE){
            return evaluateApostilaDE();
        }
        return 0;
    }
    
    private int evaluateOneMax(){
        int sum = 0; 
        for (int i = 0; i < N; i++){
            sum += value[i];
        }
        fitness = sum;
        return sum;
    }
    
    private int evaluateBinMax(){        
        int sum = 0;  
        for (int i = 0; i < N; i++){
            sum += Math.pow(2, N-i-1) * value[i];
        }
        fitness = sum;
        return sum;
    }
    
    private int evaluateTrap(){        
        int sum = 0;  
        for (int i = 0; i < N; i++){
            sum += value[i];
        }
        if (sum == N){
            sum = N;
        }else {
            sum = N - 1 - sum;
        }
        fitness = sum;
        return sum;
    }
    
    private int evaluateSenha(){
        int sum = 0;          
        for (int i = 0; i < N; i++){
            sum += value[i];
        }
        if (sum == N){
            sum = 1;
        }else {
            sum = 0;
        }
        fitness = sum;
        return sum;
    }
    
    private double evaluate3Deceptive(){        
        double sum = 0;  
        for (int i = 0; i < N; i++){
            sum += value[i];
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
        double sum = 0;  
        int max = 2;
        int min = -1;       
        for (int i = 0; i < N; i++){
            sum += Math.pow(2, N-i-1) * value[i];
        }
        double x = min + (max - min) * sum/(Math.pow(2, N)-1);
        fitness = x * Math.sin(10*Math.PI*x) + 1;
        return fitness;
    }
    
    private double evaluateApostilaDE(){        
        double sum = 0;  
        int max = 2;
        int min = -1;       
        for (int i = 0; i < N; i++){
            sum += Math.pow(2, N-i-1) * value[i];
        }
        double x = min + (max - min) * sum/(Math.pow(2, N)-1);
        fitness = x * Math.sin(10*Math.PI*x) + 1;
        return fitness;
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
    public int compareTo(IndividualB newInd) {
        if (this.fitness < newInd.fitness) {
            return -1;
        }
        if (this.fitness > newInd.fitness) {
            return 1;
        }
        return 0;
    }
          
}
