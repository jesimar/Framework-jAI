package framework.method.ga;

import framework.problem.struct.TypeProblemSolved;
import framework.operator.stop.TypeStop;
import framework.operator.stop.Stop;
import framework.operator.initialization.TypeInitialization;
import framework.operator.crossover.OperatorCrossover;
import framework.operator.selection.TypeSelection;
import framework.operator.initialization.OperatorInitialization;
import framework.operator.crossover.TypeCrossover;
import framework.operator.selection.OperatorSelection;
import framework.problem.Individual;
import framework.problem.struct.TypeProblemMaxMin;
import framework.problem.struct.TypeRepresentation;

/**
 * AGsr - Algoritmo Genetico Seleto Recombinativo.
 * @author Jesimar da Silva Arantes
 */
public class AGsrTestes {
    private final int SIZE_POPULATION = 320;    
    
    private final Individual pop[] = new Individual[SIZE_POPULATION]; 
    private final Individual popAux[] = new Individual[SIZE_POPULATION]; 
            
    private final Stop stop;
    private final OperatorInitialization opInitialization;
    private final OperatorSelection opSelection;
    private final OperatorCrossover opCrossover;
    
    private final TypeStop typeStop = TypeStop.NUMBER_GENERATION;
    private final TypeInitialization typeInitialization = TypeInitialization.RANDOM;
    private final TypeSelection typeSelection = TypeSelection.TOURNAMENT;
    private final TypeCrossover typeCrossover = TypeCrossover.UNIFORM;
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.ONE_MAX;
    private final TypeProblemMaxMin typeProblemMaxMin = TypeProblemMaxMin.MAXIMIZATION;
    private final TypeRepresentation typeRepresentation = TypeRepresentation.BINARY;
        
    public AGsrTestes(){
        stop = new Stop(typeStop);
        stop.setNumberGeneration(30);
//        stop.setNumberEvaluation(1000);
//        stop.setTime(4);
        opInitialization = new OperatorInitialization(pop, typeProblem, typeRepresentation);
        opSelection = new OperatorSelection(pop, typeProblemMaxMin);
        opCrossover = new OperatorCrossover(typeProblem, typeRepresentation);
    }  
    
