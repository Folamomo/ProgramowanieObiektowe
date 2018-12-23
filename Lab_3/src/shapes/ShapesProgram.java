package shapes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShapesProgram {

    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        BufferedReader StdIn = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        String in = "";
        while (!exit) {
            System.out.println("1. Print all shapes\n" +
                    "2. Add a circle\n" +
                    "3. Add a square\n" +
                    "4. Add a rectangle\n" +
                    "5. Exit\n");
            in = StdIn.readLine();
            int a, b, r;
            String name;
            switch (in) {
                case "1":
                    for (Shape shape : shapes) {
                        System.out.println(shape.name);
                        shape.draw();
                    }
                    break;
                case "2":
                    System.out.println("Name:");
                    name = StdIn.readLine();
                    System.out.println("Set radius:");
                    r = Integer.parseInt(StdIn.readLine());
                    shapes.add(new Kolo(r, name));
                    break;
                case "3":
                    System.out.println("Name:");
                    name = StdIn.readLine();
                    System.out.println("Set length:");
                    a = Integer.parseInt(StdIn.readLine());
                    shapes.add(new Kwadrat(a, name));
                    break;
                case "4":
                    System.out.println("Name:");
                    name = StdIn.readLine();
                    System.out.println("Set length:");
                    a = Integer.parseInt(StdIn.readLine());
                    System.out.println("Set width:");
                    b = Integer.parseInt(StdIn.readLine());
                    shapes.add(new Prostokat(a, b, name));
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
