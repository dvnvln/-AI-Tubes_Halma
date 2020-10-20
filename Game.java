import java.util.*;



public class Game{
    private static Player p1;
    private static Player p2;
    private static Game game = new Game();
    Scanner sc = new Scanner(System.in);

    public void play(Board papan, int turn){
        papan.printBoard();
        sc.nextLine();
        if (turn % 2 == 0){
            String s =p2.getCommand(papan);
            System.out.printf("\nCommand by bot : %s\n",s);
            papan.execute(p2.getCommand(papan));
        }else{
            papan.execute(p1.getCommand(papan));
        }
        if (papan.cekWin() == 0)
            play(papan,turn+1);
        else
            System.out.printf("\n%d Menang anjay\n",papan.cekWin());
    }

    public static void main(String args[]){
        if (args[0].equals("2")){
            p1 = new Human();
            p2 = new Human();
        }
        else if (args[0].equals("1")){
            p1 = new Bot(1,1);
            p2 = new Bot(2,2);
        }
        Board papan = new Board(8);
        int turn = 1;
        game.play(papan,turn);
    }

}