package gui;

import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiStartup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	Parent root = FXMLLoader.load(getClass().getResource("MainGui.fxml"));
	Scene scene = new Scene(root);
	// scene.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm());
	/**
	 * this must be modified and implemented in a extra Class Like "Settigns.Java"
	 */
	if (!Files.exists(Paths.get("src", "gui", "locale", "messages.properties"))) {
	    Files.createLink(Paths.get("src", "gui", "locale", "messages.properties"),
		    Paths.get("src", "gui", "locale", "english", "messages.properties"));
	} else {
	    Files.delete(Paths.get("src", "gui", "locale", "messages.properties"));
	    Files.createLink(Paths.get("src", "gui", "locale", "messages.properties"),
		    Paths.get("src", "gui", "locale", "english", "messages.properties"));
	}

	primaryStage.setScene(scene);
	primaryStage.setTitle("Simple Maths Trainer");
	primaryStage.show();
    }

    public void start(String[] args) {
	launch(args);
    }

}
