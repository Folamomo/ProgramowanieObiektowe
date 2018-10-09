package lab2;

import java.util.*

public class Test{
    LinkedList<Punkt3D> punkty;
    public static void main(String [] Args){
        boolean quit = false;
        Scanner odczyt = new Scanner(System.in);
        while(!quit){
            System.out.print("
1. Wczytaj punkt 3D\n
2. Wyświetl wszystkie punkty\n
3. Oblicz odległość\n
4. Zakończ\n");
            String wybor = odczyt.nextLine();
            if (wybor == "4") quit = true;
        }
    return;
    }
}
