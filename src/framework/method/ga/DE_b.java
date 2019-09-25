/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.method.ga;

import framework.problem.IndividualB;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class DE_b {
    
    private final int SIZE_POPULATION = 100;
    private final int SIZE_GENERATION = 20;
    private final int SIZE_TOURNAMENT = 6;
    private final int WEIGHT_DIFERENTIAL = 1;
    private final double RATE_CROSSOVER = 0.50;
    private final Random rnd = new Random();
    private final IndividualB pop[] = new IndividualB[SIZE_POPULATION];
    private final IndividualB popAux[] = new IndividualB[SIZE_POPULATION];
    
    public DE_b(){
        
    }
    
    private void initialize(){
        for (int i = 0; i < SIZE_POPULATION; i++){       
            pop[i] = new IndividualB();
            pop[i].initializeRND();
            pop[i].evaluate();
        }
    } 
    
    private IndividualB difference(IndividualB ind1, IndividualB ind2){
        IndividualB indDiff = new IndividualB();
        for (int i = 0; i < IndividualB.N; i++){       
            indDiff.value[i] = ind1.value[i] - ind2.value[i];
        }
        indDiff.evaluate();
        return indDiff;
    }
    
    private IndividualB mutation(IndividualB ind, IndividualB indDifference){
        IndividualB indMut = new IndividualB();
        for (int i = 0; i < IndividualB.N; i++){       
            indMut.value[i] = ind.value[i] + WEIGHT_DIFERENTIAL * indDifference.value[i];
            normalize(indMut);
        }
        indMut.evaluate();
        return indMut;
    }       
    
    private IndividualB crossover(IndividualB indTarget, IndividualB indMutation){
        IndividualB indCross = new IndividualB();
        for (int i = 0; i < IndividualB.N; i++){ 
            if (rnd.nextDouble() < RATE_CROSSOVER){
                indCross.value[i] = indTarget.value[i];
            }else {
                indCross.value[i] = indMutation.value[i];
            }
        }
        indCross.evaluate();
        return indCross;
    }
    
    private void normalize(IndividualB ind){
        for (int i = 0; i < IndividualB.N; i++){       
            ind.value[i] = (ind.value[i] > 1) ? 1 : ind.value[i];
            ind.value[i] = (ind.value[i] < 0) ? 0 : ind.value[i];
        }
    }
    
    private IndividualB selectRandom() {
        int randomId = rnd.nextInt(SIZE_POPULATION);
        return pop[randomId];
    }

    private IndividualB selectTournament() {
        int bestId = rnd.nextInt(SIZE_POPULATION);
        double bestInd = pop[bestId].evaluate();
        for (int j = 0; j < SIZE_TOURNAMENT - 1; j++) {
            int newId = rnd.nextInt(SIZE_POPULATION);
            double newInd = pop[newId].evaluate();
            if (newInd > bestInd) {
                bestInd = newInd;
                bestId = newId;
            }
        }
        return pop[bestId];
    }
    
    public void exec(){
        initialize();
        for (int generation = 0; generation < SIZE_GENERATION; generation++){
            for (int j = 0; j < SIZE_POPULATION; j++){
//                IndividualB indTarget = pop[j];
                IndividualB indTarget = selectTournament();
                IndividualB ind1 = selectRandom();
                IndividualB ind2 = selectRandom();
                IndividualB ind3 = selectRandom();
                IndividualB indDifference = difference(ind1, ind2);
                IndividualB indMutation = mutation(ind3, indDifference);
                IndividualB indCrossover = crossover(indMutation, indTarget);            
//                System.out.println(ind1.toString() + "   eval1: " + ind1.evaluate());
//                System.out.println(ind2.toString() + "   eval2: " + ind2.evaluate());
//                System.out.println(indDifference.toString() + "   evalD: " + indDifference.evaluate());
//                System.out.println(ind3.toString() + "   eval3: " + ind3.evaluate());            
//                System.out.println(indMutation.toString() + "   evalM: " + indMutation.evaluate());
//                System.out.println(indTarget.toString() + "   evalT: " + indTarget.evaluate());
//                System.out.println(indCrossover.toString() + "   evalC: " + indCrossover.evaluate());
                if (indCrossover.compareTo(indTarget) >= 0){
                    popAux[j] = indCrossover;                   
                }else{
                    popAux[j] = indTarget;
                }
            }
            for (int j = 0; j < pop.length; j++){
                pop[j] = popAux[j];
            }
            printGeneration(generation);
        }
    }
    
    public double averageFitness(IndividualB[] pop){
        double sum = 0;
        for (IndividualB individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    }   
    
    public double getFitnessBest(IndividualB pop[]){
        return getBest(pop).fitness;
    }
    
    public IndividualB getBest(IndividualB pop[]){
        IndividualB best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            IndividualB newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (fitnessNewInd > fitnessBest){
                best = newInd;
                fitnessBest = fitnessNewInd;
            }
        }
        return best;
    }
    
    public void printGeneration(int generation){
        System.out.println("----Generation["+ generation + "]----");
//        for (int i = 0; i < pop.length; i++){                        
//            System.out.println(pop[i].toString() + " fitness: " + pop[i].fitness);            
//        }        
        System.out.println("Best Individual: " + getBest(pop));        
        System.out.println("Best Fitness   : " + getFitnessBest(pop));
        System.out.println("Media Fitness: " + averageFitness(pop));
    } 
    
    /**
     * Algoritmo Evolução Diferencial.
        1. Inicializar parâmetros;
        2. Inicializar população inicial randomicamente;
        3. Avaliar população;
        4. Repetir até que critério de parada seja satisfeito;
            4.1 Selecionar randomicamente 3 indivíduos;
            4.2 Aplicar fator de diferenciação, mutação, crossover;
            4.3 Comparar o indivíduo atual com o indivíduo gerado e selecionar
                o que possui melhor fitness para população atual;
            4.4 Avaliar o indivíduo selecionado;
            4.5 Selecionar nova população;
        5. Fim (da repetição).
     */
}
