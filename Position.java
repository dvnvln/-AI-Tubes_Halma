import java.util.*;

public class Position{
    private Point position;

    public Position(){
        this.position = new Point();
    }

    public Position(int row, int column){
        this.position = new Point(row, column);
    }

    private Point getPosition(){
        return this.position;
    }
}