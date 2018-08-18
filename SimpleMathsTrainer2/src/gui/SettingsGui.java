package gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * the Settings Window
 * 
 * @author felixq
 *
 */
public class SettingsGui {
    @FXML
    private Button btn_close = new Button();

    @FXML
    private ChoiceBox<String> cb = new ChoiceBox<>();

    /**
     * auto call
     */
    public void initialize() {
	/**
	 * List for items in the ChoiceBox
	 */
	List<String> items = new ArrayList<>();
	ObservableList<String> ol = FXCollections.observableList(items);
	// System.out.println(ol);
	cb.setItems(ol);
	// System.out.println(cb.getItems());
	items.add(Messages.getString("SettingsGui.0")); //$NON-NLS-1$
	items.add(Messages.getString("SettingsGui.1")); //$NON-NLS-1$
	/**
	 * select the first Item
	 */
	cb.getSelectionModel().select(0);

	/**
	 * try to find out, what language we want to use
	 */
	try {
	    /**
	     * get the path to the Translation (real) without the name of the file
	     * (getParent())
	     */
	    String currentLangDir = Paths.get("src", "gui", "locale", "messages.properties").toRealPath().getParent() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		    .toString();
	    /**
	     * the string ends with the current locale
	     */
	    if (currentLangDir.endsWith("english")) { //$NON-NLS-1$
		cb.getSelectionModel().select(0);
	    } else if (currentLangDir.endsWith("deutsch")) { //$NON-NLS-1$
		cb.getSelectionModel().select(1);
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

	/**
	 * we want to react to changes on the ChoiceBox
	 */
	cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		setLanguage(newValue);
	    }
	});
    }

    /**
     * close the window and show MainGui again
     * 
     * @param event
     *                  parameter is auto generated
     */
    @FXML
    private void close(Event event) {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	MainGuiLauncher mainGuiLauncher = new MainGuiLauncher();
	mainGuiLauncher.launch(stage);
    }

    /**
     * method to set a new language
     * 
     * @param lang
     *                 the new Language
     */
    private void setLanguage(String lang) {
	try {
	    /**
	     * never use relative paths for Symbolic Links!
	     */
	    if (!Files.exists(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath())) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		Files.createSymbolicLink(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			/**
			 * we use the chosen value for the language in lowerChase Letters
			 */
			Paths.get("src", "gui", "locale", lang.toLowerCase(), "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	    } else {
		Files.delete(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		Files.createSymbolicLink(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			Paths.get("src", "gui", "locale", lang.toLowerCase(), "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
