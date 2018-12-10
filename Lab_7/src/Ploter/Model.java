package Ploter;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class Model {
    public static UnaryOperator<Double> ParseFunction(String functionString){
        String[] strings  =functionString.replace("-","+-").split("\\+");
        ArrayList<Double> coeficients = new ArrayList<Double>();
        ArrayList<Integer> powers = new ArrayList<Integer>();
        for (String string : strings){
            if (string.isEmpty()) continue;
            string=string.replace("-x", "-1*x");
            if(!string.contains("x")){
                string=string.concat("*x^0");
            }
            if (!string.contains("*")){
                string = "1*".concat(string);
            }
            if (!string.contains("^")){
                string = string.concat("^1");
            }

            String[] s = string.split("\\*x\\^");
            coeficients.add(Double.parseDouble(s[0]));
                powers.add(Integer.parseInt(s[1]));
        }
        return x -> {
            double sum = 0;
            for (int i = 0; i < coeficients.size(); i++) {
                sum += coeficients.get(i) * Math.pow(x, powers.get(i));
            }
            return sum;
        };
    }
}
