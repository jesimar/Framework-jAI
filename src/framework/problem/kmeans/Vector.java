/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.kmeans;

/**
 *
 * @author jesimar
 */
public class Vector {
    
    private final int D;
    private final int coord[];
    private int label;
    
    public Vector(int D){
        this.D = D;
        coord = new int[D];
    }
    
    public void setCoord(int d, int v){
        coord[d] = v;
    }
    
    public int getCoord(int d){
        return coord[d];
    }
    
    public int getLabel(){
        return label;
    }
    
    public void setLabel(int label){
        this.label = label;
    }

    @Override
    public String toString() {
        String str = "";
        for (int d = 0; d < D; d++){
            str += coord[d] + ", ";
        }
        return str;
    }        
}
