package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * this class is used to get access to the translations
 * 
 * @author felixq
 *
 */

public class Messages {

    /**
     * the path to the resource
     */
    private static Path PATH_TO_RESOURCE_LINK = Paths.get("src", "gui", "locale", "messages.properties");

    /**
     * The name for the ResourceBundle (modified later)
     */
    private static String BUNDLE_NAME = "gui.locale.messages"; //$NON-NLS-1$

    /**
     * the ResourceBundle to use
     */
    private static ResourceBundle RESOURCE_BUNDLE;

    private Messages() {
    }

    /**
     * method to get the String value from the ResourceBundle
     * 
     * @param key
     *                the key to the searched Value
     * @return the searched Value as String or null if something happens
     */
    public static String getString(String key) {
	try {
	    /**
	     * this procedure over the next lines is here, because we use a Symbolic (not
	     * Hard) link to our Translation. We do this, because we can easily find out,
	     * what language we want to use. We can get the Path to the real destination of
	     * our file and can see were it is. But the ResourceBundle doesn't support
	     * Symbolic links.
	     */
	    /**
	     * So we get the Real destination of our file (.toRealPah().normalize()):
	     *//**
	        * e.g. /home/user/src/gui/locale/English/resource.properties (this Path isn't
	        * correct, just for the demonstration)
	        */
	    /**
	     * But our next problem is, that the ResourceBundle name must be a String in the
	     * Form "gui.locale.messages".
	     */
	    /**
	     * We cut the first Part of the Path (.subPath()).
	     *//**
	        * This needs 2 arguments: the the first position to cut (count in directory
	        * names not in chars) and the 2nd position to cut (the same).
	        */
	    /**
	     * In our case, the first position is at the point of /gui/... and the 2nd at
	     * the end of the full Path. We say:
	     *//**
	        * Paths.get("src").toAbsolutePath().getNameCount() for the first position and
	        * PATH_TO_RESOURCE_LINK.toRealPath().toAbsolutePath().getNameCount() for the
	        * 2nd position.
	        */
	    /**
	     * The result is something like this: gui/locale/English/resource.properties
	     */
	    Path recources_location = PATH_TO_RESOURCE_LINK.toRealPath().normalize().subpath(
		    Paths.get("src").toAbsolutePath().getNameCount(),
		    PATH_TO_RESOURCE_LINK.toRealPath().toAbsolutePath().getNameCount());
	    /**
	     * But we remember the "gui.locale.messages".
	     */
	    /**
	     * we say: recources_location.toString().replaceAll(File.separator, ".")
	     *//**
	        * That converts the Path to a String and replaces all FileSeperatorChars
	        * (Linux, Mac = /; Windows=\) with .
	        */
	    /**
	     * our result is now very near on what we want:
	     * "gui.locale.English.resource.properties"
	     */
	    String s = recources_location.toString().replaceAll(File.separator, ".");
	    /**
	     * the last thing we do is to cut the .properties: s.substring(0,
	     * s.lastIndexOf(".")).
	     *//**
	        * the 1st argument is the start position for the cut in our case the beginning
	        * of the string and the 2nd argument is the end of the cut, the last Index of
	        * ".".
	        */
	    s = s.substring(0, s.lastIndexOf("."));
	    BUNDLE_NAME = s;
	    /**
	     * now we can load the ResourceBundle
	     */
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

    /**
     * here we do the same again and return The Bundle or null
     * @return
     */
    public static ResourceBundle getResourceBundle() {
	try {
	    Path recources_location = PATH_TO_RESOURCE_LINK.toRealPath().normalize().subpath(
		    Paths.get("src").toAbsolutePath().getNameCount(),
		    PATH_TO_RESOURCE_LINK.toRealPath().toAbsolutePath().getNameCount());
	    String s = recources_location.toString().replaceAll(File.separator, ".");
	    s = s.substring(0, s.lastIndexOf("."));
	    BUNDLE_NAME = s;
	    RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	    return RESOURCE_BUNDLE;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
