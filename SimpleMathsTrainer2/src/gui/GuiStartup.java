package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiStartup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	/**
	 * we load the FXML File on the Resource Bundle of get The Resources for the UI
	 * Strings
	 */
	Parent root = FXMLLoader.load(getClass().getResource("MainGui.fxml"), Messages.getResourceBundle());
	Scene scene = new Scene(root);

	primaryStage.setScene(scene);
	primaryStage.setTitle("Simple Maths Trainer");
	primaryStage.show();
    }

    public void start(String[] args) {
	launch(args);
    }

}
