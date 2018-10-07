package sbobek.lab1;


public class MatrixTests {
    public static void main(String[] Args){
        int [][] d1 = {{1, 1},{1, 1}};
        Macierz m1 = new Macierz(d1);
        m1.print();
        Macierz m2 = m1.add(m1);
        m2.print();
        Macierz m3 = m1.mul(3);
        m3.print();
        Macierz m4 = m3.mul(m2);
        m4.print();
    }
}
