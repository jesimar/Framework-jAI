/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.cannibals;

/**
 *
 * @author jesimar
 */
public class StateCannibals{
    
    public int DIM_X;
    public int DIM_Y;
    public final int[][] state;
    public static int cost;
    
    /*
     * Sera repesentado como uma matriz onde: 
     *                  [Lado1 , Lado2]
     *    Canibais      [nc1   , nc2  ]
     *    Missionarios  [nm1   , nm2  ]
     *    Barco         [nb1   , nb2  ]
     */
    public StateCannibals(int dimX, int dimY) {
        this.DIM_X = dimX;
        this.DIM_Y = dimY;
        state = new int[DIM_Y][DIM_X];
    }
    
    public StateCannibals(StateCannibals table) {
        this(table.DIM_X, table.DIM_Y);
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                state[i][j] = table.state[i][j];
            }
        }        
    }
    
    public void actionMoveCannibals(int numberMovesSide2){
        state[0][0] = state[0][0] - numberMovesSide2;
        state[0][1] = state[0][1] + numberMovesSide2;
    }
    
    public void actionMoveMissionaries(int numberMovesSide2){
        state[1][0] = state[1][0] - numberMovesSide2;
        state[1][1] = state[1][1] + numberMovesSide2;
    }
    
    public void actionMoveBoat(){
        if (state[2][0] == 1){
            state[2][0] = 0;
            state[2][1] = 1;
        }else{
            state[2][0] = 1;
            state[2][1] = 0;
        }
    }
    
    public int getNumCannibals(Side side){
        if (side == Side.SIDE_1){
            return state[0][0];
        }else{
            return state[0][1];
        }
    }
    
    public int getNumMissionaries(Side side){
        if (side == Side.SIDE_1){
            return state[1][0];
        }else{
            return state[1][1];
        }
    }
    
    public Side getSide(){
        if (state[2][0] == 1){
            return Side.SIDE_1;
        }else{
            return Side.SIDE_2;
        }
    }
    
    public void printState() {
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                System.out.printf("%d ", state[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object obj) {
        StateCannibals stateObj = (StateCannibals)obj;        
        for (int i = 0; i < DIM_Y; i++) {
            for (int j = 0; j < DIM_X; j++) {
                if (state[i][j] != stateObj.state[i][j]){
                    return false;
                }
            }
        }
        return true;
    }        
}
