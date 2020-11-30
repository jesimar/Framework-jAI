package framework.method.eda;

import framework.problem.otimization.IndividualBinary;
import framework.problem.struct.TypeProblemSolved;
import framework.util.IO;
import java.util.Random;

/**
 *
 * @author jesimar
 */
public class UMDA {

    private final int SIZE_POP = 50;
    private final int SIZE_SUB_POP = 10;
    private final int SIZE_EVALUATE = 100;
    private final int SIZE_TOURNAMENT = 6;
    private final IndividualBinary pop[] = new IndividualBinary[SIZE_POP];
    private final IndividualBinary subPop[] = new IndividualBinary[SIZE_SUB_POP];
    private final Double prob[] = new Double[IndividualBinary.N];
    private final int PROBABILITY_1 = 1;
    private final int PROBABILITY_2 = 2;
    private final int functionProb = PROBABILITY_1;
    private final Random rnd = new Random();
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.ONE_MAX;

    public UMDA() {

    }

    private void generate() {
        System.out.println("----Begin Generate----");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualBinary(typeProblem);
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

    private IndividualBinary selectTournament() {
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
    
    private void initProbability(){
        for (int i = 0; i < IndividualBinary.N; i++){            
            prob[i] = 0.5;
        }
        System.out.print("Probability  : "); 
        IO.print(prob);
    }
    
    private void probability(){
        for (int i = 0; i < IndividualBinary.N; i++){
            double sum = 0;
            for (int j = 0 ; j < SIZE_SUB_POP; j++){
                sum += subPop[j].gene[i] == 1 ? 1 : 0;
            }
            prob[i] = sum/SIZE_SUB_POP;
        }
        System.out.print("Probability  : "); 
        IO.print(prob);
    }
    
    /**
     * Correção de laplace
     */
    private void probability2(){
        for (int i = 0; i < IndividualBinary.N; i++){
            double sum = 0;
            for (int j = 0 ; j < SIZE_SUB_POP; j++){
                sum += subPop[j].gene[i];
            }
            prob[i] = (sum + 1)/(SIZE_SUB_POP+2);
        }
        System.out.print("Probability  : "); 
        IO.print(prob);
    }
    
    private void sample(){
        System.out.println("---Begin Sample---");
        for (int i = 0; i < SIZE_POP; i++) {
            pop[i] = new IndividualBinary(typeProblem);
            for (int j = 0; j < pop[i].gene.length; j++){
                pop[i].gene[j] = rnd.nextDouble() < prob[j] ? 1 : 0;
            }
            System.out.println(pop[i].toString());
        }
        System.out.println("---End Sample---");
    }
    
    private boolean checkConverged(Double probability[]){        
        for (Double p : probability) {
            if (p > 0.0 && p < 1.0) {
                return false;
            }
        }
        return true;
    }

    public void exec() {
        System.out.println("---BEGIN---");
        generate();
        initProbability();
        int cont = 0;
        while (cont < SIZE_EVALUATE && !checkConverged(prob)){
            select();
            if (functionProb == PROBABILITY_1){
                probability();
            }else if (functionProb == PROBABILITY_2){
                probability2();
            }
            sample();
            cont++;
        }
        System.out.println("Best Individual: " + getBest(subPop));        
        System.out.println("Best Fitness   : " + getFitnessBest(subPop));
        System.out.println("Media Fitness: " + averageFitness(subPop));
        System.out.println("Cont: " + cont);
        System.out.println("---END---");
    }
    
    public double averageFitness(IndividualBinary[] pop){
        double sum = 0;
        for (IndividualBinary individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    }   
    
    public double getFitnessBest(IndividualBinary pop[]){
        return getBest(pop).fitness;
    }
    
    public IndividualBinary getBest(IndividualBinary pop[]){
        IndividualBinary best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            IndividualBinary newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (fitnessNewInd > fitnessBest){
                best = newInd;
                fitnessBest = fitnessNewInd;
            }
        }
        return best;
    }
}
