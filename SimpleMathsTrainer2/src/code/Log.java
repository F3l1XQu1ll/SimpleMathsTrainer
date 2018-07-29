package code;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * initialize the Log Class
 * 
 * @author felixq
 *
 * @param <T>
 *            custom data type, derived from Object
 */
public class Log<T extends Object> {

    /**
     * Path for log-files
     */
    private Path log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "log.txt");
    /**
     * Initialize new BufferedWriter
     */
    private BufferedWriter writer;

    /**
     * Constructor for Log.java, called by instancing the Log Class
     */
    public Log() {
	/**
	 * check if the directory exists
	 */
	if (Files.exists(log_path.getParent())) {
	    try {
		/**
		 * if true initialize a new BufferedWriter
		 */
		writer = Files.newBufferedWriter(log_path, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    try {
		/**
		 * if false, create directory and initialize writer
		 */
		Files.createDirectory(log_path.getParent());
		writer = Files.newBufferedWriter(log_path, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    /**
     * function for logging
     * 
     * @param t
     *              Data ("public class Log<T extends Object>") which has to be
     *              written
     */
    public void log(T t) {
	try {
	    /**
	     * write and create a new line; t.toString() => convert Data to String (just
	     * working when data extends Object)
	     */
	    writer.write(t.toString());
	    writer.newLine();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * write data to file
     */
    public void close() {
	try {
	    writer.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
