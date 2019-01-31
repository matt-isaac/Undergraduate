package components;

public class OrderedPair {
    private int x;
    private int y;

    public OrderedPair(int x1, int y1){
        x = x1;
        y = y1;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x1) {
        this.x = x1;
    }

    public void setY(int y1){
        this.y = y1;
    }


    public boolean equals(OrderedPair op) {
        return (op.getX() == x && op.getY() == y);
    }
}
