/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.method.eda;

import framework.problem.otimization.IndividualC;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class UMDAcBimodal {

    private final int SIZE_POP = 50;
    private final int SIZE_SUB_POP = 10;    
    private final int SIZE_EVALUATE = 100;
    private final int SIZE_TOURNAMENT = 6;
    private final IndividualC pop[] = new IndividualC[SIZE_POP];
    private final IndividualC subPop[] = new IndividualC[SIZE_SUB_POP];
    
    private final int bimodal = 2;    
    private final double mean[][] = new double[bimodal][IndividualC.N];
    private final double stdDev[][] = new double[bimodal][IndividualC.N];
    private final Random rnd = new Random();

    public UMDAcBimodal() {

    }

    private void generate() {
        System.out.println("----Begin Generate----");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualC(null);
            pop[i].initializeRND();
            System.out.println(pop[i].toString());
        }
        System.out.println("----End Generate----");
    }

    private void select() {
        System.out.println("----Begin Select----");
        for (int i = 0; i < SIZE_SUB_POP; i++) {
            subPop[i] = selectTournament();
            System.out.println(subPop[i].toString() + "   eval: " + subPop[i].evaluate());
        }
        System.out.println("----End Select----");
    }

    private IndividualC selectTournament() {
        int bestId = rnd.nextInt(SIZE_POP);
        double bestInd = pop[bestId].evaluate();
        for (int j = 0; j < SIZE_TOURNAMENT - 1; j++) {
            int newId = rnd.nextInt(SIZE_POP);
            double newInd = pop[newId].evaluate();
            if (newInd > bestInd) {
                bestInd = newInd;
                bestId = newId;
            }
        }
        return pop[bestId];
    }

    private void mean(int i) {
        double sumMean1 = 0;
        int n1 = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            if (subPop[j].gene[i] < subPop[j].INTER_VALUE){
                sumMean1 += subPop[j].gene[i];
                n1++;
            }
        }
        if (n1 != 0){
            mean[0][i] = sumMean1 / n1;
        }else {
            mean[0][i] = 0.0;
        }
        
        double sumMean2 = 0;
        int n2 = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            if (subPop[j].gene[i] >= subPop[j].INTER_VALUE){
                sumMean2 += subPop[j].gene[i];
                n2++;
            }
        }
        if (n2 != 0){
            mean[1][i] = sumMean2 / n2;
        }else {
            mean[1][i] = 0.0;
        }        
    }

    private void stdDev(int i) {
        double sumStdDev1 = 0;
        int n1 = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            if (subPop[j].gene[i] < subPop[j].INTER_VALUE){
                sumStdDev1 += Math.pow(subPop[j].gene[i] - mean[0][i], 2.0);
                n1++;
            }
        }
        if (n1 != 0){
            stdDev[0][i] = Math.sqrt(sumStdDev1 / n1);
        }else {
            stdDev[0][i] = 1;
        }
        
        double sumStdDev2 = 0;
        int n2 = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            if (subPop[j].gene[i] >= subPop[j].INTER_VALUE){
                sumStdDev2 += Math.pow(subPop[j].gene[i] - mean[1][i], 2.0);
                n2++;
            }
        }
        if (n2 != 0){
            stdDev[1][i] = Math.sqrt(sumStdDev2 / n2);
        }else {
            stdDev[1][i] = 1;
        }
    }

    private void probability() {
        for (int i = 0; i < IndividualC.N; i++) {
            mean(i);
            stdDev(i);
        }        
        System.out.print("\nMean[0] : ");
        for (int i = 0; i < IndividualC.N; i++) {            
            System.out.print(mean[0][i] + ", ");
        }
        System.out.print("\nMean[1] : ");
        for (int i = 0; i < IndividualC.N; i++) {            
            System.out.print(mean[1][i] + ", ");
        }
        System.out.print("\nStdev[0] : ");
        for (int i = 0; i < IndividualC.N; i++) {            
            System.out.print(stdDev[0][i] + ", ");
        }
        System.out.print("\nStdev[1] : ");
        for (int i = 0; i < IndividualC.N; i++) {            
            System.out.print(stdDev[1][i] + ", ");
        }
    }

    private void sample() {
        System.out.println("\n---Begin Sample---");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualC(null);
            pop[i].sampleBiModal(mean, stdDev);
            System.out.println(pop[i].toString());
        }
        System.out.println("---End Sample---");
    }

    public void exec() {
        System.out.println("---BEGIN---");
        generate();
        for (int l = 0; l < SIZE_EVALUATE; l++) {
            select();
            probability();
            sample();
        }
        System.out.println("---END---");
    }

}
