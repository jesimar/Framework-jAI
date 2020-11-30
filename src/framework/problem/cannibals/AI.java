/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.cannibals;

import framework.problem.struct.Status;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jesimar
 */
public class AI {

    private final SolveCannibals cannibalsProblem;
    private final boolean PRINT_SOLUTIONS = true;

    public AI(SolveCannibals cannibalsProblem) {
        this.cannibalsProblem = cannibalsProblem;
    }

    /*
     * Busca em largura.     
     */
    public boolean bfs(StateCannibals state) {
        if (cannibalsProblem.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        StateCannibals.cost = 0;
        List<StateCannibals> frontier = new LinkedList<>();
        List<StateCannibals> explored = new LinkedList<>();
        frontier.add(state);        
        while (!frontier.isEmpty()) {
            StateCannibals node = frontier.remove(0);
            explored.add(node);
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (cannibalsProblem.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            createChild(node, frontier, explored);
        }
        return false;
    }

    private void createChild(StateCannibals node, List<StateCannibals> frontier, 
            List<StateCannibals> explored) {
        Side side = node.getSide();
        int factor = side == Side.SIDE_1 ? 1 : -1;
        if (node.getNumCannibals(side) >= 1) {
            addChild(node, frontier, explored, 1 * factor, 0);
            if (node.getNumCannibals(side) >= 2) {
                addChild(node, frontier, explored, 2 * factor, 0);
            }
        }
        if (node.getNumMissionaries(side) >= 1) {
            addChild(node, frontier, explored, 0, 1 * factor);
            if (node.getNumMissionaries(side) >= 2) {
                addChild(node, frontier, explored, 0, 2 * factor);
            }
        }
        if (node.getNumCannibals(side) >= 1 && node.getNumMissionaries(side) >= 1) {
            addChild(node, frontier, explored, 1 * factor, 1 * factor);
        }
    }
    
    private void addChild(StateCannibals node, List<StateCannibals> frontier, 
            List<StateCannibals> explored, int moveC, int moveM){
        StateCannibals child = new StateCannibals(node);
        child.actionMoveCannibals(moveC);
        child.actionMoveMissionaries(moveM);
        child.actionMoveBoat();
        if (!violeted(child)){
            if (!explored.contains(child) && !frontier.contains(child)){
                frontier.add(child);
                StateCannibals.cost++;
            }
        }
    }
    
    private boolean violeted(StateCannibals state){
        if (state.getNumMissionaries(Side.SIDE_1) > 0 && 
                state.getNumCannibals(Side.SIDE_1) > state.getNumMissionaries(Side.SIDE_1)){
            return true;
        } else if (state.getNumMissionaries(Side.SIDE_2) > 0 && 
                state.getNumCannibals(Side.SIDE_2) > state.getNumMissionaries(Side.SIDE_2)){
            return true;
        }
        return false;
    }

    /*
     * Busca em profundidade.     
     */
    public boolean dfs(StateCannibals state) {
        if (cannibalsProblem.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        StateCannibals.cost = 0;
        Stack<StateCannibals> stack = new Stack<>();
        List<StateCannibals> explored = new LinkedList<>();
        stack.push(state);
        while (!stack.isEmpty()) {
            StateCannibals node = stack.pop();
            explored.add(node);
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (cannibalsProblem.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            createChildStack(node, stack, explored);
        }
        return false;
    }
    
    private void createChildStack(StateCannibals node, Stack<StateCannibals> frontier, 
            List<StateCannibals> explored) {
        Side side = node.getSide();
        int factor = side == Side.SIDE_1 ? 1 : -1;
        if (node.getNumCannibals(side) >= 1) {
            addChildStack(node, frontier, explored, 1 * factor, 0);
            if (node.getNumCannibals(side) >= 2) {
                addChildStack(node, frontier, explored, 2 * factor, 0);
            }
        }
        if (node.getNumMissionaries(side) >= 1) {
            addChildStack(node, frontier, explored, 0, 1 * factor);
            if (node.getNumMissionaries(side) >= 2) {
                addChildStack(node, frontier, explored, 0, 2 * factor);
            }
        }
        if (node.getNumCannibals(side) >= 1 && node.getNumMissionaries(side) >= 1) {
            addChildStack(node, frontier, explored, 1 * factor, 1 * factor);
        }
    }
    
    private void addChildStack(StateCannibals node, Stack<StateCannibals> frontier, 
            List<StateCannibals> explored, int moveC, int moveM){
        StateCannibals child = new StateCannibals(node);
        child.actionMoveCannibals(moveC);
        child.actionMoveMissionaries(moveM);
        child.actionMoveBoat();
        if (!violeted(child)){
            if (!explored.contains(child) && !frontier.contains(child)){
                frontier.push(child);
                StateCannibals.cost++;
            }
        }
    }

    /*
    * Busca em profundidade recursiva
    */    
    public boolean rdfs(StateCannibals state) {
        StateCannibals.cost = 0;
        List<StateCannibals> explored = new LinkedList<>();
        return rdfs(state, explored);
    }
    
    
    private boolean rdfs(StateCannibals state, List<StateCannibals> explored) {
        explored.add(state);
        if (PRINT_SOLUTIONS) {
            print(state);
        }
        if (cannibalsProblem.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        Side side = state.getSide();
        int factor = side == Side.SIDE_1 ? 1 : -1;
        if (state.getNumCannibals(side) >= 1) {
            if (addChildRecursive(state, explored, 1 * factor, 0)){
                return true;
            }
            if (state.getNumCannibals(side) >= 2) {
                if (addChildRecursive(state, explored, 2 * factor, 0)){
                    return true;
                }
            }
        }
        if (state.getNumMissionaries(side) >= 1) {
            if (addChildRecursive(state, explored, 0, 1 * factor)){
                return true;
            }
            if (state.getNumMissionaries(side) >= 2) {
                if (addChildRecursive(state, explored, 0, 2 * factor)){
                    return true;
                }
            }
        }
        if (state.getNumCannibals(side) >= 1 && state.getNumMissionaries(side) >= 1) {
            if (addChildRecursive(state, explored, 1 * factor, 1 * factor)){
                return true;
            }
        }
        return false;
    }
    
    private boolean addChildRecursive(StateCannibals node, List<StateCannibals> explored, 
            int moveC, int moveM){
        StateCannibals child = new StateCannibals(node);
        child.actionMoveCannibals(moveC);
        child.actionMoveMissionaries(moveM);
        child.actionMoveBoat();
        if (!violeted(child)){
            if (!explored.contains(child)){
                StateCannibals.cost++;
                if (rdfs(child, explored)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Busca em largura recursiva     
     */
    public boolean rbfs(StateCannibals board) {
        StateCannibals.cost = 0;
        List<StateCannibals> frontier = new LinkedList<>();
        List<StateCannibals> explored = new LinkedList<>();
        return rbfs(board, frontier, explored);
    }
    
    /*
     * Busca em largura recursiva     
     */
    public boolean rbfs(StateCannibals state, List<StateCannibals> frontier, 
            List<StateCannibals> explored) {
        explored.add(state);
        if (PRINT_SOLUTIONS) {
            print(state);
        }
        if (cannibalsProblem.getStatus(state) == Status.FINISHED) {
            print(state);
            return true;
        }
        Side side = state.getSide();
        int factor = side == Side.SIDE_1 ? 1 : -1;
        if (state.getNumCannibals(side) >= 1) {
            addChildFrontier(state, frontier, 1 * factor, 0);
            if (state.getNumCannibals(side) >= 2) {
                addChildFrontier(state, frontier, 2 * factor, 0);
            }
        }
        if (state.getNumMissionaries(side) >= 1) {
            addChildFrontier(state, frontier, 0, 1 * factor);
            if (state.getNumMissionaries(side) >= 2) {
                addChildFrontier(state, frontier, 0, 2 * factor);                
            }
        }
        if (state.getNumCannibals(side) >= 1 && state.getNumMissionaries(side) >= 1) {
            addChildFrontier(state, frontier, 1 * factor, 1 * factor);
        }        
        if (!frontier.isEmpty()){
            StateCannibals elem = frontier.remove(0);
            if (rbfs(elem, frontier, explored)) {
                return true;
            }
        }   
        return false;
    }
    
    private void addChildFrontier(StateCannibals node, List<StateCannibals> frontier, 
            int moveC, int moveM){
        StateCannibals child = new StateCannibals(node);
        child.actionMoveCannibals(moveC);
        child.actionMoveMissionaries(moveM);
        child.actionMoveBoat();
        if (!violeted(child)){
            if (!frontier.contains(child)){
                StateCannibals.cost++;
                frontier.add(child);
            }
        }        
    }

    /*
     * Busca em profundidade limitada.     
     */
    public boolean dls() {
        return false;
    }

    /*
     * Busca em profundidade limitada iterativa.     
     */
    public boolean ids() {
        return false;
    }

    /*
     * Busca em largura bidirecional
     */
    public boolean bidirectionalSearch() {
        return false;
    }

    /*
     * Busca em Greedy Best-First
     */
    public boolean gbf() {
        return false;
    }
    
    private void print(StateCannibals node) {
        System.out.println("------");
        System.out.println("Cost: " + StateCannibals.cost);
        node.printState();
        System.out.println("");
    }
}
