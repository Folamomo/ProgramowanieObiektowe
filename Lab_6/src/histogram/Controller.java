package histogram;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Random random = new Random();

    @FXML
    private BarChart<?, ?> Chart;

    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    Histogram histogram = new Histogram();
    public XYChart.Series series= new BarChart.Series();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 20; i++) {
            histogram.add(Integer.toString(random.nextInt()), random.nextGaussian());
        }
        Chart.getData().addAll(series);
        for (Map.Entry<Double, Integer> entry : histogram.getProcessedData().entrySet()){
            series.getData().add(new XYChart.Data(Double.toString(entry.getKey()), entry.getValue()));
        }
    }


    public void doUpdate()
    {
        histogram.add(Integer.toString(random.nextInt()), random.nextGaussian());
        series.getData().clear();
        for (Map.Entry<Double, Integer> entry : histogram.getProcessedData().entrySet()){
            series.getData().add(new XYChart.Data(Double.toString(entry.getKey()), entry.getValue()));
        }

    }
}
