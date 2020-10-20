import java.util.*;
import java.lang.Math;

public class MinmaxLocal {
    static double inf = Double.POSITIVE_INFINITY;
    static double onf = Double.NEGATIVE_INFINITY;
    public static void minMax(){

    }

    public static Board minValue(Board board, Cell from, double maxVal, int depth){
        if (depth==0) //(depth==0)root || found winner
            return board; //board hasil
        else{
            Board newboard = new Board();
            double x = onf;
            for (Cell i : board.availMove(from, null, false)){
                newboard = minValue(newboard, from, x, depth-1);
                double newX = newboard.getUtility();
                if (newX < x){
                    x = newX;
                    newboard.Move(from,i);
                }
                if (newX < maxVal)
                    break;
            }
            return newboard;
        }
    }

    public static Board maxValue(Board board, Cell from, double minVal, int depth){
        if (depth==0) //(depth==0)root || found winner
            return board; //board hasil
        else{
            Board newboard = new Board();
            double x = onf;
            for (Cell i : board.availMove(from, null, false)){
                newboard = minValue(newboard, from, x, depth-1);
                double newX = newboard.getUtility();
                if (newX > x){
                    x = newX;
                    newboard.Move(from,i);
                }
                if (newX > minVal)
                    break;
            }
            return newboard;
        }
    }
}