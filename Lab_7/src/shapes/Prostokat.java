package shapes;

import java.awt.*;

public class Prostokat extends Shape {
    public int a;
    public int b;
    public Prostokat(String _name, int _a, int _b) {
        name = _name;
        a = _a;
        b = _b;
    }
    public void draw(Graphics graphics){
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                System.out.print("x");
            }
            System.out.println();
        }
    }

    @Override
    public boolean pressed(int mousePositionX, int mousePositionY) {
        return false;
    }
}
