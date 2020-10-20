import java.util.*;

public class Bot extends Player {
    static double inf = Double.POSITIVE_INFINITY;
    static double onf = Double.NEGATIVE_INFINITY;
    //Player = player 1 atau player 2
    //type = tipe botnya, 1 untuk MinMax, 2 untuk MinMax with Local Search
    private int player;
    private int type;

    
    // public String MinMaxLoc (Board papan){
    //     // return;
    // }
    public Bot(int pl, int type){
        player = pl;
        this.type = type;
    }

    public HashMap<Cell,Cell> generateBestNeighbor(Board papan){
        // HashMap<Cell, ArrayList<Cell>> possibleMove = new HashMap<Cell, ArrayList<Cell>>();
        double util = 99999;
        if (player == 1){
            util *= -1;
        }
        HashMap<Cell,Cell> result = new HashMap<Cell,Cell>();
        for (int i = 0; i < papan.getSize(); i++){
            for (int j =0; j < papan.getSize(); j++){
                Cell cell = papan.getCell(i,j);
                // cell.print();
                if (cell.getPion() == player){
                    System.out.printf("pion %d di",cell.getPion());cell.print();System.out.println("");
                    if (player == 1){
                        // Cell cell = papan.getCell(j,i);
                        // if (cell.getPion() == player){
                        //     possibleMove.put(cell, papan.availMove(cell, null, false));
                        // }
                        for (Cell c : papan.availMove(cell, new ArrayList<Cell>(), false)){
                            papan.Move(cell, c);
                            if (papan.getUtility() > util){
                                util = papan.getUtility();
                                result.clear();
                                result.put(cell, c);
                                // System.out.printf("Move updated : new move to ");cell.print();c.print();
                                // System.out.println();
                            }
                            papan.Move(c, cell);
                        }
                    }
                    
                    else if (player == 2){
                        // Cell cell = papan.getCell(j,i);
                        // if (cell.getPion() == player){
                        //     possibleMove.put(cell, papan.availMove(cell, null, false));
                        // }
                        // System.out.printf("\nUtil : %f\n",util);
                        for (Cell c : papan.availMove(cell, new ArrayList<Cell>(), false)){
                            papan.Move(cell, c);
                            // System.out.printf("Move from ");cell.print();c.print();System.out.printf(" util : %f\n",papan.getUtility());
                            if (papan.getUtility() < util){
                                util = papan.getUtility();
                                result.clear();
                                result.put(cell, c);
                                // System.out.printf("Move updated : new move to ");cell.print();c.print();
                                // System.out.println();
                            }
                            papan.Move(c, cell);
                        }
                    }
                }
    
            }
        }
        return result;
    }

    // public static HashMap<Cell,Cell> minValue(Board board, int player, int depth){
    //     if (depth==0 ||  board.cekWin() == player )//(depth==0)root || found winner
    //         return null; //board hasil
    //     else{
    //         double min = inf;
    //         HashMap<Cell,Cell> ret = new HashMap<>();
    //         ArrayList<Cell> pions = board.getPion(player);
    //         for (Cell pion : pions){
    //             for (Cell move : board.availMove(pion,new ArrayList<Cell>(),false)){
    //                 Board newboard = new Board(board);
    //                 newboard.Move(pion,move);
    //                 HashMap<Cell,Cell> val = maxValue(newboard,((player % 2) + 1),depth-1);
    //                 Board newboard2; 
    //                 if (val == null){
    //                     newboard2 = newboard;
    //                 }else{
    //                     newboard2 = new Board(board);
    //                     for (Cell k : val.keySet())
    //                         newboard2.Move(k,val.get(k));
    //                 }
    //                 if (newboard2.getUtility() < min){
    //                     ret.clear();
    //                     ret.put(pion,move);
    //                     System.out.printf("\nMove updated : ");pion.print();move.print();System.out.println();
    //                 }
    //             }
    //         }
    //         return ret;
    //     }
    // }
    

    // public static HashMap<Cell,Cell> maxValue(Board board, int player, int depth){
    //     if (depth==0 || board.cekWin() == player ) //(depth==0)root || found winner
    //         return null; //board hasil
    //     else{
    //         double min = onf;
    //         HashMap<Cell,Cell> ret = new HashMap<>();
    //         ArrayList<Cell> pions = board.getPion(player);
    //         for (Cell pion : pions){
    //             for (Cell move : board.availMove(pion,new ArrayList<Cell>(),false)){
    //                 Board newboard = new Board(board);
    //                 newboard.Move(pion,move);
    //                 HashMap<Cell,Cell> val = minValue(newboard,((player % 2) + 1),depth-1);
    //                 Board newboard2; 
    //                 if (val == null){
    //                     newboard2 = new Board(newboard);
    //                 }else{
    //                     newboard2 = new Board(board);
    //                     for (Cell k : val.keySet())
    //                         newboard2.Move(k,val.get(k));
    //                 }
    //                 if (newboard2.getUtility() > min){
    //                     ret.clear();
    //                     ret.put(pion,move);
    //                     min = newboard2.getUtility();
    //                     System.out.printf("\nMove updated : ");pion.print();move.print();System.out.println();
    //                 }
    //             }
    //         }
    //         return ret;
    //     }
    // }

