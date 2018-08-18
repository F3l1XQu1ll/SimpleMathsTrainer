package gui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Class to create FX-Application Thread
 * @author felixq
 *
 */
public class GuiStartup extends Application {

    @Override
    public void start(Stage primaryStage) {
	/**
	 * Else in the earlier Class, we just create the FX-Application Thread and launch the MainGui with it's own Launcher-Class.
	 */
	MainGuiLauncher mainGuiLauncher = new MainGuiLauncher();
	mainGuiLauncher.launch(primaryStage);
    }

    public void start() {
	launch();
    }
}
