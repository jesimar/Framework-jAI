package framework.operator.crossover;

import framework.problem.Individual;
import framework.problem.IndividualBinary;
import framework.problem.IndividualContinuous;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class OperatorMultCrossover {
    
    private final Random rnd = new Random();
    private final TypeProblemSolved typeProblem;
    private final TypeRepresentation typeRepresentation;
    
    public OperatorMultCrossover(TypeProblemSolved typeProblem, 
            TypeRepresentation typeRepresentation){
        this.typeProblem = typeProblem;
        this.typeRepresentation = typeRepresentation;
    }
    
    public Individual crossover(Individual ind1, Individual ind2, 
            TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2);
        }
        return null;
    }        
    
    public Individual crossover(Individual ind1, Individual ind2, Individual ind3, 
            TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2, ind3);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2, ind3);
        }
        return null;
    }
    
    public Individual crossover(Individual ind1, Individual ind2, Individual ind3, 
            Individual ind4, TypeCrossover typeCrossover){
        if (typeCrossover == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2, ind3, ind4);
        }else if (typeCrossover == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2, ind3, ind4);
        }
        return null;
    }
    
    private Individual crossoverUniform(Individual ind1, Individual ind2){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indChild = new IndividualBinary(typeProblem);        
            for (int i = 0; i < IndividualBinary.N; i++){
                indChild.gene[i] = (rnd.nextBoolean() ? ((IndividualBinary)ind1).gene[i] : 
                        ((IndividualBinary)ind2).gene[i]);
            }
            return indChild;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indChild = new IndividualContinuous(typeProblem);        
            for (int i = 0; i < IndividualContinuous.N; i++){
                indChild.gene[i] = (rnd.nextBoolean() ? ((IndividualContinuous)ind1).gene[i] : 
                        ((IndividualContinuous)ind2).gene[i]);
            }
            return indChild;
        }
        return null;
    }
    
    private Individual crossoverUniform(Individual ind1, Individual ind2, 
            Individual ind3){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indChild = new IndividualBinary(typeProblem);        
            for (int i = 0; i < IndividualBinary.N; i++){
                int value;
                int rand = rnd.nextInt(3);
                if (rand == 0){
                    value = ((IndividualBinary)ind1).gene[i];
                }else if (rand == 1){
                    value = ((IndividualBinary)ind2).gene[i];
                }else {
                    value = ((IndividualBinary)ind3).gene[i];
                }
                indChild.gene[i] = value;
            }
            return indChild;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indChild = new IndividualContinuous(typeProblem);        
            for (int i = 0; i < IndividualContinuous.N; i++){
                double value;
                int rand = rnd.nextInt(3);
                if (rand == 0){
                    value = ((IndividualContinuous)ind1).gene[i];
                }else if (rand == 1){
                    value = ((IndividualContinuous)ind2).gene[i];
                }else {
                    value = ((IndividualContinuous)ind3).gene[i];
                }
                indChild.gene[i] = value;
            }
            return indChild;
        }
        return null;
    }
    
    private Individual crossoverUniform(Individual ind1, Individual ind2, 
            Individual ind3, Individual ind4){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indChild = new IndividualBinary(typeProblem);        
            for (int i = 0; i < IndividualBinary.N; i++){
                int value;
                int rand = rnd.nextInt(4);
                if (rand == 0){
                    value = ((IndividualBinary)ind1).gene[i];
                }else if (rand == 1){
                    value = ((IndividualBinary)ind2).gene[i];
                }else if (rand == 2){
                    value = ((IndividualBinary)ind3).gene[i];
                }else {
                    value = ((IndividualBinary)ind4).gene[i];
                }
                indChild.gene[i] = value;
            }
            return indChild;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indChild = new IndividualContinuous(typeProblem);        
            for (int i = 0; i < IndividualContinuous.N; i++){
                double value;
                int rand = rnd.nextInt(3);
                if (rand == 0){
                    value = ((IndividualContinuous)ind1).gene[i];
                }else if (rand == 1){
                    value = ((IndividualContinuous)ind2).gene[i];
                }else if (rand == 2){
                    value = ((IndividualContinuous)ind3).gene[i];
                }else {
                    value = ((IndividualContinuous)ind4).gene[i];
                }
                indChild.gene[i] = value;
            }
            return indChild;
        }
        return null;
    }
    
    private Individual crossoverOnePoint(Individual ind1, Individual ind2){
        if (typeRepresentation == TypeRepresentation.BINARY){
            IndividualBinary indChild = new IndividualBinary(typeProblem);
            int pointCut = 1 + rnd.nextInt(IndividualBinary.N - 1);
            for (int i = 0; i < pointCut; i++){
                indChild.gene[i] = ((IndividualBinary)ind1).gene[i];
            }
            for (int i = pointCut; i < IndividualBinary.N; i++){
                indChild.gene[i] = ((IndividualBinary)ind2).gene[i];
            }
            return indChild;
        }else if (typeRepresentation  == TypeRepresentation.CONTINUOUS){
            IndividualContinuous indChild = new IndividualContinuous(typeProblem);
            int pointCut = 1 + rnd.nextInt(IndividualContinuous.N - 1);
            for (int i = 0; i < pointCut; i++){
                indChild.gene[i] = ((IndividualContinuous)ind1).gene[i];
            }
            for (int i = pointCut; i < IndividualContinuous.N; i++){
                indChild.gene[i] = ((IndividualContinuous)ind2).gene[i];
            }
            return indChild;
        }
        return null;
    }    
    
    private Individual crossoverOnePoint(Individual ind1, Individual ind2, 
            Individual ind3){        
        //Não implementado ainda
        return null;
    }
    
    private Individual crossoverOnePoint(Individual ind1, Individual ind2, 
            Individual ind3, Individual ind4){        
        //Não implementado ainda
        return null;
    }        
    
}
