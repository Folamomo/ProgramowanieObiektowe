package Ploter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class Controller implements Initializable {



    @FXML
    private TextField equation;
    @FXML
    private TextField rangeLeft;
    @FXML
    private TextField rangeRight;
    @FXML
    private TextField samples;

    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private LineChart<?,?> chart;
    public XYChart.Series series= new XYChart.Series();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chart.getData().addAll(series);
    }


    public void doUpdate()
    {
        series.getData().clear();
        UnaryOperator f = Model.ParseFunction(equation.getText());
        double left = Double.parseDouble(rangeLeft.getText());
        double right = Double.parseDouble(rangeRight.getText());
        double step = (right - left)/Double.parseDouble(samples.getText());
        for (double i = left; i <= right; i+=step) {
            series.getData().add(new XYChart.Data(i, f.apply(i) ) );
        }

   }
}
