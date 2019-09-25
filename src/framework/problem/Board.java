/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem;

/**
 *
 * @author jesimar
 */
public abstract class Board {
    
    public int DIM_X;
    public int DIM_Y;
    public final char board[][];
    public static int cost;
    public int level = 0;
    
    public Board(int DIM_X, int DIM_Y){
        this.DIM_X = DIM_X;
        this.DIM_Y = DIM_Y;
        board = new char[DIM_Y][DIM_X];
    }
    
    public void printBoard() {
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                System.out.printf("%c ", board[i][j]);
            }
            System.out.println();
        }
    }
}
