package shapes;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    public static void main(String[] args) {
        MyPanel panel = new MyPanel();

        Kwadrat kwadrat = new Kwadrat("test", 100, 1, 1);
        Kwadrat kwadrat2 = new Kwadrat("test2", 150, 120, 1);
        Trojkat trojkat = new Trojkat("test", 200);
        panel.shapesList.add(kwadrat);
        panel.shapesList.add(kwadrat2);


        Movement movement = new Movement(panel);
        MainFrame frame = new MainFrame();
        frame.setSize(1000,800);
        frame.setVisible(true);
        frame.add(panel);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        frame.setName("Shapes");
    }

}
