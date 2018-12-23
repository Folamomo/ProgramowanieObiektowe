package shapes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Movement implements MouseMotionListener, MouseListener {
    private int X,Y; //mouse coordinates
    private MyPanel panel;
    private Shape captured;

    public Movement(MyPanel panel) {
        this.panel=panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        captured=null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        for(Shape shape: panel.shapesList){
            if(shape.pressed(X,Y)){
                captured=shape;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        captured=null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(captured!=null){
            captured.setPositionX(captured.getPositionX()+e.getX()-X);
            captured.setPositionY(captured.getPositionY()+e.getY()-Y);
            X = e.getX();
            Y = e.getY();
            panel.repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
