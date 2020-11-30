/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.map_coloring;

import framework.problem.struct.Status;
import framework.problem.struct.TypeSearch;
import java.util.List;

/**
 *
 * @author jesimar
 */
public class SolveMap {
    
    private final TypeSearch typeSearch = TypeSearch.DFS;
    private final AustraliaMap australiaMap = new AustraliaMap();
    private final BrazilMap brazilMap = new BrazilMap();
    
    public SolveMap() {
        solve2();
    }
    
    private void solve(){
        initState(australiaMap);
        AIAustralia ai = new AIAustralia(this);
        if (typeSearch == TypeSearch.BFS){
            ai.bfs(australiaMap);
        } 
        else if (typeSearch == TypeSearch.DFS){
            ai.dfs(australiaMap);
        } 
//        else if (typeSearch == TypeSearch.RDFS){
//            ai.rdfs(australia);
//        } else if (typeSearch == TypeSearch.RBFS){            
//            ai.rbfs(australia);
//        }
    }
    
    private void solve2(){
        initState(brazilMap);
        AIBrazil ai = new AIBrazil(this);
        if (typeSearch == TypeSearch.BFS){
            ai.bfs(brazilMap);
        } 
        else if (typeSearch == TypeSearch.DFS){
            ai.dfs(brazilMap);
        } 
//        else if (typeSearch == TypeSearch.RDFS){
//            ai.rdfs(australia);
//        } else if (typeSearch == TypeSearch.RBFS){            
//            ai.rbfs(australia);
//        }
    }
   
    public void initState(AustraliaMap autralia) {
        autralia = new AustraliaMap();
    }
    
    public void initState(BrazilMap brazil) {
        brazil = new BrazilMap();
    }
    
    public Status getStatus(AustraliaMap australia) {        
        for (State state : australia.getStates()) {            
            if (state.getColor().equals(".")){
                return Status.SEARCHING;                
            }            
        }
        for (State state : australia.getStates()) {            
            List<State> ajd = state.getAdjacent();
            for (State s : ajd){
                if (state.getColor().equals(s.getColor())){
                    return Status.SEARCHING;                
                }
            }
        }
        return Status.FINISHED;
    }
    
    public Status getStatus(BrazilMap brasil) {        
        for (State state : brasil.getStates()) {            
            if (state.getColor().equals(".")){
                return Status.SEARCHING;                
            }            
        }
        for (State state : brasil.getStates()) {            
            List<State> ajd = state.getAdjacent();
            for (State s : ajd){
                if (state.getColor().equals(s.getColor())){
                    return Status.SEARCHING;                
                }
            }
        }
        return Status.FINISHED;
    }
}
