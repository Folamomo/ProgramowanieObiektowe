package crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cryptographer {
    public static void cryptfile(File in, PrintWriter out, Algorithm algo){
        Scanner sc = null;

        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            while (sc2.hasNext()) {
                String s = sc2.next();
                out.print(algo.crypt(s));
                out.print(" ");
            }
            out.println();
        }
        out.close();
    }
    public static void decryptfile(File in, PrintWriter out, Algorithm algo){
        Scanner sc = null;

        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            while (sc2.hasNext()) {
                String s = sc2.next();
                out.print(algo.decrypt(s));
                out.print(" ");
            }
            out.println();
        }
        out.close();
    }
}
