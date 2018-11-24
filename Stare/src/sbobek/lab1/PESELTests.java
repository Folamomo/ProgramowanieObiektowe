package sbobek.lab1;

public class PESELTests {
    public static void main(String[] Args){
        String[] ToTest = {"98010230711", "00000000000", "11111111111", "98121771514", "13"};
        for (String s:ToTest) {
            System.out.print(s);
            System.out.print(" - ");
            System.out.println(PESEL.check(s));
        }
    }
}
