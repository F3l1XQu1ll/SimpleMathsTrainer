package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * launch the Statistics Window
 * 
 * @author felixq
 *
 */
public class StatisticsLauncher {
    /**
     * standard procedure for Java-FX application; but it isn't self executable and
     * doesn't extend Application
     */
    private void start() {
	// TODO Auto-generated method stub
	Stage stage = new Stage();
	Parent parent;
	try {
	    parent = FXMLLoader.load(getClass().getResource("StatisticsGui.fxml"));
	    Scene scene = new Scene(parent);
	    stage.setScene(scene);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	stage.setTitle("Statistics");
	stage.showAndWait();
    }

    public void launch() {
	start();
    }

}
