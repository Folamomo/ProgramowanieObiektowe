package shapes;

import java.awt.*;

public class Kwadrat extends Shape{

    public int a;
    public Kwadrat(String _name, int _a, int _postionX, int _positionY) {
        name = _name;
        a = _a;
        positionX = _postionX;
        positionY = _positionY;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect(positionX,positionY,a,a);
    }
    public boolean pressed(int mousePositionX, int mousePositionY){
        return (mousePositionX >= positionX && mousePositionX <= positionX+a && mousePositionY >= positionY && mousePositionY <= positionY + a);
    }
}
