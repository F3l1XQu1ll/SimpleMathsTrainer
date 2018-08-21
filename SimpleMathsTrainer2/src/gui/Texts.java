package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * replacement for the Messages.java Class
 * 
 * @author felixq
 *
 */
public class Texts {

    /**
     * constructor (why, why is this here? It's here because it doe's noting!)
     */
    public Texts() {

    }

    /**
     * read a value from the ResourceBundle
     * 
     * @param key
     *                the key to search
     * @return the value referring to the key or null if something bad happens
     */
    public static String getString(String key) {
	try {
	    ResourceBundle resourceBundle = getResourceBundle();
	    return resourceBundle.getString(key);
	} catch (MissingResourceException e) {
	    return '!' + key + '!';
	}
    }

    /**
     * reads the Settings File and
     * 
     * @return returns the ResourceBundle for the current Language or null ...
     */
    public static ResourceBundle getResourceBundle() {
	File settingsFile = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Settings.xml").toFile();
	if (Files.exists(settingsFile.toPath())) {
	    try {
		Document settings_doc = new SAXBuilder().build(settingsFile);
		String LANGUAGE = settings_doc.getRootElement().getChildText("Language");
		if (!LANGUAGE.equals(null)) {
		    if (LANGUAGE.equals("English")) {
			String res_dir = "gui.locale.en_EN";
			ResourceBundle resourceBundle = ResourceBundle.getBundle(res_dir);
			return resourceBundle;
		    } else if (LANGUAGE.equals("Deutsch")) {
			String res_dir = "gui.locale.de_DE";
			ResourceBundle resourceBundle = ResourceBundle.getBundle(res_dir);
			return resourceBundle;
		    }
		} else {
		    String res_dir = "gui.locale.en_EN";
		    ResourceBundle resourceBundle = ResourceBundle.getBundle(res_dir);
		    return resourceBundle;
		}
	    } catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	String res_dir = "gui.locale.en_EN";
	ResourceBundle resourceBundle = ResourceBundle.getBundle(res_dir);
	return resourceBundle;
    }

}
