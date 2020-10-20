import java.util.*;
import java.lang.Math;

public class Board{
    //attribute
    //private int[][] board;
    //Player 1 kiri atas
    //Player 2 kanan bawah
    //p1_goal = tujuan P1 = rumah P2
    //p2_goal = tujuan P2 = rumah P1
    private HashMap<Integer,HashMap<Integer,Cell>> board;
    private int size;
    public static ArrayList<Cell> p1_goal = new ArrayList<>();
    public static ArrayList<Cell> p2_goal = new ArrayList<>();
    public static ArrayList<Arah> directions = new ArrayList<>();

    // public class Arah{
    //     // -1, 0, atau 1
    //     public int x;
    //     public int y;

    //     public Arah(){
    //         for (int i = -1; i < 2; i++){
    //             for (int j = -1; j < 2; j++){
    //                 if (!(i==j && i == 0)){
    //                     new Arah(i,j);
    //                 }
    //             }
    //         }
    //     }

    //     public Arah(int x, int y){
    //         this.x = x;
    //         this.y = y;
    //         directions.add(this);
    //     }
    // }

    

    //constructor
    public Board(){
        Arah x = new Arah();
        this.size = 16;
        this.board = new HashMap<>();
        for (int i = 0; i < size; i ++){
            board.put(i,new HashMap<>());
            for (int j = 0; j < size; j++){

                board.get(i).put(j,new Cell(j,i,0,0));

                if ( i + j <= 5){
                    if ( (i != 5) && (j != 5) ){
                        Cell c = new Cell(j,i,2,1);
                        p2_goal.add(c);
                        board.get(i).put(j,c);   
                    } 
                }

                if ( i + j >= 25){
                    if ( (i != 10) && (j != 10) ){
                        Cell c = new Cell(j,i,1,2);
                        p1_goal.add(c);
                        board.get(i).put(j,c);   
                    } 
                }
            }
        }
    }

    public Board(Board copy){
        size = copy.getSize();
        this.board = new HashMap<>();
        for (int i =0; i < size; i ++){
            board.put(i,new HashMap<>());
            for (int j =0; j< size; j++){
                Cell tocopy = copy.getCell(i,j);
                board.get(i).put(j,new Cell(tocopy));
            }
        }
    }

    public Board(int size){
        Arah x = new Arah();
        this.size = size;

        if (this.size == 16){
            this.board = new HashMap<>();
            for (int i = 0; i < size; i ++){
                board.put(i,new HashMap<>());
                for (int j = 0; j < size; j++){
    
                    board.get(i).put(j,new Cell(j,i,0,0));
    
                    if ( i + j <= 5){
                        if ( (i != 5) && (j != 5) ){
                            Cell c = new Cell(j,i,2,1);
                            p2_goal.add(c);
                            board.get(i).put(j,c);   
                        } 
                    }
    
                    if ( i + j >= 25){
                        if ( (i != 10) && (j != 10) ){
                            Cell c = new Cell(j,i,1,2);
                            p1_goal.add(c);
                            board.get(i).put(j,c);   
                        } 
                    }
                }
            }    
        }
        
        else if (this.size == 10){
            this.board = new HashMap<>();
            for (int i = 0; i < size; i ++){
                board.put(i,new HashMap<>());
                for (int j = 0; j < size; j++){
    
                    board.get(i).put(j,new Cell(j,i,0,0));
    
                    if ( i + j <= 4){
                        Cell c = new Cell(j,i,2,1);
                        p2_goal.add(c);
                        board.get(i).put(j,c);    
                    }
    
                    if ( i + j >= 14){
                        Cell c = new Cell(j,i,1,2);
                        p1_goal.add(c);
                        board.get(i).put(j,c);   
                    }
                }
            }
        }

        else if (this.size == 8){
            this.board = new HashMap<>();
            for (int i = 0; i < size; i ++){
                board.put(i,new HashMap<>());
                for (int j = 0; j < size; j++){
    
                    board.get(i).put(j,new Cell(j,i,0,0));
    
                    if ( i + j <= 3){
                        Cell c = new Cell(j,i,2,1);
                        p2_goal.add(c);
                        board.get(i).put(j,c);    
                    }
    
                    if ( i + j >= 11){
                        Cell c = new Cell(j,i,1,2);
                        p1_goal.add(c);
                        board.get(i).put(j,c);   
                    }
                }
            }
        }
    }

    //getter
    public int getSize(){
        return size;
    }

    public Cell getCell(int row, int column){
        return board.get(row).get(column);    
    }

