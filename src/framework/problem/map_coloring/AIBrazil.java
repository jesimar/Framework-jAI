/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.map_coloring;

import framework.problem.Status;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jesimar
 */
public class AIBrazil {

    private final SolveMap solveMap;
    private final boolean PRINT_SOLUTIONS = true;

    public AIBrazil(SolveMap solveMap) {
        this.solveMap = solveMap;
    }

    /*
     * Busca em largura.     
     */
    public boolean bfs(BrazilMap state) {
        if (solveMap.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        state.level = -1;
        BrazilMap.cost = 0;
        List<BrazilMap> frontier = new LinkedList<>();
        List<BrazilMap> explored = new LinkedList<>();
        frontier.add(state);
        while (!frontier.isEmpty()) {
            BrazilMap node = frontier.remove(0);
            explored.add(node);
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (solveMap.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            for (String color : node.getDomain()) {
                BrazilMap child = new BrazilMap(node);
                child.level = node.level + 1;
                if (child.level < BrazilMap.MAX_LEVEL) {
                    child.action(child.level, color);
                    if (!explored.contains(child) && !frontier.contains(child)) {
                        System.out.println("color: " + color);
                        child.printState();
                        frontier.add(child);
                        BrazilMap.cost++;
                    }
                }
            }
        }
        return false;
    }

    /*
     * Busca em profundidade.     
     */
    public boolean dfs(BrazilMap state) {
        if (solveMap.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        BrazilMap.cost = 0;
        state.level = -1;
        Stack<BrazilMap> stack = new Stack<>();
        List<BrazilMap> explored = new LinkedList<>();
        stack.push(state);
        while (!stack.isEmpty()) {
            BrazilMap node = stack.pop();
            explored.add(node);
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (solveMap.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            for (String color : node.getDomain()) {
                BrazilMap child = new BrazilMap(node);
                child.level = node.level + 1;
                if (child.level < BrazilMap.MAX_LEVEL) {
                    child.action(child.level, color);
                    if (!violated(child)) {
                        if (!explored.contains(child) && !stack.contains(child)) {
                            System.out.println("color: " + color);
                            child.printState();
                            stack.push(child);
                            BrazilMap.cost++;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean violated(BrazilMap child){
        for (State state : child.getStates()) {            
            List<State> ajd = state.getAdjacent();
            for (State s : ajd){                
                if (!state.getColor().equals(".")){
                    if (state.getColor().equals(s.getColor())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void print(BrazilMap node) {
        System.out.println("------");
        System.out.println("Cost: " + BrazilMap.cost);
        System.out.println("Level: " + node.level);
        node.printState();
        System.out.println("");
    }
}
