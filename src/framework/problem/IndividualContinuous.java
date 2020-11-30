package framework.problem;

import framework.operator.stop.Stop;
import framework.problem.struct.TypeProblemSolved;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class IndividualContinuous extends Individual implements Comparable<IndividualContinuous>{   
    
    private final double E = Math.E;
    private final double PI = Math.PI;
    private final Random rnd = new Random();
    
    public double INF_VALUE = -10;
    public double SUP_VALUE = 10;
    
    public static int N = 2;
    public double[] gene;
    
    private final TypeProblemSolved typeProblem;
    
    public IndividualContinuous(TypeProblemSolved typeProblem){
        this.typeProblem = typeProblem;
        if (typeProblem == TypeProblemSolved.ACKLEY_PROBLEM){
            INF_VALUE = -30;
            SUP_VALUE = 30;
            N = 10;
        }else if (typeProblem == TypeProblemSolved.ALUFFI_PENTINI_PROBLEM){
            INF_VALUE = -10;
            SUP_VALUE = 10;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.BECKER_LAGO_PROBLEM){
            INF_VALUE = -10;
            SUP_VALUE = 10;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.BOHACHEVSKY_1_PROBLEM){
            INF_VALUE = -50;
            SUP_VALUE = 50;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.BOHACHEVSKY_2_PROBLEM){
            INF_VALUE = -50;
            SUP_VALUE = 50;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.BRANIN_PROBLEM){
            INF_VALUE = -5;//problema  -5 <= x <= 10 e 0 <= y <= 15.
            SUP_VALUE = 15;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.CAMEL_BACK3){
            INF_VALUE = -5;
            SUP_VALUE = 5;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.CAMEL_BACK6){
            INF_VALUE = -5;
            SUP_VALUE = 5;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.COSINE_MIXTURE_PROBLEM){
            INF_VALUE = -1;
            SUP_VALUE = 1;
            N = 4; //N = 2
        }else if (typeProblem == TypeProblemSolved.DEKKERS_AARTS_PROBLEM){
            INF_VALUE = -20;
            SUP_VALUE = 20;
            N = 2;
        }else if (typeProblem == TypeProblemSolved.APOSTILA_TIA){
            INF_VALUE = -1;
            SUP_VALUE = 2;
            N = 1;
        }else if (typeProblem == TypeProblemSolved.DE){
            INF_VALUE = -1;
            SUP_VALUE = 2;
            N = 2;
        }
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
        Stop.actualEvaluation++;
        if (typeProblem == TypeProblemSolved.ACKLEY_PROBLEM){
            return evaluateAckley();
        }else if (typeProblem == TypeProblemSolved.ALUFFI_PENTINI_PROBLEM){
            return evaluateAluffiPentini();
        }else if (typeProblem == TypeProblemSolved.BECKER_LAGO_PROBLEM){
            return evaluateBeckerLago();
        }else if (typeProblem == TypeProblemSolved.BOHACHEVSKY_1_PROBLEM){
            return evaluateBohachevsky1();
        }else if (typeProblem == TypeProblemSolved.BOHACHEVSKY_2_PROBLEM){
            return evaluateBohachevsky2();
        }else if (typeProblem == TypeProblemSolved.BRANIN_PROBLEM){
            return evaluateBranin();
        }else if (typeProblem == TypeProblemSolved.CAMEL_BACK3){
            return evaluateCamelBack3();
        }else if (typeProblem == TypeProblemSolved.CAMEL_BACK6){
            return evaluateCamelBack6();
        }else if (typeProblem == TypeProblemSolved.COSINE_MIXTURE_PROBLEM){
            return evaluateCosineMixture();
        }else if (typeProblem == TypeProblemSolved.DEKKERS_AARTS_PROBLEM){
            return evaluateDekkersAarts();
        }else if (typeProblem == TypeProblemSolved.APOSTILA_TIA){
            return evaluateApostilaTiaContinuos();
        }else if (typeProblem == TypeProblemSolved.DE){
            return evaluateDE();
        }
        return 0;
    }
    
    @Override
    public void copy(Individual ind){
        for (int i = 0; i < gene.length; i++){
            gene[i] = ((IndividualContinuous)ind).gene[i];
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
    public int compareTo(IndividualContinuous newInd) {
        if (this.fitness < newInd.fitness) {
            return -1;
        }
        if (this.fitness > newInd.fitness) {
            return 1;
        }
        return 0;
    }
    
    private double evaluateAckley(){
        //min f(x) = -20 exp(-0.02 * sqrt(n^(-1)*sum_{i=1}^n x_i^2)) - exp(n^(-1) * sum_{i=1}^n (cos(2*pi*x_i))+20+e  com x_i ∈ [-30, 30].
        //f(x)* = 0    com n = 10     x*=0
        double partA = 0;
        double partB = 0;
        for (int i = 0; i < N; i++){
            partA += Math.pow(gene[i], 2);
            partB += Math.cos(2 * PI * gene[i]);
        }
        partA = Math.pow(E, -0.02*Math.sqrt((1.0/N)*partA));
        partB = Math.pow(E, (1.0/N)*partB);
        double sum = -20 * partA - partB + 20 + E;
        fitness = sum;
        return sum;
    }
    
    private double evaluateAluffiPentini(){
        //min f(x, y) = 0.25x^4 - 0.5x^2 + 0.1x + 0.5y^2     com x, y ∈ [-10, 10].
        //f(x,y)* = -0.3523  com x = -1.0465 e y = 0.
        double sum = 0.25 * Math.pow(gene[0], 4) - 0.5 * Math.pow(gene[0], 2) +
                0.1 * gene[0] + 0.5 * Math.pow(gene[1], 2);   
        fitness = sum;
        return sum;
    }
    
    private double evaluateBeckerLago(){
        //min f(x, y) = (|x| - 5)^2 + (|y| - 5)^2            com x, y ∈ [-10, 10].
        //f(x,y)* = 0  com x=+5 ou x=-5 e y=+5 ou y=-5. São quatros soluções.
        double sum = Math.pow(Math.abs(gene[0]) - 5, 2) + Math.pow(Math.abs(gene[1]) - 5, 2);
        fitness = sum;
        return sum;
    }
    
    private double evaluateBohachevsky1(){
        //min f(x, y) = x^2+2y^2-0.3cos(3*pi*x)-0.4cos(4*pi*y)+0.7    x, y ∈ [-50, 50].
        //f(x,y)* = 0  com x = 0 e y = 0.
        double sum = Math.pow(gene[0], 2) + 2 * Math.pow(gene[1], 2) - 0.3 * 
              Math.cos(3 * PI * gene[0]) - 0.4 * Math.cos(4 * PI * gene[1]) + 0.7;
        fitness = sum;
        return sum;
    }
    
    private double evaluateBohachevsky2(){
        //min f(x, y) = x^2+2y^2-0.3cos(3*pi*x)*cos(4*pi*y)+0.3    x, y ∈ [-50, 50].
        //f(x,y)* = 0  com x = 0 e y = 0.
        double sum = Math.pow(gene[0], 2) + 2 * Math.pow(gene[1], 2) - 0.3 * 
                  Math.cos(3 * PI * gene[0]) * Math.cos(4 * PI * gene[1]) + 0.3;
        fitness = sum;
        return sum;
    }
    
    private double evaluateBranin(){
        //min f(x, y) =  a(y - bx^2 + cx - d)^2 + g(1 - h)cos(x) + g
        //f(x,y)* = 5/(4pi) = 0.39788735773  são três soluções com (x, y)=(-pi, 12.275) ou (pi, 2.275) ou (3pi; 2.475)
        double a = 1;
        double b = 5.1/(4*PI*PI);
        double c = 5/PI;
        double d = 6;
        double g = 10;
        double h = 1.0/(8 * PI);
        double sum = a * Math.pow(gene[1] - b*gene[0]*gene[0] + c*gene[0] - d, 2) + 
                g*(1 - h)*Math.cos(gene[0]) + g;  
        fitness = sum;
        return sum;
    }
    
    private double evaluateCamelBack3(){
        //min f(x, y) = 2x^2 - 1.05x^4 + (1/6)x^6 + xy + y^2      com x, y ∈ [-5, 5].
        //f(x,y)* = 0  com (x, y) = (0, 0)
        double sum = 2*Math.pow(gene[0], 2) - 1.05 * Math.pow(gene[0], 4) + 
                (1.0/6) * Math.pow(gene[0], 6) + gene[0] * gene[1] + Math.pow(gene[1], 2);
        fitness = sum;
        return sum;
    }
    
    private double evaluateCamelBack6(){
        //min f(x, y) = 4x^2 - 2.1x^4 + (1/3)x^6 + xy - 4y^2 + 4y^4      com x, y ∈ [-5, 5].
        //f(x,y)* = -1.0316  com (x, y) = (0.089842, -0.712656) e (-0.089842, 0.712656)
        double sum = 4*Math.pow(gene[0], 2) - 2.1 * Math.pow(gene[0], 4) + 
                (1.0/3) * Math.pow(gene[0], 6) + gene[0] * gene[1] - 4 * Math.pow(gene[1], 2)
                + 4 * Math.pow(gene[1], 4);
        fitness = sum;
        return sum;
    }
    
    private double evaluateCosineMixture(){
        //min f(x) = 0.1 sum_{i=1}^n (cos(5*pi*x_i) - sum_{i=1}^n x_i^2  com x_i ∈ [-1, 1].
        //f(x)* = 0.20 se n = 2     x*=0
        //f(x)* = 0.40 se n = 4     x*=0
        double partA = 0;
        double partB = 0;
        for (int i = 0; i < N; i++){
            partA += Math.cos(5*PI * gene[i]);
            partB += Math.pow(gene[i], 2);
        }
        double sum = 0.1 * partA - partB;
        fitness = sum;
        return sum;
    }
    
    private double evaluateDekkersAarts(){
        //min f(x, y) = 10^5x^2 + y^2 - (x^2 + y^2)^2 + 10^(-5) * (x^2 + y^2)^4    com x, y ∈ [-20, 20].
        //f(x, y)* = -24777     com (x, y) = (0, 15)  e (0, -15)
        double sum = Math.pow(10, 5) * Math.pow(gene[0], 2) + Math.pow(gene[1], 2) - 
                Math.pow(Math.pow(gene[0], 2) + Math.pow(gene[1], 2), 2) + 
                Math.pow(10, -5) * Math.pow(Math.pow(gene[0], 2) + Math.pow(gene[1], 2), 4);
        fitness = sum;
        return sum;
    }
    
    private double evaluateApostilaTiaContinuos(){   
        //max f(x) = x * sen(10 * pi * x) + 1       com x ∈ [-1, 2]
        //f(x)* = 2.85027        com x = 1.85055
        double sum = gene[0] * Math.sin(10 * PI * gene[0]) + 1;
        fitness = sum;
        return sum;
    }
    
    private double evaluateDE(){
        //max f(x, y) = xsin(4πx) − ysin(4πy + π) + 1    com x, y ∈ [-1, 2]
        //f(x, y)* = 4.253898408      com (x, y) = (1.62888, 1.62888)
        double sum = gene[0] * Math.sin(4*PI*gene[0]) - gene[1] * Math.sin(4*PI*gene[1] + PI) + 1;        
        fitness = sum;
        return sum;
    }
}