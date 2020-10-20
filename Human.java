import java.util.*;

public class Human extends Player{
    public String getCommand(Board papan){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }
}