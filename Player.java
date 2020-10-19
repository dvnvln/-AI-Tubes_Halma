import java.util.ArrayList;

public class Player{
    public static Player player1;
    public static Player player2;

    //attribute
    private ArrayList<Position> listPion;
    private boolean myTurn;

    //constructor
    public Player(){
        this.listPion = new ArrayList<>();
        this.myTurn = false;
    }

    //getter & setter
    public Position getListPion(int idx){
        if (idx < lsitPion.size()) return listPion.get(idx);
        return null;
    }

    public boolean getMyTurn(){
        return this.myTurn;
    }
}