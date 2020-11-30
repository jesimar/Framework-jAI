/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.nqueens;

import framework.problem.struct.Status;
import framework.problem.struct.TypeSearch;


/**
 *
 * @author jesimar
 */
public class SolveNQueen {
    
    private final TypeSearch typeSearch = TypeSearch.BFS; 
    public final int DIM_X = 5;
    public final int DIM_Y = 5;
    private final Board board = new StateNQueen(DIM_X, DIM_Y);
    
    public SolveNQueen() {
        solve();
    }
    
    private void solve(){
        initBoard(board);        
        AI ai = new AI(this);
        if (typeSearch == TypeSearch.BFS){
            ai.bfs(board);
        } else if (typeSearch == TypeSearch.DFS){
            ai.dfs(board);
        } else if (typeSearch == TypeSearch.RDFS){            
            ai.rdfs(board);
        } else if (typeSearch == TypeSearch.RBFS){            
            ai.rbfs(board);
        }
    }
    
    public void initBoard(Board board) {
        for (int i = 0; i < board.DIM_Y; i++) {
            for (int j = 0; j < board.DIM_X; j++) {
                board.board[i][j] = StateNQueen.CHAR_EMPTY;
            }
        }
    }
    
    public Status getStatus(Board board) {    
        //Verifica Consistencia nas colunas        
        for (int i = 0; i < board.DIM_X; i++) {
            int countCol = 0;
            for (int j = 0; j < board.DIM_Y; j++) {
                if (board.board[j][i] == StateNQueen.CHAR_QUEEN){
                    countCol++;
                }
            }
            if (countCol != 1){
                return Status.SEARCHING;
            }
        }   
        //Verifica consistencia na diagonal principal        
        for (int i = 0; i < board.DIM_Y; i++) {
            int countDiag1 = 0;
            for (int j = 0; j < board.DIM_X - i; j++) {
                if (board.board[i+j][j] == StateNQueen.CHAR_QUEEN){
                    countDiag1++;
                }
            }
            if (countDiag1 > 1){
                return Status.SEARCHING;
            }
            countDiag1 = 0;
            for (int j = 0; j < board.DIM_X - i; j++) {
                if (board.board[j][j+i] == StateNQueen.CHAR_QUEEN){
                    countDiag1++;
                }
            }
            if (countDiag1 > 1){
                return Status.SEARCHING;
            }
        }
        //Verifica consistencia na diagonal secundaria        
        for (int i = board.DIM_Y - 1; i >= 0; i--) {
            int countDiag2 = 0;
            for (int j = 0; j < board.DIM_X - (board.DIM_Y - 1 - i); j++) {
                if (board.board[i-j][j] == StateNQueen.CHAR_QUEEN){
                    countDiag2++;
                }
            }
            if (countDiag2 > 1){
                return Status.SEARCHING;
            }
            countDiag2 = 0;
            for (int j = 0; j < board.DIM_X - (board.DIM_Y - 1 - i); j++) {
                if (board.board[board.DIM_Y-1-j][(board.DIM_Y-1-i)+j] == StateNQueen.CHAR_QUEEN){
                    countDiag2++;
                }
            }
            if (countDiag2 > 1){
                return Status.SEARCHING;
            }
        }   
        
        //Verifica Consistencia nas linhas
        for (int i = 0; i < board.DIM_Y; i++) {
            int countLine = 0;
            for (int j = 0; j < board.DIM_X; j++) {
                if (board.board[i][j] == StateNQueen.CHAR_QUEEN){
                    countLine++;
                }
            }
            if (countLine != 1){                
                return Status.SEARCHING;
            }
        } 
        return Status.FINISHED;
    }
}
