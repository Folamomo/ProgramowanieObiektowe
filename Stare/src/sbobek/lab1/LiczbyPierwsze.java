package sbobek.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LiczbyPierwsze {
    public static void main(String[] args){
        System.out.println("Podaj liczbę maksymalną");
        Scanner in = new Scanner(System.in);
        int max = in.nextInt();
        ArrayList primes = durszlak.getPrimes(max);
        for (Object i:primes) {
            System.out.println(i);
        }
    }
}
