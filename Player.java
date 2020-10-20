import java.util.ArrayList;

public abstract class Player{
    // public static Player player1;
    // public static Player player2;

    //attribute
    // private ArrayList<Position> listPion;
    // private boolean myTurn;
    // int num;

    //constructor
    // public Player(){
    //     // this.listPion = new ArrayList<>();
    //     // this.myTurn = false;
    // }

    //getter & setter
    // public Position getListPion(int idx){
    //     if (idx < listPion.size()) return listPion.get(idx);
    //     return null;
    // }

    // public boolean getMyTurn(){
    //     return this.myTurn;
    // }

    public abstract String getCommand(Board papan);
    //FORMAT RETURN STRING (X,Y)(X,Y) yang kiri asal yang kanan tujuan, kalo move ga valid dianggep skip
}