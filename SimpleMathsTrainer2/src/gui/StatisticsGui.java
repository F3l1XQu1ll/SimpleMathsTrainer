package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import code.XMLLogEvaluter;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Code of the Statistics Window
 * 
 * @author felixq
 *
 */
public class StatisticsGui {

    /**
     * some definitions of controls
     */
    @FXML
    private TextField tf_add_correct = new TextField();
    @FXML
    private TextField tf_add_false = new TextField();
    @FXML
    private TextField tf_add_rate = new TextField();

    @FXML
    private TextField tf_sub_correct = new TextField();
    @FXML
    private TextField tf_sub_false = new TextField();
    @FXML
    private TextField tf_sub_rate = new TextField();

    @FXML
    private TextField tf_mul_correct = new TextField();
    @FXML
    private TextField tf_mul_false = new TextField();
    @FXML
    private TextField tf_mul_rate = new TextField();

    @FXML
    private TextField tf_div_correct = new TextField();
    @FXML
    private TextField tf_div_false = new TextField();
    @FXML
    private TextField tf_div_rate = new TextField();

    @FXML
    private TextField tf_add_calc_time_average = new TextField();
    @FXML
    private TextField tf_sub_calc_time_average = new TextField();
    @FXML
    private TextField tf_mul_calc_time_average = new TextField();
    @FXML
    private TextField tf_div_calc_time_average = new TextField();

    @FXML
    private CategoryAxis ca_all = new CategoryAxis();

    @FXML
    private NumberAxis na_all = new NumberAxis();

    @FXML
    private LineChart<String, Number> ch_all = new LineChart<String, Number>(ca_all, na_all);

    @FXML
    private Button btn_close = new Button();

    /**
     * initialize the logEvaluter
     */
    private XMLLogEvaluter logEvaluter = new XMLLogEvaluter();

    /**
     * initialize the window components
     */
    public void initialize() {
	readErrorRate();
	readCalculationTimes();
    }

    /**
     * switch back to the Main Scene
     * 
     * @param event
     */
    @FXML
    private void close(Event event) {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	/**
	 * when the window is already maximized, the new stage will not be correctly drawn.
	 */
	stage.setMaximized(false);
	MainGuiLauncher mainGuiLauncher = new MainGuiLauncher();
	mainGuiLauncher.launch(stage);
    }

