package zadania;
import java.io.*;
import java.util.ArrayList;

import javaIn.*;

public class LiczbyPierwsze {
    public static void main(String[] args) {
        System.out.print("Podaj górny limit: ");
        int max = JIn.getInt();
        ArrayList pierwsze = durszlak.getPrimes(max);
        System.out.println(pierwsze.toString());
    }
}
