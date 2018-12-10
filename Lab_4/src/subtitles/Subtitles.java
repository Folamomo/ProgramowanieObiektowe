package subtitles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import subtitles.SubtitleExceptions.InvalidFormat;

public class Subtitles {


    private static String delay (String in, int delay, int framerate) throws InvalidFormat{
        Pattern goodSubtitleLine = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+)");
        Matcher m = goodSubtitleLine.matcher(in);
        StringBuilder result = new StringBuilder();
        if(m.find()) {
            int newStart = Integer.parseInt(m.group(1)) + (delay * framerate) / 1000;
            int newEnd = Integer.parseInt(m.group(2)) + (delay * framerate) / 1000;
            result.append("{").append(newStart).append("}{").append(newEnd).append("}").append(m.group(3));
            return result.toString();
        } else throw new InvalidFormat(in);
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
            System.err.println("Usage: in out delay framerate");
            return;
        }
        if (framerate<0){
            System.err.println("Framerate can't be negative");
        }
        File in = new File(pathIn);
        PrintWriter OutputFile = null;
        try {
            OutputFile = new PrintWriter(pathOut);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid Path: " + pathOut);
            return;
        }

        Scanner sc = null;
        try {
            sc = new Scanner(in);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid Path: " + pathIn);
            return;
        }


        while (sc.hasNextLine()) {
            try {
                OutputFile.println(Subtitles.delay(sc.nextLine(), delay_ms, framerate));
            }
            catch (InvalidFormat e){
                System.err.println(e.getMessage());
                return;
            }
        }



        OutputFile.close();

    }
}