    /**
     * read the error rate and values and assign them to the controls
     */
    private void readErrorRate() {
	List<Double> add_values = logEvaluter.calculateErrorRate(0);
	tf_add_correct.setText(String.valueOf(Math.round(add_values.get(0))));
	tf_add_false.setText(String.valueOf(Math.round(add_values.get(1))));
	tf_add_rate.setText(
		String.valueOf(Math.round((add_values.get(1) / (add_values.get(1) + add_values.get(0))) * 100) + "%")); //$NON-NLS-1$

	List<Double> sub_values = logEvaluter.calculateErrorRate(1);
	tf_sub_correct.setText(String.valueOf(Math.round(sub_values.get(0))));
	tf_sub_false.setText(String.valueOf(Math.round(sub_values.get(1))));
	tf_sub_rate.setText(
		String.valueOf(Math.round((sub_values.get(1) / (sub_values.get(1) + sub_values.get(0))) * 100) + "%")); //$NON-NLS-1$

	List<Double> mul_values = logEvaluter.calculateErrorRate(2);
	tf_mul_correct.setText(String.valueOf(Math.round(mul_values.get(0))));
	tf_mul_false.setText(String.valueOf(Math.round(mul_values.get(1))));
	tf_mul_rate.setText(
		String.valueOf(Math.round((mul_values.get(1) / (mul_values.get(1) + mul_values.get(0))) * 100) + "%")); //$NON-NLS-1$

	List<Double> div_values = logEvaluter.calculateErrorRate(3);
	tf_div_correct.setText(String.valueOf(Math.round(div_values.get(0))));
	tf_div_false.setText(String.valueOf(Math.round(div_values.get(1))));
	tf_div_rate.setText(
		String.valueOf(Math.round((div_values.get(1) / (div_values.get(1) + div_values.get(0))) * 100) + "%")); //$NON-NLS-1$

	/**
	 * add the averages to the charts
	 *//**
	    * first read the error-rate of each training to a Map, Then create a new Series
	    * (points in the Graph), give it a name and add the calculated rates to it. At
	    * least add the series to the graph
	    */
	Map<Integer, List<Double>> add_t_values = logEvaluter.calculateErrorRateForAllTrainings(0);
	Series<String, Number> add_rates = new Series<>();
	add_rates.setName(Texts.getString("StatisticsGui.0")); //$NON-NLS-1$
	add_t_values.forEach((t, v) -> {
	    System.out.println(t);
	    System.out.println((v.get(1) / (v.get(1) + v.get(0))) * 100);
	    add_rates.getData().add(
		    new Data<String, Number>(String.valueOf(t), Math.round((v.get(1) / (v.get(1) + v.get(0))) * 100)));
	});
	ch_all.getData().clear();
	ch_all.getData().add(add_rates);

	Map<Integer, List<Double>> sub_t_values = logEvaluter.calculateErrorRateForAllTrainings(1);
	Series<String, Number> sub_rates = new Series<>();
	sub_rates.setName(Texts.getString("StatisticsGui.1")); //$NON-NLS-1$
	sub_t_values.forEach((t, v) -> {
	    System.out.println(t);
	    System.out.println((v.get(1) / (v.get(1) + v.get(0))) * 100);
	    sub_rates.getData().add(
		    new Data<String, Number>(String.valueOf(t), Math.round((v.get(1) / (v.get(1) + v.get(0))) * 100)));
	});
	ch_all.getData().add(sub_rates);

	Map<Integer, List<Double>> mul_t_values = logEvaluter.calculateErrorRateForAllTrainings(2);
	Series<String, Number> mul_rates = new Series<>();
	mul_rates.setName(Texts.getString("StatisticsGui.2")); //$NON-NLS-1$
	mul_t_values.forEach((t, v) -> {
	    System.out.println(t);
	    System.out.println((v.get(1) / (v.get(1) + v.get(0))) * 100);
	    mul_rates.getData().add(
		    new Data<String, Number>(String.valueOf(t), Math.round((v.get(1) / (v.get(1) + v.get(0))) * 100)));
	});
	ch_all.getData().add(mul_rates);

	Map<Integer, List<Double>> div_t_values = logEvaluter.calculateErrorRateForAllTrainings(3);
	Series<String, Number> div_rates = new Series<>();
	div_rates.setName(Texts.getString("StatisticsGui.3")); //$NON-NLS-1$
	div_t_values.forEach((t, v) -> {
	    System.out.println(t);
	    System.out.println((v.get(1) / (v.get(1) + v.get(0))) * 100);
	    div_rates.getData().add(
		    new Data<String, Number>(String.valueOf(t), Math.round((v.get(1) / (v.get(1) + v.get(0))) * 100)));
	});
	ch_all.getData().add(div_rates);
    }

    /**
     * read the calculation times and assign them to the controls
     */
    private void readCalculationTimes() {
	List<Long> times = new ArrayList<>();
	times = logEvaluter.calculateTimeForCalc(0);
	tf_add_calc_time_average.setText(calcAverage(times));
	times = logEvaluter.calculateTimeForCalc(1);
	tf_sub_calc_time_average.setText(calcAverage(times));
	times = logEvaluter.calculateTimeForCalc(2);
	tf_mul_calc_time_average.setText(calcAverage(times));
	times = logEvaluter.calculateTimeForCalc(3);
	tf_div_calc_time_average.setText(calcAverage(times));
    }

    /**
     * calculate the average of the calculation times
     * 
     * @param times
     * @return the average as string
     */
    private String calcAverage(List<Long> times) {
	if (times.size() != 0) {
	    long average = 0;
	    for (long l : times) {
		average += l;
	    }
	    /**
	     * short form for average=Σ(times)/times
	     */
	    average /= times.size();
	    // average = Math.round(average);
	    return String.valueOf(average);
	} else {
	    return Texts.getString("StatisticsGui.4"); //$NON-NLS-1$
	}
    }
}
