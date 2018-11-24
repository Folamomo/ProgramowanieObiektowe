package zadania;

public class Prostokat extends Shape{
    private int x;
    private int y;

    public Prostokat(int x, int y, String name) {
        super.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print("X");
            }
            System.out.print("\n");
        }
    }


}