    public HashMap<Cell,Cell> minMaxAlgo(Board board, int depth){
        HashMap<Cell,Cell> move;
        if (player == 1){
            move = maxValue(board,player,2);
        }
        else{
            move = minValue(board,player,2);
        }
        return move;
    }   

    public HashMap<Cell,Cell> minValue(Board board,int player, int depth){
        HashMap<Cell,Cell> retval = new HashMap<>();
        if (depth == 0){
            return null;
        }
        else{
            double util = inf;
            ArrayList<Cell> pions = board.getPion(player);
            for (Cell from : pions){
                // System.out.printf("\nDari sini");from.print();
                // System.out.printf("with %f util",board.getUtility());
                // board.printBoard();
                for (Cell to : board.availMove(from,new ArrayList<Cell>(), false)){
                    Board tomove = new Board(board);
                    // System.out.printf("\nKe sini");to.print();
                    tomove.Move(tomove.getCell(from.getY(),from.getX()),tomove.getCell(to.getY(),to.getX()));
                    // System.out.printf("with %f util",tomove.getUtility());
                    HashMap<Cell,Cell> val = minValue(tomove,(player%2)+1,depth-1);
                    if (val == null){
                        if (tomove.getUtility() < util) {
                            retval.clear();
                            retval.put(from,to);
                            util = tomove.getUtility();
                            // System.out.printf("\nTerpilih ");to.print();System.out.printf(" dengan util %f",util);
                            tomove.Move(tomove.getCell(to.getX(),to.getY()),tomove.getCell(from.getX(),from.getY()));
                        }
                    }else{
                        for (Cell c : val.keySet()){
                            tomove.Move(c,val.get(c));
                        }
                        if (tomove.getUtility() < util) {
                            retval.clear();
                            retval.put(from,to);
                            util = tomove.getUtility();
                            // System.out.printf("\nTerpilih ");to.print();System.out.printf(" dengan util %f",util);
                            for (Cell c : val.keySet()){
                                tomove.Move(val.get(c),c);
                            }
                        }
                    }
                    // tomove.Move(tomove.getCell(to.getX(),to.getY()),tomove.getCell(from.getX(),from.getY()));
                }   
            }
            return retval;
        }
    }

    public HashMap<Cell,Cell> maxValue(Board board,int player, int depth){
        Board copy = new Board(board);
        HashMap<Cell,Cell> retval = new HashMap<>();
        if (depth == 0){
            return null;
        }
        else{
            double util = onf;
            ArrayList<Cell> pions = board.getPion(player);
            for (Cell from : pions){
                // System.out.printf("\nDari sini");from.print();
                // System.out.printf("with %f util",board.getUtility());
                // board.printBoard();
                for (Cell to : board.availMove(from,new ArrayList<Cell>(), false)){
                    Board tomove = new Board(board);
                    // System.out.printf("\nKe sini");to.print();
                    tomove.Move(tomove.getCell(from.getY(),from.getX()),tomove.getCell(to.getY(),to.getX()));
                    // System.out.printf("with %f util",tomove.getUtility());
                    HashMap<Cell,Cell> val = minValue(tomove,(player%2)+1,depth-1);
                    if (val == null){
                        if (tomove.getUtility() > util) {
                            retval.clear();
                            retval.put(from,to);
                            util = tomove.getUtility();
                            // System.out.printf("\nTerpilih ");to.print();System.out.printf(" dengan util %f",util);
                            // tomove.Move(tomove.getCell(to.getX(),to.getY()),tomove.getCell(from.getX(),from.getY()));
                        }
                    }else{
                        for (Cell c : val.keySet()){
                            tomove.Move(c,val.get(c));
                        }
                        if (tomove.getUtility() > util) {
                            retval.clear();
                            retval.put(from,to);
                            util = tomove.getUtility();
                            System.out.printf("\nTerpilih ");to.print();System.out.printf(" dengan util %f",util);
                            for (Cell c : val.keySet()){
                                tomove.Move(val.get(c),c);
                            }
                        }
                    }
                    tomove.Move(tomove.getCell(to.getX(),to.getY()),tomove.getCell(from.getX(),from.getY()));
                }   
            }
            return retval;
        }
    }



    public String getCommand(Board papan){
        String output = "";
        // long startTime = System.nanoTime();
        if (type == 2){
            HashMap<Cell,Cell> result = generateBestNeighbor(papan);
            for (Cell k : result.keySet()){
                // k.print();
                output = String.format("(%d,%d)(%d,%d)", k.getY(), k.getX(), result.get(k).getY(), result.get(k).getX());
            }
        }
        else if (type == 1){
            HashMap<Cell,Cell> result = minMaxAlgo(papan,3);
            for (Cell k : result.keySet()){
                // k.print();
                output = String.format("(%d,%d)(%d,%d)", k.getY(), k.getX(), result.get(k).getY(), result.get(k).getX());
                
            }
        }
        System.out.printf("\n\nMOVE : %s\n\n",output);
        return output;
    }
}
