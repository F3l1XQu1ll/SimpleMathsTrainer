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
    /**
     * Import ResourceBundle for Translations
     * @param stage the generated Scene will be displayed on this stage.
     */
    private void start(Stage stage) {
	// TODO Auto-generated method stub
	Parent parent;
	try {
	    parent = FXMLLoader.load(getClass().getResource("StatisticsGui.fxml"), Texts.getResourceBundle());
	    Scene scene = new Scene(parent);
	    stage.setScene(scene);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	stage.setTitle("Statistics");
	stage.centerOnScreen();
	stage.sizeToScene();
	stage.show();
    }

    public void launch(Stage stage) {
	start(stage);
    }

}
