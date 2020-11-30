package framework.method.eda;

import framework.problem.otimization.IndividualBinary;
import framework.problem.struct.TypeProblemSolved;
import java.util.Random;

/**
 * FALTA TERMINAR 
 * @author Jesimar da Silva Arantes
 */
public class EcGA {
    
    private final Random rnd = new Random();
    private final int N = 100; //tamanho da população
    
    private final int S = 3;//tamanho do torneio 
    private final double rateCrossover = 0.8;    
    
    private final IndividualBinary pop[] = new IndividualBinary[N];
    
    private final TypeProblemSolved typeProblem = TypeProblemSolved.ONE_MAX;
    
    public EcGA(){
        
    }   
    
    public void exec(){        
        
    }
    
    private void initialize(){
        for (int i = 0 ; i < N; i++){
            pop[i] = new IndividualBinary(typeProblem);
            pop[i].initializeRND();
        }
    }
    
    private double evaluate(IndividualBinary ind){        
        return ind.evaluate();
    }
    
}
