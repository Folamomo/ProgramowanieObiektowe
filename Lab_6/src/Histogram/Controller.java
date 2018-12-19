package Histogram;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {



    @FXML
    private BarChart<?, ?> histogram;

    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    public XYChart.Series series= new XYChart.Series();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        histogram.getData().addAll(series);
    }


    public void doUpdate()
    {
//        series.getData().clear();
//        UnaryOperator f = Model.ParseFunction(equation.getText());
//        double left = Double.parseDouble(rangeLeft.getText());
//        double right = Double.parseDouble(rangeRight.getText());
//        double step = (right - left)/Double.parseDouble(samples.getText());
//        for (double i = left; i <= right; i+=step) {
//            series.getData().add(new XYChart.Data(i, f.apply(i) ) );
//        }

    }
}
