package gui;

import code.Trainer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class MainGui {
    /**
     * initialize the elements of the window, which we want to have access to. The
     * "@FXML" is for the FXML-Parser, because the elements are private - so they
     * won't found.
     */
    @FXML
    private TextField tf_operand1 = new TextField();
    @FXML
    private TextField tf_operand2 = new TextField();
    @FXML
    private TextField tf_operator = new TextField();
    @FXML
    private TextField tf_answer = new TextField();

    @FXML
    private Button btn_check = new Button();
    @FXML
    private Button btn_stats = new Button();
    @FXML
    private Button btn_quit = new Button();

    /**
     * initialize the trainer
     */
    private Trainer trainer = new Trainer();

    /**
     * this Method is called, when the window gets initialized (auto call)
     */
    public void initialize() {
	newQuestion();
    }

    /**
     * "WEHERE IS THE ****ING ESC-KEY!?"
     */
    @FXML
    private void quit() {
	/**
	 * create new alert-dialog
	 */
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setContentText("Do you realy want to Rage-Quit the program?");
	alert.setHeaderText("Realy?!");
	/**
	 * Delete predefined buttons
	 */
	alert.getButtonTypes().clear();
	/**
	 * and add our own
	 */
	alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);

	/**
	 * display the dialog and react to the answer
	 */
	alert.showAndWait().filter(r -> r == ButtonType.YES).ifPresent(r -> System.exit(0));
    }

    /**
     * check the answer
     */
    @FXML
    private void check() {
	/**
	 * check if the answer field is empty (if you have inserted no text it's already
	 * not null!) (beware of String == String! That will create a new object, and
	 * java compares not the value of objects, rather the HASH of it - so the
	 * comparing will always (excepting you try this with the same object) return
	 * false!)
	 */
	if (!tf_answer.getText().equals("")) {
	    /**
	     * get the answer
	     */
	    double answer = Double.parseDouble(tf_answer.getText());
	    /**
	     * react to the answer
	     */
	    if (trainer.checkResult(answer)) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Wow! How have you done this?!");
		alert.setHeaderText("Correct!");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	    } else {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Sorry, I know ... you are perfect. \nBut this is ... WRONG!");
		alert.setHeaderText("(In)Correct!");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	    }
	    newQuestion();
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setContentText("Could you have entered ... NOTHING?!");
	    alert.setHeaderText("No answer keeps ... \nno answer!");
	    alert.getButtonTypes().clear();
	    alert.getButtonTypes().add(ButtonType.YES);
	    alert.showAndWait();
	}
    }

    /**
     * open the statistics window
     */
    @FXML
    private void showStatistics() {
	StatisticsLauncher statisticsLauncher = new StatisticsLauncher();
	statisticsLauncher.launch();
    }

    /**
     * generate a new question
     */
    private void newQuestion() {
	/**
	 * clear the last answer
	 */
	tf_answer.clear();
	/**
	 * new question by trainer
	 */
	trainer.genQuestion();
	/**
	 * fill out the FextFileds
	 */
	tf_operand1.setText(trainer.getOperation().get(0));
	tf_operand2.setText(trainer.getOperation().get(1));
	tf_operator.setText(trainer.getOperation().get(2));
    }
}
