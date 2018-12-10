package zadania;

import java.util.*;
import zadania.Punkt3D;

public class Test{
    private LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
    public static void main(String [] Args){
        boolean quit = false;
        Scanner odczyt = new Scanner(System.in);
        Test dane = new Test();
        while(!quit){
            System.out.print(
                    "1. Wczytaj punkt 3D\n" +
                            "2. Wyświetl wszystkie punkty\n" +
                            "3. Oblicz odległość\n" +
                            "4. Zakończ\n");
            String wybor = odczyt.nextLine();
            if (wybor.equals("4")) quit = true;
            else if (wybor.equals(("1"))){
                System.out.print("Podaj X, Y, Z oraz nazwę punktu\n");
                Punkt3D nowy = new Punkt3D(odczyt.nextDouble(), odczyt.nextDouble(), odczyt.nextDouble(), odczyt.next());
                odczyt.nextLine();
                dane.punkty.add(nowy);


            }
            else if (wybor.equals("2")){
                for (Punkt3D punkt: dane.punkty){
                    System.out.print(punkt.GetName()+": ("+punkt.GetX()+"; "+punkt.GetY()+"; "+punkt.GetZ()+")\n");
                }
            }
            else if (wybor.equals("3")){
                System.out.print("Podaj nazwy 2 punktów\n");
                String nazwa1 = odczyt.next();
                String nazwa2 = odczyt.next();
                odczyt.nextLine();
                Punkt3D punkt1=new Punkt3D(0, 0, 0, "");
                Punkt3D punkt2=new Punkt3D(0, 0, 0, "");
                for (Punkt3D punkt: dane.punkty){
                    if (punkt.GetName().equals(nazwa1)) punkt1 = punkt;
                    else if (punkt.GetName().equals(nazwa2)) punkt2 = punkt;
                }
                double odleglosc = punkt1.distance(punkt2);
                System.out.println("Odległość wynosi "+ odleglosc);
            }

        }
        return;
    }
}
