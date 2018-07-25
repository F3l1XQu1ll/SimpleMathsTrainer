package code;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * initialisieren der Log classe
 * 
 * @author felixq
 *
 * @param <T>
 *            selbst definierter datentyp, der von Object abgeleitet wurde
 */
public class Log<T extends Object> {

    /**
     * Pfad für Logdateien
     */
    private Path log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "log.txt");
    /**
     * neuen BufferedWriter initialisieren
     */
    private BufferedWriter writer;

    /**
     * sog. Construktor für Log.java, wird aufgerufen wenn neues Objekt
     * initialisiert wird
     */
    public Log() {
	/**
	 * prüfen ob verzeichniss existiert
	 */
	if (Files.exists(log_path)) {
	    try {
		/**
		 * wenn ja, neuen BufferedWriter initialisieren
		 */
		writer = Files.newBufferedWriter(log_path, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    try {
		/**
		 * wenn nicht, verzeichniss anlegen und writer initialisieren
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
     * funktion zum loggen
     * 
     * @param t
     *              Daten ("public class Log<T extends Object>") die geschrieben
     *              werden sollen
     */
    public void log(T t) {
	try {
	    /**
	     * schreiben und neue Zeile beginnen; t.toString() => in schreibbaren string
	     * umwandeln (funktioniert nur bei Daten extends Objekt)
	     */
	    writer.write(t.toString());
	    writer.newLine();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * daten in datei schreiben
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
