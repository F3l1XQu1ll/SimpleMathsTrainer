package gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    /**
     * Replace "gui.locale.english.messages" with "gui.locale.german.messages" to
     * get the German Version of the Application.
     */
    private static final String BUNDLE_NAME = "gui.locale.english.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
	try {
	    return RESOURCE_BUNDLE.getString(key);
	} catch (MissingResourceException e) {
	    return '!' + key + '!';
	}
    }
}
