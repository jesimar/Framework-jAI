/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.nqueens;

import framework.problem.struct.Status;
import framework.problem.cannibals.SolveCannibals;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jesimar
 */
public class AI {

    private SolveNQueen problem;
    private SolveCannibals cannibalsProblem;
    private final boolean PRINT_SOLUTIONS = false;

    public AI(SolveNQueen queenProblem) {
        this.problem = queenProblem;
    }
    
    public AI(SolveCannibals cannibalsProblem) {
        this.cannibalsProblem = cannibalsProblem;
    }

    /*
     * Busca em largura.     
     */
    public boolean bfs(Board board) {
        if (problem.getStatus(board) == Status.FINISHED) {
            return true;
        }
        board.level = -1;
        Board.cost = 0;
        List<Board> frontier = new LinkedList<>();
        frontier.add(board);
        while (!frontier.isEmpty()) {
            Board node = frontier.remove(0);
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (problem.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            for (int col = 0; col < board.DIM_X; col++) {
                StateNQueen child = new StateNQueen(node);
                child.level = node.level + 1;                
                if (child.level < board.DIM_X) {
                    child.action(child.level, col);
                    frontier.add(child);
                    Board.cost++;
                }
            }
        }
        return false;
    }

    /*
     * Busca em profundidade.     
     */
    public boolean dfs(Board board) {
        if (problem.getStatus(board) == Status.FINISHED) {
            print(board);
            return true;
        }
        board.level = -1;
        Board.cost = 0;
        Stack<Board> stack = new Stack<>();
        stack.push(board);
        while (!stack.isEmpty()) {
            Board node = stack.pop();
            if (PRINT_SOLUTIONS) {
                print(node);
            }
            if (problem.getStatus(node) == Status.FINISHED) {
                print(node);
                return true;
            }
            for (int col = 0; col < node.DIM_X; col++) {
                StateNQueen child = new StateNQueen(node);
                child.level = node.level + 1;
                if (child.level < child.DIM_X) {
                    child.action(child.level, col);
                    stack.push(child);
                    Board.cost++;
                }
            }
        }
        return false;
    }

    /*
    * Busca em profundidade recursiva
    */
    public boolean rdfs(Board board) {
        board.level = 0;
        Board.cost = 0;
        return rdfs(board, board.level);
    }
    
    public boolean rdfs(Board board, int level) {
        if (PRINT_SOLUTIONS) {
            print(board);
        }
        if (problem.getStatus(board) == Status.FINISHED) {
            print(board);
            return true;
        }
        for (int col = 0; col < board.DIM_X; col++) {
            StateNQueen child = new StateNQueen(board);
            child.level = level;
            if (child.level < board.DIM_X) {
                child.action(child.level, col);
                Board.cost++;
                if (rdfs(child, level + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Busca em largura recursiva     
     */
    public boolean rbfs(Board board) {
        board.level = -1;
        Board.cost = 0;
        List<Board> list = new LinkedList<>();
        return rbfs(board, list);
    }
    
    /*
     * Busca em largura recursiva     
     */
    public boolean rbfs(Board board, List<Board> list) {
        if (PRINT_SOLUTIONS) {
            print(board);
        }
        if (problem.getStatus(board) == Status.FINISHED) {
            print(board);
            return true;
        }
        for (int col = 0; col < board.DIM_X; col++) {
            StateNQueen child = new StateNQueen(board);
            child.level = board.level+1;
            if (child.level < board.DIM_X) {
                child.action(child.level, col);
                Board.cost++;
                list.add(child);
            }
        }
        if (!list.isEmpty()){
            Board elem = list.remove(0);
            if (rbfs(elem, list)) {
                return true;
            }
        }
        return false;
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

    private void print(Board node) {
        System.out.println("------");
        System.out.println("Cost: " + StateNQueen.cost);
        System.out.println("Level: " + node.level);
        node.printBoard();
        System.out.println("");
    }
}
