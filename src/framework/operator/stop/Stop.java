package framework.operator.stop;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public class Stop {
    
    private final TypeStop typeStop;
    
    private int numberGeneration;
    private int numberEvaluation;
    private int time;    
    
    private int actualGeneration = 0;
    public static int actualEvaluation = 0;
    private double timeInitial;
    
    public Stop(TypeStop typeStop){
        this.typeStop = typeStop;
    }
    
    public boolean end(){
        if (typeStop == TypeStop.NUMBER_GENERATION){
            return endGeneration();
        } else if (typeStop == TypeStop.NUMBER_EVALUATION){
            return endEvaluations();
        } else if (typeStop == TypeStop.ELAPSED_TIME){
            return endElapsedTime();
        } else if (typeStop == TypeStop.FOREVER){
            return endForever();
        } else if (typeStop == TypeStop.CONVERGENCE){
            return endConvergence();
        } else if (typeStop == TypeStop.STAGNATION_IN_EVOLUTION){
            return endStagnationInEvolution();
        }
        return false;
    }
    
    private boolean endGeneration(){
        if (actualGeneration < numberGeneration - 1){
            actualGeneration++;
            return false;
        }else{
            return true;
        }
    }
    
    private boolean endEvaluations(){
        if (actualEvaluation < numberEvaluation){
            return false;
        }else{
            return true;
        }
    }
    
    private boolean endElapsedTime(){
        double elapsedTime = (System.currentTimeMillis() - timeInitial)/1000.0;
        if (elapsedTime < time){
            return false;
        }else{
            return true;
        }
    }
    
    private boolean endForever(){
        return true;
    }
    
    //Não esta funcionando ainda
    private boolean endConvergence(){
        return true;
    }
    
    //Não esta funcionando ainda
    private boolean endStagnationInEvolution(){
        return true;
    }

    public void setNumberGeneration(int numberGeneration) {
        this.numberGeneration = numberGeneration;
    }        

    public void setNumberEvaluation(int numberEvaluation) {
        this.numberEvaluation = numberEvaluation;
    }

    //time em segundos
    public void setTime(int time) {
        this.time = time;
        this.timeInitial = System.currentTimeMillis();
    }        
}
