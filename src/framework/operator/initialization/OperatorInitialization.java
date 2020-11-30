package framework.operator.initialization;

import framework.problem.IndividualBinary;
import framework.problem.IndividualContinuous;
import framework.problem.Individual;
import framework.problem.struct.TypeProblemSolved;
import framework.problem.struct.TypeRepresentation;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class OperatorInitialization {
    
    private final Individual pop[];
    private final TypeRepresentation typeRepresentation;
    private final TypeProblemSolved typeProblem;
   
    public OperatorInitialization(Individual pop[], TypeProblemSolved typeProblem, 
            TypeRepresentation typeRepresentation){
        this.pop = pop;
        this.typeProblem = typeProblem;
        this.typeRepresentation = typeRepresentation;
    }
    
    public void initialize(TypeInitialization typeInitialization){
        if (typeInitialization == TypeInitialization.RANDOM){
            initializeRND();
        }
    }
    
    private void initializeRND(){
        if (typeRepresentation == TypeRepresentation.BINARY){
            for (int i = 0 ; i < pop.length; i++){
                pop[i] = new IndividualBinary(typeProblem);
                pop[i].initializeRND();
            }
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            for (int i = 0 ; i < pop.length; i++){
                pop[i] = new IndividualContinuous(typeProblem);
                pop[i].initializeRND();
            }
        }
    }    
}
