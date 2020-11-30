package framework.util;

/**
 *
 * @author Jesimar S. Arantes
 */
public class IO {
    
    public static void print(Double vector[]){
        for (Double vet : vector) {
            System.out.print(String.format("%.2f ", vet));
        }
        System.out.println();
    }
    
    public static void print(double vector[]){
        for (double vet : vector){
            System.out.print(String.format("%.2f ", vet));
        }
        System.out.println();
    }
    
}
