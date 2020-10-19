public class Pion{
    private Point position;
    private boolean color;

    public Pion(){
        this.position = new Point();
        this.color = false;
    }

    private Point getPosition(){
        return this.position;
    }

    private boolean getColor(){
        return this.color;
    }

    private void setPosition(Point position){
        this.position = position;
    }

    private void setColor(boolean color){
        this.color = color;
    }
}