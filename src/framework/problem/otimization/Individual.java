package framework.problem.otimization;

/**
 *
 * @author Jesimar da Silva Arantes
 */
public abstract class Individual {
    
    public abstract void initializeRND();
    
    public abstract double evaluate();
    
    public abstract void copy(Individual ind);
    
    public double fitness = -1;
}
