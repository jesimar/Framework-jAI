/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.method.eda;

import framework.problem.otimization.IndividualMimic;
import framework.util.IO;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class MIMICcG {

    private final int SIZE_POP = 50;
    private final int SIZE_SUB_POP = 10;
    private final int SIZE_EVALUATE = 100;
    private final int SIZE_TOURNAMENT = 6;
    private final IndividualMimic pop[] = new IndividualMimic[SIZE_POP];
    private final IndividualMimic subPop[] = new IndividualMimic[SIZE_SUB_POP];
    private final double mean[] = new double[IndividualMimic.N];
    private final double stdDev[] = new double[IndividualMimic.N];
    private final Random rnd = new Random();

    public MIMICcG() {

    }

    private void generate() {
        System.out.println("----Begin Generate----");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualMimic();
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

    private IndividualMimic selectTournament() {
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
        double sumMean = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            sumMean += subPop[j].value[i];
        }
        sumMean = sumMean / SIZE_SUB_POP;
        mean[i] = sumMean;
    }

    private void stdDev(int i) {
        double sumStdDev = 0;
        for (int j = 0; j < SIZE_SUB_POP; j++) {
            sumStdDev += Math.pow(subPop[j].value[i] - mean[i], 2.0);
        }
        sumStdDev = Math.sqrt(sumStdDev / SIZE_SUB_POP);
        stdDev[i] = sumStdDev;
    }

    private void probability() {
        for (int i = 0; i < IndividualMimic.N; i++) {
            mean(i);
            stdDev(i);
        }
        System.out.print("Mean  : ");
        IO.print(mean);
        System.out.print("StdDev: ");
        IO.print(stdDev);
    }

    private void sample() {
        System.out.println("---Begin Sample---");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualMimic();
            pop[i].sample(mean, stdDev);
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
