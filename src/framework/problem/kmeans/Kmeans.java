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
public class Kmeans {
    
    private final int n;
    private final int k;
    private final int D = 2;
    private final Vector data[];
    private final Vector centroid[];
    
    public Kmeans(int n, int k){
        this.n = n;
        this.k = k;
        data = new Vector[n];
        for (int i = 0; i < n; i++){
            data[i] = new Vector(D);
        }
        centroid = new Vector[k];
        for (int nc = 0; nc < k; nc++){
            centroid[nc] = new Vector(D);
        }
    }
    
    public void initData(){
        for (int i = 0; i < n; i++){                        
            if (i % 2 == 0){
                data[i].setCoord(0, (int)(Math.random() * 10));
                data[i].setCoord(1, (int)(Math.random() * 10));
            }else{
                data[i].setCoord(0, (int)(Math.random() * 10) + 10);
                data[i].setCoord(1, (int)(Math.random() * 10) + 10);
            }
        }        
    }
    
    public void printData(){
        for (int i = 0; i < n; i++){                                           
            System.out.printf("%s%d\n", data[i], data[i].getLabel());
        }
    }
    
    public void initCentroidRandom(){
        for (int nc = 0; nc < k; nc++){            
            int cx = (int)(Math.random()*20);
            int cy = (int)(Math.random()*20);
            centroid[nc].setCoord(0, cx);
            centroid[nc].setCoord(1, cy);
        }
    }
    
    public void updateCentroid(){        
        for (int nc = 0; nc < k; nc++){
            int cx = 0;
            int cy = 0;
            int qtd = 0;
            for (int i = 0; i < n; i++){            
                if (data[i].getLabel() == nc){
                    cx += data[i].getCoord(0);
                    cy += data[i].getCoord(1);
                    qtd++;
                }
            }
            cx = cx/qtd;
            cy = cy/qtd;
            centroid[nc].setCoord(0, cx);
            centroid[nc].setCoord(1, cy);
        }
    }
    
    public void descoveryGroup(){
        for (int i = 0; i < n; i++){
            double smallDist = Integer.MAX_VALUE;
            int label = -1;
            for (int nc = 0; nc < k; nc++){
                double dist = euclidianDistance(
                        data[i].getCoord(0), data[i].getCoord(1), 
                        centroid[nc].getCoord(0), centroid[nc].getCoord(1));                
                if (dist < smallDist){
                    smallDist = dist;
                    label = nc;
                }
            }
            data[i].setLabel(label);
        }
    }
    
    public void exec(){
        initCentroidRandom();
        for (int i = 0; i < 10; i++){
            descoveryGroup();
            updateCentroid();
        }
    }
    
    private double euclidianDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
}