    public void exec(){//AGsr   
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }        
        printGeneration(generation);
        while (!stop.end()){            
            for (int i = 0; i < pop.length; i++){ 
                Individual indPai1 = opSelection.select(typeSelection);
                Individual indPai2 = opSelection.select(typeSelection);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                indChild.evaluate();
                popAux[i] = indChild;
            } 
            for (int i = 0; i < pop.length; i++){
                pop[i] = popAux[i];
            }
            generation++;
            printGeneration(generation);
        }
    }
    
    public void exec2(){//Touro(){
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }        
        printGeneration(generation);
        while (!stop.end()){
            Individual indPai1 = opSelection.selectTouroCruzador();
            for (int i = 0; i < pop.length; i++){                
                Individual indPai2 = opSelection.select(typeSelection);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                indChild.evaluate();
                popAux[i] = indChild;
            } 
            for (int i = 0; i < pop.length; i++){
                pop[i] = popAux[i];
            }
            generation++;
            printGeneration(generation);
        }
    }
    
    public void exec3(){//execAnalizesTournament(){       
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }        
        printGeneration(generation);  
        int sizeTournament = 2;
        while (generation < 10){
            for (int i = 0; i < pop.length; i++){
                Individual indPai1 = opSelection.selectTournament(sizeTournament);
                Individual indPai2 = opSelection.selectTournament(sizeTournament);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                indChild.evaluate();
                popAux[i] = indChild;
            }
            generation++;
            printGenerationPopAux(generation);
            sizeTournament *=2;
        }
    }
    
    public void exec4(){//execAnalizesTruncamento(){       
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }        
        printGeneration(generation);
        double sizeTruncation = 50.00;
        while (generation < 7){
            for (int i = 0; i < pop.length; i++){
                Individual indPai1 = opSelection.selectTruncation(sizeTruncation);
                Individual indPai2 = opSelection.selectTruncation(sizeTruncation);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                indChild.evaluate();
                popAux[i] = indChild;
            }
            generation++;
            printGenerationPopAux(generation);
            if (generation == 2){
                sizeTruncation = 30.85;
            }else if (generation == 3){
                sizeTruncation = 15.87;
            }else if (generation == 4){
                sizeTruncation = 6.68;
            }else if (generation == 5){
                sizeTruncation = 2.28;
            }else if (generation == 6){
                sizeTruncation = 0.62;
            }
        }
    }
    
    public void exec5(){//execAnalizesTruncamento(){       
        int generation = 1;
        opInitialization.initialize(typeInitialization);
        for (int i = 0; i < pop.length; i++){
            pop[i].evaluate();
        }        
        printGeneration(generation);
        double sizeTruncation = 50.00;
        while (generation < 15){
            for (int i = 0; i < pop.length; i++){
                Individual indPai1 = opSelection.selectTruncation(sizeTruncation);
                Individual indPai2 = opSelection.selectTruncation(sizeTruncation);
                Individual indChild = opCrossover.crossover(indPai1, indPai2, typeCrossover);
                indChild.evaluate();
                popAux[i] = indChild;
            }
            generation++;
            printGenerationPopAux(generation);
            if (generation == 2){
                sizeTruncation = 45.00;
            }else if (generation == 3){
                sizeTruncation = 40.00;
            }else if (generation == 4){
                sizeTruncation = 35.00;
            }else if (generation == 5){
                sizeTruncation = 30.00;
            }else if (generation == 6){
                sizeTruncation = 25.00;
            }else if (generation == 7){
                sizeTruncation = 20.00;
            }else if (generation == 8){
                sizeTruncation = 15.00;
            }else if (generation == 9){
                sizeTruncation = 10.00;
            }else if (generation == 10){
                sizeTruncation = 5.00;
            }else if (generation == 11){
                sizeTruncation = 2.50;
            }else if (generation == 12){
                sizeTruncation = 2.00;
            }else if (generation == 13){
                sizeTruncation = 1.25;
            }else if (generation == 14){
                sizeTruncation = 0.625;
            }
        }
    }
    
    public double getFitnessBest(TypeProblemSolved typeProblem, Individual pop[]){
        return getBest(typeProblem, pop).fitness;
    }
    
    public Individual getBest(TypeProblemSolved typeProblem, Individual pop[]){
        Individual best = pop[0];
        double fitnessBest = best.fitness;
        for (int i = 1; i < pop.length; i++){
            Individual newInd = pop[i];
            double fitnessNewInd = newInd.fitness;
            if (fitnessNewInd > fitnessBest){
                best = newInd;
                fitnessBest = fitnessNewInd;
            }
        }
        return best;
    }
   
    public double averageFitness(Individual[] pop){
        double sum = 0;
        for (Individual individual : pop) {
            sum += individual.fitness;
        }
        return sum/pop.length;
    }    
    
    public void printGeneration(int generation){        
        System.out.println("----Generation["+ generation + "]----");
        for (int i = 0; i < pop.length; i++){                        
//            System.out.println(pop[i].toString() + " fitness: " + pop[i].fitness);
//            System.out.print(pop[i].fitness + " ;");
        }
        System.out.println("\nMedia Fitness: " + averageFitness(pop));
//        System.out.println("Best Individual2: " + getBest(typeProblem, pop));
//        System.out.println("Best Fitness   : " + getFitnessBest(typeProblem, pop));
    }
    
    public void printGenerationPopAux(int generation){        
        System.out.println("----Generation["+ generation + "]----");
        for (int i = 0; i < pop.length; i++){
//            System.out.print(popAux[i].fitness + " ;");
        }
        System.out.println("\nMedia Fitness: " + averageFitness(popAux));
    }
}
