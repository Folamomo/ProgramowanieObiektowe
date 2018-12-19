package histogram;

import java.util.*;


public class Histogram {
    private Map<String, Double> rawData = new HashMap<>();
    private Map<Double, Integer> processedData = new TreeMap<Double, Integer>();


    private int numberOfSteps = 20;

    public Histogram() {};

    public Histogram(Map<String, Double> data){
        rawData.putAll(data);
        processData();
    }
    public void add (String name, double value){
        rawData.put(name, value);
        processData();
    }
    public void addAll(Map<String, Double> elements){
        rawData.putAll(elements);
        processData();
    }
    public void remove(String name){
        rawData.remove(name);
        processData();
    }

    public Map<Double, Integer> getProcessedData(){
        return processedData;
    }

    private void processData(){
        processedData.clear();
        double max = Collections.max(rawData.values());
        double min = Collections.min(rawData.values());
        double step = (max-min)/numberOfSteps;
        for (int i = 0; i < numberOfSteps; i++) {
            double left = min + i * step;
            double right = left + step;
            int n = 0;
            for (double value : rawData.values()){
                if (value>left && value< right) ++n;
            }
            processedData.put(left, n);
        }
    }
}
