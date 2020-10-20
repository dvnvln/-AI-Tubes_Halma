import java.util.*;

public class Arah{
    // -1, 0, atau 1
    public int x;
    public int y;
    // public static ArrayList<Arah> directions = new ArrayList<>();

    public Arah(){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (!(i==j && i == 0)){
                    Arah x = new Arah(i,j);
                    Board.directions.add(x);
                }
            }
        }
    }

    public Arah(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Arah Jump(){
        return new Arah(x*2,y*2);
    }

    public void print(){
        System.out.printf("%d %d",y,x);
    }

}