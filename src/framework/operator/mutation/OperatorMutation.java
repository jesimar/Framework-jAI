package framework.operator.mutation;

import framework.problem.otimization.Individual;
import framework.problem.otimization.IndividualBinary;
import framework.problem.otimization.IndividualContinuous;
import framework.problem.struct.TypeRepresentation;
import java.util.Random;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class OperatorMutation {
    
    private final Random rnd = new Random();
    private final TypeRepresentation typeRepresentation;
    
    public OperatorMutation(TypeRepresentation typeRepresentation){
        this.typeRepresentation = typeRepresentation;
    }
    
    public void mutation(Individual individual, TypeMutation typeMutation){
        if (typeMutation == TypeMutation.UNIFORM){
            mutationUniform(individual);
        }else if (typeMutation == TypeMutation.LIMIT){
            mutationLimit(individual);
        }else if (typeMutation == TypeMutation.CREEP){
            mutationCreep(individual);
        }
    }
    
    private void mutationUniform(Individual ind){
        if (typeRepresentation == TypeRepresentation.BINARY){
            int index = rnd.nextInt(IndividualBinary.N);
            ((IndividualBinary)ind).gene[index] = ((IndividualBinary)ind).gene[index] == 0 ? 1: 0;
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            int index = rnd.nextInt(IndividualContinuous.N);
            double inf = ((IndividualContinuous)ind).INF_VALUE;
            double sup = ((IndividualContinuous)ind).SUP_VALUE;
            double intervalPertubation = 10.0;
            double pertubation = ((rnd.nextDouble() - 0.5) * (sup - inf)/intervalPertubation);
            double vf = ((IndividualContinuous)ind).gene[index] + pertubation;
            if (vf >= inf && vf <= sup){
                ((IndividualContinuous)ind).gene[index] = vf;
            }else if (vf < inf){
                ((IndividualContinuous)ind).gene[index] = inf;
            }else {
                ((IndividualContinuous)ind).gene[index] = sup;
            }
        }
    }
    
    private void mutationLimit(Individual ind){
        if (typeRepresentation == TypeRepresentation.BINARY){
            
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            
        }
    }
    
    private void mutationCreep(Individual ind){
        if (typeRepresentation == TypeRepresentation.BINARY){
            
        }else if (typeRepresentation == TypeRepresentation.CONTINUOUS){
            
        }
    }
    
}
