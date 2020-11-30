package framework.operator.crossover;

import framework.problem.IndividualBinary;
import framework.problem.IndividualContinuous;
import framework.problem.Individual;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class OperatorCrossover {
    
    private final Random rnd = new Random();
    private final TypeProblemSolved typeProblem;
    private final TypeRepresentation typeRepresentation;
    
    public OperatorCrossover(TypeProblemSolved typeProblem, 
            TypeRepresentation typeRepresentation){
        this.typeProblem = typeProblem;
        this.typeRepresentation = typeRepresentation;
    }
    
    public Individual crossover(Individual ind1, Individual ind2, TypeCrossover typeCross){
        if (typeCross == TypeCrossover.UNIFORM){
            return crossoverUniform(ind1, ind2);
        }else if (typeCross == TypeCrossover.ONE_POINT){
            return crossoverOnePoint(ind1, ind2);
        }else if (typeCross == TypeCrossover.TWO_POINT){
            return crossoverTwoPoint(ind1, ind2);
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
    
    private Individual crossoverTwoPoint(Individual ind1, Individual ind2){
        if (typeRepresentation == TypeRepresentation.BINARY){
            
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            
        }
        return null;
    }
    
}
