package gui;

import code.Trainer;
import code.XMLLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
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
    @FXML
    private Button btn_settings = new Button();

    /**
     * initialize the XMLLogEvaluter
     */
    private XMLLogger logger = new XMLLogger();

    /**
     * initialize the trainer
     */
    private Trainer trainer = new Trainer();

    /**
     * this Method is called, when the window gets initialized (auto call)
     */
    public void initialize() {
	logger.read();
	logger.newTraining();
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
	alert.setContentText(Texts.getString("MainGui.0")); //$NON-NLS-1$
	alert.setHeaderText(Texts.getString("MainGui.1")); //$NON-NLS-1$
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
	alert.showAndWait().filter(r -> r == ButtonType.YES).ifPresent(r -> {
	    /**
	     * DON'T logger.wite() here! This gives an incomplete try!
	     */
	    System.exit(0);
	});
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
	if (!tf_answer.getText().equals("")) { //$NON-NLS-1$
	    /**
	     * there are ... some easter eggs!
	     */
	    if (tf_answer.getText().equals(Texts.getString("MainGui.1"))) { //$NON-NLS-1$
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(Texts.getString("MainGui.13")); //$NON-NLS-1$
		alert.setHeaderText(Texts.getString("MainGui.14")); //$NON-NLS-1$
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	    } else if (tf_answer.getText().equals(Texts.getString("MainGui.11"))) { //$NON-NLS-1$
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(Texts.getString("MainGui.13")); //$NON-NLS-1$
		alert.setHeaderText(Texts.getString("MainGui.15")); //$NON-NLS-1$
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	    } else if (tf_answer.getText().equals(Texts.getString("MainGui.12"))) { //$NON-NLS-1$
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(Texts.getString("MainGui.13")); //$NON-NLS-1$
		alert.setHeaderText(Texts.getString("MainGui.16")); //$NON-NLS-1$
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	    } else {
		/**
		 * get the answer and handle exceptions when parsing numbers
		 */
		try {
		    double answer = Double.parseDouble(tf_answer.getText());
		    /**
		     * react to the answer
		     */
		    if (trainer.checkResult(answer)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			/**
			 * for localization Texts.getString("MainGui.?)
			 */
			alert.setContentText(Texts.getString("MainGui.3")); //$NON-NLS-1$
			alert.setHeaderText(Texts.getString("MainGui.4")); //$NON-NLS-1$
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.OK);
			alert.showAndWait();
			logger.endTry(true);
		    } else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(Texts.getString("MainGui.5")); //$NON-NLS-1$
			alert.setHeaderText(Texts.getString("MainGui.6")); //$NON-NLS-1$
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.OK);
			alert.showAndWait();
			logger.endTry(false);
		    }
		    logger.write();
		    newQuestion();
		} catch (NumberFormatException e) {
		    /**
		     * thrown when the user answers with a text
		     */
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.setContentText(Texts.getString("MainGui.9")); //$NON-NLS-1$
		    alert.setHeaderText(Texts.getString("MainGui.2")); //$NON-NLS-1$
		    alert.getButtonTypes().clear();
		    alert.getButtonTypes().add(ButtonType.YES);
		    alert.showAndWait();
		}
	    }
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setContentText(Texts.getString("MainGui.7")); //$NON-NLS-1$
	    alert.setHeaderText(Texts.getString("MainGui.8")); //$NON-NLS-1$
	    alert.getButtonTypes().clear();
	    alert.getButtonTypes().add(ButtonType.YES);
	    alert.showAndWait();
	}
    }

    /**
     * open the statistics window (using the same Stage)
     */
    @FXML
    private void showStatistics(ActionEvent e) {
	StatisticsLauncher statisticsLauncher = new StatisticsLauncher();
	statisticsLauncher.launch(getStage(e));
    }

    /**
     * open the settings window (using the same Stage)
     */
    @FXML
    private void showSettings(ActionEvent e) {
	SettingsLauncher settingsLauncher = new SettingsLauncher();
	settingsLauncher.launch(getStage(e));
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
	int log_type = 0;
	switch (trainer.getOperation().get(2)) {
	case "+": //$NON-NLS-1$
	    log_type = 0;
	    break;
	case "-": //$NON-NLS-1$
	    log_type = 1;
	    break;
	case "*": //$NON-NLS-1$
	    log_type = 2;
	    break;
	case "/": //$NON-NLS-1$
	    log_type = 3;
	    break;
	}
	logger.newTry(log_type, tf_operand1.getText() + tf_operator.getText() + tf_operand2.getText() + "=" //$NON-NLS-1$
		+ String.valueOf(trainer.getAnswer()));
    }

    /**
     * find the current stage
     * 
     * @param e
     *              an ActionEvent
     * @return the current stage
     */
    private Stage getStage(ActionEvent e) {
	Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	return stage;
    }
}
