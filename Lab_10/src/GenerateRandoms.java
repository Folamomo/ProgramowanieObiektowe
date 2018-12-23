import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class GenerateRandoms {
    public static void main(String[] args) {
        Random random = new Random();
        MaxMultiThread multiThread = new MaxMultiThread();
        ArrayList<Future<IntStream>> futureArrayList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            futureArrayList.add(multiThread.execute(
                    ()-> {
                        IntStream r = random.ints(10000);
                        System.out.println("Finished making a batch of randoms");
                        return r;
                    }));
        }
        try( BufferedWriter writer = new BufferedWriter(new FileWriter("randoms.txt"))){
            for (Future<IntStream> f : futureArrayList) {
                IntStream s = f.get();
                s.forEach((int i)-> {
                    try {
                        writer.write(i +" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.newLine();
                writer.flush();
                System.out.println("Finished writing a batch of randoms");
            }
            System.out.println("All done");
            multiThread.end();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
