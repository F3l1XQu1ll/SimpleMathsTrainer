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

public class SettingsGui {
    @FXML
    private Button btn_close = new Button();

    @FXML
    private ChoiceBox<String> cb = new ChoiceBox<>();

    public void initialize() {
	List<String> items = new ArrayList<>();
	ObservableList<String> ol = FXCollections.observableList(items);
	// System.out.println(ol);
	cb.setItems(ol);
	// System.out.println(cb.getItems());
	items.add("English");
	items.add("German");
	cb.getSelectionModel().select(0);

	try {
	    String currentLangDir = Paths.get("src", "gui", "locale", "messages.properties").toRealPath().getParent()
		    .toString();
	    if (currentLangDir.endsWith("english")) {
		cb.getSelectionModel().select(0);
	    } else if (currentLangDir.endsWith("german")) {
		cb.getSelectionModel().select(1);
	    }

	    // System.out.println(Paths.get("src", "gui", "locale",
	    // "messages.properties").toRealPath());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		setLanguage(newValue);
	    }
	});
    }

    @FXML
    private void close(Event event) {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.close();
    }

    private void setLanguage(String lang) {
	try {
	    /**
	     * never use relative paths for Symbolic Links!
	     */
	    if (!Files.exists(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath())) {
		Files.createSymbolicLink(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath(),
			Paths.get("src", "gui", "locale", lang.toLowerCase(), "messages.properties").toAbsolutePath());
	    } else {
		Files.delete(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath());
		Files.createSymbolicLink(Paths.get("src", "gui", "locale", "messages.properties").toAbsolutePath(),
			Paths.get("src", "gui", "locale", lang.toLowerCase(), "messages.properties").toAbsolutePath());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
