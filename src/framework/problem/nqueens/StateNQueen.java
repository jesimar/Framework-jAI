/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.nqueens;

import framework.problem.Board;


/**
 *
 * @author jesimar
 */
public class StateNQueen extends Board{

    public static final char CHAR_EMPTY = '.';
    public static final char CHAR_QUEEN = 'o';
    
    public StateNQueen(int dimX, int dimY) {
        super(dimX, dimY);
    }
    
    public StateNQueen(Board table) {
        super(table.DIM_X, table.DIM_Y);
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                board[i][j] = table.board[i][j];
            }
        }        
    }
    
    public void action(int line, int col){
        board[line][col] = CHAR_QUEEN;
    }              
}
