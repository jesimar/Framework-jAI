package framework.problem;

/**
 *
 * @author Jesimar da Silva Arantes
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
