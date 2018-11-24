package zadania;

public class Kolo extends Shape {
    int promien;

    public Kolo(int promien, String name) {
        super.name = name;
        this.promien = promien;
    }

    @Override
    public void draw() {
        for (int i = 0; i < 2*promien; i++) {
            for (int j = 0; j < 2*promien; j++) {
                if(wewnatrz(i, j))  System.out.print("X");
            }
            System.out.print("\n");
        }
    }
    private boolean wewnatrz(int x, int y){
        return (promien-x)*(promien-x)+(promien-y)*(promien-y)<=promien*promien;
    }
}
