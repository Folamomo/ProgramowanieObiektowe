package shapes;


import java.awt.*;

public abstract class Shape {
    public String name;

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int postionY) {
        this.positionY = postionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    protected int positionX;
    protected int positionY;
    public abstract void draw(Graphics graphics);
    public abstract boolean pressed(int mousePositionX, int mousePositionY);
}
