package zadania;

public class Trojkat extends Shape {
    private int a;
    private int h;

    public Trojkat(int a, int h, String name) {
        super.name=name;
        this.a = a;
        this.h = h;
    }

    @Override
    public void draw() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < a / h * i; j++) {
                System.out.print("X");
            }
            System.out.print("\n");
        }
    }
}
