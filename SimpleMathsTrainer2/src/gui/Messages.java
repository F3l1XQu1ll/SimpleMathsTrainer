package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

//import com.sun.java.util.jar.pack.Package.File;

public class Messages {
    /**
     * Replace the settings in GuiStartup.java
     */

    private static Path PATH_TO_RESOURCE_LINK = Paths.get("src", "gui", "locale", "messages.properties");

    private static String BUNDLE_NAME = "gui.locale.messages"; //$NON-NLS-1$

    private static ResourceBundle RESOURCE_BUNDLE;
    // ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
	try {
	    Path recources_location = PATH_TO_RESOURCE_LINK.toRealPath().normalize().subpath(
		    Paths.get("src").toAbsolutePath().getNameCount(),
		    PATH_TO_RESOURCE_LINK.toRealPath().toAbsolutePath().getNameCount());
	    String s = recources_location.toString().replaceAll(File.separator, ".");
	    s = s.substring(0, s.lastIndexOf("."));
	    BUNDLE_NAME = s;
	    RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	    return RESOURCE_BUNDLE.getString(key);
	} catch (MissingResourceException e) {
	    return '!' + key + '!';
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }
}
