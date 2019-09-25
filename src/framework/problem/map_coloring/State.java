/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.map_coloring;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jesimar
 */
public class State {
        
    private String name;
    private String color;
    private final List<State> adjacent = new LinkedList<>();    
    
    public State(String name){
        this.name = name;
        this.color = ".";
    }
    
    public State(String name, String color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<State> getAdjacent() {
        return adjacent;
    }

    public void addAdjacent(State adjacent) {
        this.adjacent.add(adjacent);
    }
            
    public void setAdjacent(List<State> adjacents) {
        for (State adj : adjacents){
            this.adjacent.add(adj);
        }
    }
}
