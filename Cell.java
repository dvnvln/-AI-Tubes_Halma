import java.util.*;

public class Cell{
    
    //attribute
    private Point position;
    private int type;
    private boolean status;

    //constructor
	public Cell(){
        this.position = new Point();
        this.type = 0;
        this.status = false;
    }
    
    public Cell(Point position, int type, boolean status){
        this.position = position;
        this.type = type;
        this.status = status;
    }

    //getter & setter
    private Point getPosition(){
        return this.position;
    }

    private int getType(){
        return this.type;
    }

    private boolean getStatus(){
        return this.status;
    }

    private void setPosition(Point position){
        this.position = position;
    }

    private void setType(int type){
        this.type = type;
    }

    private void setStatus(boolean status){
        this.status = status;
    }
}