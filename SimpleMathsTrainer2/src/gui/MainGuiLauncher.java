package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the Main GUI on the existing FX-Application Tread
 * @author felixq
 *
 */
public class MainGuiLauncher {
    /**
     * standard procedure for Java-FX application; but it isn't self executable and
     * doesn't extend Application
     * @param stage 
     */
    private void start(Stage stage) {
	Parent parent;
	try {
	    parent = FXMLLoader.load(getClass().getResource("MainGui.fxml"), Texts.getResourceBundle());
	    Scene scene = new Scene(parent);
	    stage.setScene(scene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	stage.setTitle("Simple Maths Trainer");
	stage.centerOnScreen();
	stage.sizeToScene();
	stage.show();
    }

    public void launch(Stage stage) {
	start(stage);
    }
}
