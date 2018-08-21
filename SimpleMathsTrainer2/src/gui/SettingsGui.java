package gui;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
	items.add(Texts.getString("SettingsGui.0")); //$NON-NLS-1$
	items.add(Texts.getString("SettingsGui.1")); //$NON-NLS-1$
	/**
	 * select the first Item
	 */
	cb.getSelectionModel().select(0);

	/**
	 * try to find out, what language we want to use
	 */
	// try {
	// /**
	// * get the path to the Translation (real) without the name of the file
	// * (getParent())
	// */
	// String currentLangDir = Paths.get("src", "gui", "locale",
	// "messages.properties").toRealPath().getParent() //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// .toString();
	// /**
	// * the string ends with the current locale
	// */
	// if (currentLangDir.endsWith("english")) { //$NON-NLS-1$
	// cb.getSelectionModel().select(0);
	// } else if (currentLangDir.endsWith("deutsch")) { //$NON-NLS-1$
	// cb.getSelectionModel().select(1);
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	String currentLang = readSettings();
	if (!currentLang.equals(null)) {
	    if (currentLang.equals("English")) {
		cb.getSelectionModel().select(0);
	    } else if (currentLang.equals("Deutsch")) {
		cb.getSelectionModel().select(1);
	    }
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
	Path settings_file = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Settings.xml");
	try {
	    if (Files.exists(settings_file.getParent())) {
		if (Files.exists(settings_file)) {
		    setLanguageToSettingsFile(lang);
		} else {
		    // Files.createFile(settings_file);
		    createEmptySettingsFile();
		    setLanguageToSettingsFile(lang);
		}
	    } else {
		Files.createFile(settings_file);
		createEmptySettingsFile();
		setLanguageToSettingsFile(lang);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// try {
	// /**
	// * never use relative paths for Symbolic Links!
	// */
	// if (!Files.exists(Paths.get("src", "gui", "locale",
	// "messages.properties").toAbsolutePath())) { //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// Files.createSymbolicLink(Paths.get("src", "gui", "locale",
	// "messages.properties").toAbsolutePath(), //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// /**
	// * we use the chosen value for the language in lowerChase Letters
	// */
	// Paths.get("src", "gui", "locale", lang.toLowerCase(),
	// "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// } else {
	// Files.delete(Paths.get("src", "gui", "locale",
	// "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// Files.createSymbolicLink(Paths.get("src", "gui", "locale",
	// "messages.properties").toAbsolutePath(), //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// Paths.get("src", "gui", "locale", lang.toLowerCase(),
	// "messages.properties").toAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
	// //$NON-NLS-3$ //$NON-NLS-4$
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
    }

    /**
     * read the settings file and write the current language to it
     * 
     * @param lang
     *                 the language to set
     */
    private void setLanguageToSettingsFile(String lang) {
	Path settings_file = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Settings.xml");
	try {
	    Document document = new SAXBuilder().build(settings_file.toFile());
	    Element root = document.getRootElement();
	    root.getChild("Language").setText(lang);
	    // root.getChild("Language").addContent(lang);
	    XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
	    OutputStream outputStream = Files.newOutputStream(settings_file, StandardOpenOption.CREATE);
	    xmlOutputter.output(document, outputStream);
	    outputStream.close();
	} catch (JDOMException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * create a new, empty settings file (not really empty, it contains the DOCTYPE
     * and a root element called "Settings")
     */
    private void createEmptySettingsFile() {
	Path settings_file = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Settings.xml");
	try {
	    Document document = new Document();
	    document.setDocType(new DocType("SMTSettingsXML"));
	    document.setRootElement(new Element("Settings"));
	    document.getRootElement().getChildren().add(new Element("Language"));
	    XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
	    OutputStream outputStream = Files.newOutputStream(settings_file, StandardOpenOption.CREATE);
	    xmlOutputter.output(document, outputStream);
	    outputStream.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * read the settings File
     * 
     * @return and return the current language as String
     */
    private String readSettings() {
	File settingsFile = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Settings.xml").toFile();
	if (Files.exists(settingsFile.toPath())) {
	    try {
		Document settings_doc = new SAXBuilder().build(settingsFile);
		String LANGUAGE = settings_doc.getRootElement().getChildText("Language");
		if (!LANGUAGE.equals(null)) {
		    if (LANGUAGE.equals("English")) {
			return LANGUAGE;
		    } else if (LANGUAGE.equals("Deutsch")) {
			return LANGUAGE;
		    }
		} else {
		    return null;
		}
	    } catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return null;
    }
}
