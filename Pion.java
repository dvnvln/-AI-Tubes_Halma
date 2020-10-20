public class Pion{
    private Position position;
    private boolean color;

    public Pion(){
        this.position = new Position();
        this.color = false;
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean getColor(){
        return this.color;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public void setColor(boolean color){
        this.color = color;
    }
}