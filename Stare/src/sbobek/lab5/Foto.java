package sbobek.lab5;

import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

public class Foto {
    public static void Recognition(String Image, Indico indico, IndicoResult single) throws IOException, IndicoException {
        String resultString = null;
        double resultDouble = 0;
        Path pathToDst = null;
        Path pathToSrc = null;
        single = indico.imageRecognition.predict(
                Image
        );

        Map<String, Double> result = single.getImageRecognition();

        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (resultDouble < entry.getValue()) {
                resultString = entry.getKey();
                resultDouble = entry.getValue();
            }
        }
        boolean success = (new File("~/zdjecia_posegregowane/"+resultString.replaceAll("\\s+","_"))).mkdirs();
        if (!success) {
            // Directory creation failed
        }
        pathToSrc = Paths.get(Image);
        pathToDst = Paths.get("~/zdjecia_posegregowane/" + resultString.replaceAll("\\s+", "_") + "/" + Image.lastIndexOf("/") + 1);

        Files.copy(pathToSrc, pathToDst, StandardCopyOption.REPLACE_EXISTING);
    }
    public static void main(String[] argv) throws IndicoException {
        // single example
        Indico indico = new Indico("9326d47501f573f6facca86182475ecb");
        IndicoResult single = null;

        try (Stream<Path> paths = Files.walk(Paths.get(argv[0]))) {
            paths
                    .filter((p) -> (p.toString().endsWith(".jpg")))
                    .forEach((p)-> {
                        try {
                            Recognition(p.toString(), indico, single);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (IndicoException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}