package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the Settings Class.
 * @author felixq
 *
 */
public class SettingsLauncher {
    /**
     * standard procedure for Java-FX application; but it isn't self executable and
     * doesn't extend Application
     * @param stage 
     */
    private void start(Stage stage) {
	// TODO Auto-generated method stub
	Parent parent;
	try {
	    parent = FXMLLoader.load(getClass().getResource("SettingsGui.fxml"), Messages.getResourceBundle());
	    Scene scene = new Scene(parent);
	    stage.setScene(scene);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	stage.setTitle("Settings");
	stage.centerOnScreen();
	stage.sizeToScene();
	stage.show();
    }

    public void launch(Stage stage) {
	start(stage);
    }
}
