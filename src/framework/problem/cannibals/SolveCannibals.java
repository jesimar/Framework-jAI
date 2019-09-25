/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.cannibals;

import framework.problem.Status;
import framework.problem.TypeSearch;


/**
 *
 * @author jesimar
 */
public class SolveCannibals {
    
    private final TypeSearch typeSearch = TypeSearch.RBFS; 
    public final int DIM_X = 2;
    public final int DIM_Y = 3;
    private final StateCannibals board = new StateCannibals(DIM_X, DIM_Y);
    private final StateCannibals boardFinal = new StateCannibals(DIM_X, DIM_Y);
    
    public SolveCannibals() {
        solve();
    }
    
    private void solve(){
        initBoard(board);   
        finalBoard();
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
    
    /*
     * Estado inicial do problema
     *                  [Lado1 , Lado2]
     *    Canibais      [3     , 0    ]
     *    Missionarios  [3     , 0    ]
     *    Barco         [1     , 0    ]
     */
    public void initBoard(StateCannibals board) {
        board.state[0][0] = 3;
        board.state[0][1] = 0;
        board.state[1][0] = 3;
        board.state[1][1] = 0;
        board.state[2][0] = 1;
        board.state[2][1] = 0;
    }
            
    /*
     * Estado final do problema
     *                  [Lado1 , Lado2]
     *    Canibais      [0     , 3    ]
     *    Missionarios  [0     , 3    ]
     *    Barco         [0     , 1    ]
     */
    private void finalBoard(){        
        boardFinal.state[0][0] = 0;
        boardFinal.state[0][1] = 3;
        boardFinal.state[1][0] = 0;
        boardFinal.state[1][1] = 3; 
        boardFinal.state[2][0] = 0;
        boardFinal.state[2][1] = 1;
    }
    
    public Status getStatus(StateCannibals board) {        
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                if (board.state[i][j] != boardFinal.state[i][j]){
                    return Status.SEARCHING;
                }
            }
        }
        return Status.FINISHED;
    }
}
