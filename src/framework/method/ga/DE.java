package framework.method.ga;

import framework.problem.otimization.Individual;
import framework.problem.otimization.IndividualBinary;
import framework.problem.otimization.IndividualContinuous;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class DE {
    
    private final Random rnd = new Random();
    
    private final int SIZE_POPULATION = 1000;
    private final int SIZE_GENERATION = 1000;
    private final int SIZE_TOURNAMENT = 6;
    private final double WEIGHT_DIFFERENTIAL = 0.5;
    private final double RATE_CROSSOVER = 0.50;
    
    private final Individual pop[] = new Individual[SIZE_POPULATION];
    private final Individual popAux[] = new Individual[SIZE_POPULATION];
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.DE;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.CONTINUOUS;
    
    public DE(){
        
    }
    
    private void initialize(){
        if (typeRepresentation == TypeRepresentation.BINARY){
            for (int i = 0; i < SIZE_POPULATION; i++){       
                pop[i] = new IndividualBinary(typeProblem);
                pop[i].initializeRND();
                pop[i].evaluate();
            }
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            for (int i = 0; i < SIZE_POPULATION; i++){       
                pop[i] = new IndividualContinuous(typeProblem);
                pop[i].initializeRND();
                pop[i].evaluate();
            }
        }
    } 
    
    private Individual difference(Individual ind1, Individual ind2){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indDiff = new IndividualBinary(typeProblem);
            for (int i = 0; i < IndividualBinary.N; i++){       
                indDiff.gene[i] = ((IndividualBinary)ind1).gene[i] - 
                        ((IndividualBinary)ind2).gene[i];
            }
            normalize(indDiff);//adicionei
            indDiff.evaluate();
            return indDiff;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indDiff = new IndividualContinuous(typeProblem);
            for (int i = 0; i < IndividualContinuous.N; i++){       
                indDiff.gene[i] = ((IndividualContinuous)ind1).gene[i] - 
                        ((IndividualContinuous)ind2).gene[i];
            }
            normalize(indDiff);//adicionei
            indDiff.evaluate();
            return indDiff;
        }
        return null;
    }
    
    private Individual mutation(Individual ind, Individual indDifference){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indMut = new IndividualBinary(typeProblem);
            for (int i = 0; i < IndividualBinary.N; i++){       
                indMut.gene[i] = ((IndividualBinary)ind).gene[i] + 
                        1 * ((IndividualBinary)indDifference).gene[i];
            }
            normalize(indMut);
            indMut.evaluate();
            return indMut;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indMut = new IndividualContinuous(typeProblem);
            for (int i = 0; i < IndividualContinuous.N; i++){       
                indMut.gene[i] = ((IndividualContinuous)ind).gene[i] + 
                        WEIGHT_DIFFERENTIAL * ((IndividualContinuous)indDifference).gene[i];
            }
            normalize(indMut);
            indMut.evaluate();
            return indMut;
        }
        return null;
    }       
    
    private Individual crossover(Individual indTarget, Individual indMutation){
        if (typeRepresentation == TypeRepresentation.BINARY){
            Individual indCross = new IndividualBinary(typeProblem);
            for (int i = 0; i < IndividualBinary.N; i++){ 
                if (rnd.nextDouble() < RATE_CROSSOVER){
                    ((IndividualBinary)indCross).gene[i] = ((IndividualBinary)indTarget).gene[i];
                }else {
                    ((IndividualBinary)indCross).gene[i] = ((IndividualBinary)indMutation).gene[i];
                }
            }
            indCross.evaluate();
            return indCross;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            Individual indCross = new IndividualContinuous(typeProblem);
            int sigma = rnd.nextInt(IndividualContinuous.N);
            for (int i = 0; i < IndividualContinuous.N; i++){ 
                if (rnd.nextDouble() < RATE_CROSSOVER || i == sigma){
                    ((IndividualContinuous)indCross).gene[i] = ((IndividualContinuous)indMutation).gene[i];
                }else {
                    ((IndividualContinuous)indCross).gene[i] = ((IndividualContinuous)indTarget).gene[i];
                }
            }
            normalize(indCross);//adicionei
            indCross.evaluate();
            return indCross;
        }
        return null;
    }
    
    private void normalize(Individual ind){
        if (typeRepresentation == TypeRepresentation.BINARY){
            for (int i = 0; i < IndividualBinary.N; i++){     
                int gen = ((IndividualBinary)ind).gene[i];
                ((IndividualBinary)ind).gene[i] = (gen >= 1) ? 1 : 0;
            }
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            for (int i = 0; i < IndividualContinuous.N; i++){  
                double gen = ((IndividualContinuous)ind).gene[i];
                double sup = ((IndividualContinuous)ind).SUP_VALUE;
                double inf = ((IndividualContinuous)ind).INF_VALUE;
                if (gen > inf && gen < sup){
                    ((IndividualContinuous)ind).gene[i] = gen;
                } else if (gen >= sup){
                    ((IndividualContinuous)ind).gene[i] = sup;
                } else if (gen <= inf){
                    ((IndividualContinuous)ind).gene[i] = inf;
                }
            }
        }
    }
    
    private Individual selectRandom() {
        int randomId = rnd.nextInt(SIZE_POPULATION);
        return pop[randomId];
    }

    private Individual selectTournament() {
        int bestId = rnd.nextInt(SIZE_POPULATION);
        double bestInd = pop[bestId].evaluate();
        for (int j = 0; j < SIZE_TOURNAMENT - 1; j++) {
            int newId = rnd.nextInt(SIZE_POPULATION);
            double newInd = pop[newId].evaluate();
            if (typeProblemMaxMin == TypeProblemMaxMin.MAXIMIZATION){
                if (newInd > bestInd) {
                    bestInd = newInd;
                    bestId = newId;
                }
            }else if (typeProblemMaxMin == TypeProblemMaxMin.MINIMIZATION){
                if (newInd < bestInd) {
                    bestInd = newInd;
                    bestId = newId;
                }
            }
        }
        return pop[bestId];
    }
    
    public void exec(){
        initialize();
        for (int generation = 0; generation < SIZE_GENERATION; generation++){
            for (int j = 0; j < SIZE_POPULATION; j++){
                Individual indTarget = selectTournament();
                Individual ind1 = selectRandom();
                Individual ind2 = selectRandom();
                Individual ind3 = selectRandom();
                Individual indDifference = difference(ind1, ind2);
                Individual indMutation = mutation(ind3, indDifference);
                Individual indCrossover = crossover(indMutation, indTarget);            
//                System.out.println(ind1.toString() + "   eval1: " + ind1.evaluate());
//                System.out.println(ind2.toString() + "   eval2: " + ind2.evaluate());
//                System.out.println(indDifference.toString() + "   evalD: " + indDifference.evaluate());
//                System.out.println(ind3.toString() + "   eval3: " + ind3.evaluate());            
//                System.out.println(indMutation.toString() + "   evalM: " + indMutation.evaluate());
//                System.out.println(indTarget.toString() + "   evalT: " + indTarget.evaluate());
//                System.out.println(indCrossover.toString() + "   evalC: " + indCrossover.evaluate());
                if (typeProblemMaxMin == TypeProblemMaxMin.MAXIMIZATION){
                    if (indCrossover.fitness > indTarget.fitness){
                        popAux[j] = indCrossover;
                    }else{
                        popAux[j] = indTarget;
                    }
                }else if (typeProblemMaxMin == TypeProblemMaxMin.MINIMIZATION){
                    if (indCrossover.fitness < indTarget.fitness){
                        popAux[j] = indCrossover;
                    }else{
                        popAux[j] = indTarget;
                    }
                }
            }
            for (int j = 0; j < pop.length; j++){
                pop[j] = popAux[j];
            }
            printGeneration(generation);
        }
    }
    
    public double averageFitness(Individual[] pop){
        double sum = 0;
        for (Individual individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    }   
    
    public double getFitnessBest(Individual pop[]){
        return getBestIndividual(pop).fitness;
    }
    
    public Individual getBestIndividual(Individual pop[]){
        Individual best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            Individual newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (typeProblemMaxMin == TypeProblemMaxMin.MAXIMIZATION){
                if (fitnessNewInd > fitnessBest){
                    best = newInd;
                    fitnessBest = fitnessNewInd;
                }
            }else if (typeProblemMaxMin == TypeProblemMaxMin.MINIMIZATION){
                if (fitnessNewInd < fitnessBest){
                    best = newInd;
                    fitnessBest = fitnessNewInd;
                }
            }
        }
        return best;
    }
    
    public void printGeneration(int generation){
        System.out.println("----Generation["+ generation + "]----");
//        for (int i = 0; i < pop.length; i++){                        
//            System.out.println(pop[i].toString() + " fitness: " + pop[i].fitness);            
//        }        
        System.out.println("Best Individual: " + getBestIndividual(pop));        
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
