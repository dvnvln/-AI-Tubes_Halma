import java.util.*;

public class Cell{
    
    //attribute
    //x,y = koordinat
    //type = tipe cell. 0 = cell biasa, 1 = goal player 1, 2 = goal player 2
    //pion = ada pion ato engga dan punya siapa. 0 = ga ada pion, 1 = pion player 1, 2 = pion player 2
    private int x;
    private int y;
    private int type;
    private int pion;

    


    public Cell(int x, int y, int type, int pion){
        this.x = x;
        this.y = y;
        this.type = type;
        this.pion = pion;
    }

    public Cell(Cell c){
        this.x = c.x;
        this.y = c.y;
        this.type = c.type;
        this.pion = c.pion;
    }

    //getter & setter
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getType(){
        return this.type;
    }

    public int getPion(){
        return this.pion;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setPion(int pion){
        this.pion = pion;
    }

    public boolean isEmpty(){
        return this.pion == 0;
    }

    public void print(){
        System.out.printf("(%d,%d)",y,x);
    }

}