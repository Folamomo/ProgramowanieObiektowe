package zadania;
import java.io.*;
import java.util.ArrayList;

import javaIn.*;

public class LiczbyPierwsze {
    public static void main(String[] args) {
        System.out.print("Podaj górny limit: ");
        long max = 4294967294;
        ArrayList pierwsze = durszlak.getPrimes(max);
        System.out.println(pierwsze.get(pierwsze.size()-1));
    }
}
