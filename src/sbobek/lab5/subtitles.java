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
        m.find();
        int newStart = Integer.parseInt(m.group(1))+(delay*framerate)/1000;
        int newEnd = Integer.parseInt(m.group(2))+(delay*framerate)/1000;
        result.append("{").append(newStart).append("}{").append(newEnd).append("}").append(m.group(3));
        return result.toString();
    }

    public static void main(String[] argv) {
        String pathIn;
        String pathOut;
        int delay_ms;
        int framerate;
        try {
            pathIn = argv [0];
            pathOut = argv [1];
            delay_ms = Integer.parseInt(argv[2]);
            framerate = Integer.parseInt(argv[3]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: in out delay framerate");
            return;
        }
        File in = new File(pathIn);
        PrintWriter OutputFile = null;
        try {
            OutputFile = new PrintWriter(pathOut);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Path: " + pathOut);
            return;
        }

        Scanner sc = null;
        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Path: " + pathIn);
            return;
        }


        while (sc.hasNextLine()) {
            try {
                OutputFile.println(subtitles.delay(sc.nextLine(), delay_ms, framerate));
            }
            catch (IllegalStateException e){
                System.out.println("Bad format");
                return;
            }
        }



        OutputFile.close();

    }
}
