import java.util.*;

public class Board{

    //attribute
    private int[][] board;
    private int size;

    //constrcutor
    public Board(){
        board = new int[16][16];
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                board[i][j] = 0;
            }
        }
    }

    public Board(int size){
        board = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = 0;
            }
        }
    }

    //getter
    private int getSize(){
        return size;
    }

    private int getElement(int row, int column){
        return board[row][column];
    }

    //setter
    private void assignElement(int row, int column, int playerValue){
        board[row][column] = playerValue;
    }
}