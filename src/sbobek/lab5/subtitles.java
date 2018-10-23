package sbobek.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class subtitles {


    private static String delay (String in, int delay, int framerate){
        Pattern goodSubtitleLine = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+)");
        Matcher m = goodSubtitleLine.matcher(in);
        StringBuilder result = new StringBuilder();
        int newStart = Integer.parseInt(m.group(1))+(delay*1000)/framerate;
        int newEnd = Integer.parseInt(m.group(2))+(delay*1000)/framerate;
        result.append("{").append(newStart).append("}{").append(newEnd).append("}").append(m.group(3));
        return result.toString();
    }

    public static void main(String[] argv) {
        File in = new File(argv[0]);
        PrintWriter out = null;
        try {
            out = new PrintWriter(argv[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Path: " + argv[1]);
            return;
        }

        Scanner sc = null;
        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Path: " + argv[0]);
            return;
        }

        while (sc.hasNextLine()) {
            out.println(delay(sc.nextLine(), Integer.parseInt(argv[2]), Integer.parseInt(argv[3])));
        }

    }
}