    public double getUtility(){
        double val = 0;
        for (int i = 0; i < getSize(); i ++)
            for(int j = 0; j < getSize(); j ++){
                Cell cell = getCell(j,i);
                ArrayList<Integer> distances= new ArrayList<Integer>();
                // double max_distance = Integer.MIN_VALUE;

                if (cell.getPion() == 1){
                    double max_distance = Integer.MAX_VALUE;
                    ArrayList<Cell> p1_goal_copy = new ArrayList<Cell>(p1_goal);
                    for (Cell n : p1_goal_copy){
                        if (n.getPion() != 1){
                            max_distance = Math.min(max_distance, cellDistance(n, cell));
                        }
                    }
                    // System.out.println("Pion Player 1");
                    // cell.print();
                    // System.out.println(max_distance);
                    if (cell.getType() != 1) val -= max_distance;
                    // System.out.println(val);
            
                }
                else if (cell.getPion() == 2){
                    double max_distance = Integer.MIN_VALUE;
                    ArrayList<Cell> p2_goal_copy = new ArrayList<Cell>(p2_goal);
                    for (Cell n : p2_goal_copy){
                        if (n.getPion() != 2)
                            max_distance = Math.max(max_distance, cellDistance(n, cell));
                    }
                    // System.out.println("Pion Player 2");
                    // cell.print();
                    // System.out.println(max_distance)                    
                    if (cell.getType() != 2) val += max_distance;
                    // System.out.println(val);
                }
            }    
        return Math.round(val * 100.0) / 100.0;
    }

    //setter
    public double cellDistance(Cell c1, Cell c2){
        return (Math.sqrt(Math.pow( c1.getX() - c2.getX(), 2 ) + Math.pow (c1.getY() - c2.getY(), 2 )));
    }
    public void assignElement(int row, int column, int playerValue){
        // board[row][column] = playerValue;

    }

    public int cekWin(){
        int win = 1;
        for (Cell c : Board.p1_goal){
            if (c.getPion() != 1) win = 2;
        }
        if (win != 1)
            for (Cell c : Board.p2_goal){
                if (c.getPion() != 2) win = 0;
            }
        return win;
    }


    public boolean moveAman(Cell from, Arah a){
        int xhasil = from.getX() + a.getX();
        int yhasil = from.getY() + a.getY();
        if (xhasil > -1 && xhasil < size){
            if (yhasil > -1 && yhasil < size){
                // if ( (from.getType() == 0 && getCell(yhasil, xhasil).getType() != (3-from.getPion())) || (from.getType() != 0 && from.getType() == from.getPion() && getCell(yhasil, xhasil).getType() != 0 ) ){
                    return true;
                // }
            }
        }
        return false;
    }

    public Cell moveArah(Cell from, Arah a){
        // System.out.println("\nMOVEARAH");a.print();
        // from.print();
        Cell to = getCell(from.getY()+a.getY(),from.getX()+a.getX());
        // to.print();
        return to;
    }

    // public ArrayList<Cell> availMove(Cell from, Player player, ArrayList<Cell> moves, boolean abisLoncat){
    public ArrayList<Cell> availMove(Cell from, ArrayList<Cell> pred, boolean abisLoncat){
        ArrayList<Cell> move = new ArrayList<Cell>();
        // Collections.copy(move,moves);
        if (from.getPion() == 0){
            return new ArrayList<Cell>();
        }

        directions.forEach((n) ->{
            // n.print();
            if(moveAman(from,n)){
                Cell moving = moveArah(from,n);
                if (moving.isEmpty() ){
                    // n.print();
                    if (!abisLoncat){
                        move.add(moving);
                    }
                }
                else{
                    if(moveAman(from,n.Jump())){
                        Cell jumping = moveArah(from,n.Jump());
                        if (jumping.isEmpty() && !pred.contains(jumping)){
                            move.add(jumping);
                            pred.add(from);
                            move.addAll(availMove(jumping, pred, true));
                        }
                    }
                }
            }
        });
        
        return move;
    }

    public void Move(Cell from, Cell to){
        int gerak = from.getPion();
        from.setPion(0);
        to.setPion(gerak);
    }

    public void printBoard(){
        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j ++){
                System.out.print("|");
                System.out.print(getCell(i,j).getPion());
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public void execute(String command){
        String com[] = command.replace(")("," ").replace("(","").replace(")","").split(" ");
        Cell from = getCell(Integer.parseInt(com[0].split(",")[0]),Integer.parseInt(com[0].split(",")[1]));
        Cell to = getCell(Integer.parseInt(com[1].split(",")[0]),Integer.parseInt(com[1].split(",")[1]));
        // Cell to = getCell(com[1].split(",").map((n) -> Integer.parseInt(n)));
        Move(from,to);
    }

    public ArrayList<Cell> getPion(int player){
        ArrayList<Cell> hasil = new ArrayList<>();
        for (int i = 0; i< size; i++){
            for (int j = 0; j < size; j++){
                Cell tocheck = getCell(i,j);
                if (tocheck.getPion() == player){
                    hasil.add(tocheck);
                }
            }
        }
        return hasil;
    }

    public static void main(String args[]){
        Board papan = new Board();
        // papan.Move(papan.getCell(0,4), papan.getCell(0,5), 1);
        papan.printBoard();
        System.out.println(papan.getUtility());
    }
}
