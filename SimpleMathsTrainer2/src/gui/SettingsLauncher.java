package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsLauncher {
    /**
     * standard procedure for Java-FX application; but it isn't self executable and
     * doesn't extend Application
     */
    private void start() {
	// TODO Auto-generated method stub
	Stage stage = new Stage();
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
	stage.showAndWait();
    }

    public void launch() {
	start();
    }
}
