/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framework.operator.stop;

/**
 *
 * @author jesimar
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
        }else if (typeStop == TypeStop.NUMBER_EVALUATION){
            return endEvaluations();
        }else if (typeStop == TypeStop.TIME){
            return endTime();
        }else if (typeStop == TypeStop.CONVERGENCE){
            return endConvergence();
        }else if (typeStop == TypeStop.FOREVER){
            return endForever();
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
    
    private boolean endTime(){
        double timePassed = (System.currentTimeMillis() - timeInitial)/1000.0;
        if (timePassed < time){
            return false;
        }else{
            return true;
        }
    }
    
    //Não esta funcionando ainda
    private boolean endConvergence(){
        return true;
    }
    
    private boolean endForever(){
        return true;
    }

    public void setNumberGeneration(int numberGeneration) {
        this.numberGeneration = numberGeneration;
    }        

    public void setNumberEvaluation(int numberEvaluation) {
        this.numberEvaluation = numberEvaluation;
    }

    public void setTime(int time) {
        this.time = time;
        this.timeInitial = System.currentTimeMillis();
    }        
}
