package code;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * now we going to log the results in a XML file
 * 
 * @author felixq
 *
 */
public class XMLLogger {

    /**
     * the path to the log file
     */
    private Path xml_log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "Log.xml");

    /**
     * the XML Document (not on hard disk! This exists only virtual.)
     */
    private Document document;

    /**
     * Constructor for this class to create necessary files and directories
     */
    public XMLLogger() {
	if (!Files.exists(xml_log_path)) {
	    try {
		/**
		 * create new file on hard disk
		 */
		Files.createFile(xml_log_path);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    /**
	     * initialize new XML Document
	     */
	    document = new Document();

	    /**
	     * Get the Root Element of the Document
	     */
	    document.setRootElement(new Element("Log"));
	    /**
	     * Set the DocTipe (SMTLogXML for SimpleMathsTrainerXML)
	     */
	    document.setDocType(new DocType("SMTLogXML"));
	    // System.out.println(document.toString());
	    /**
	     * write changes in Document to the Hard disk
	     */
	    write();
	}
    }

    /**
     * read the File to a Document
     */
    public void read() {
	try {
	    /**
	     * Documents get built not read!
	     */
	    document = new SAXBuilder().build(xml_log_path.toFile());
	    /**
	     * catch some exceptions
	     */
	} catch (JDOMException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * get the current Document (virtual)
     * 
     * @return the Document
     */
    public Document getDocument() {
	return document;
    }

    /**
     * write Document to Hard Disk
     */
    public void write() {
	/**
	 * ... Get some Beautiful XML ...
	 */
	XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
	try {
	    /**
	     * we use a stream, not a BufferedWriter - the same rules, but no one cares ...
	     */
	    OutputStream outputStream = Files.newOutputStream(xml_log_path, StandardOpenOption.CREATE);
	    /**
	     * put the Document to the Stream
	     */
	    out.output(document, outputStream);
	    /**
	     * and display it
	     */
	    out.output(document, System.out);
	    /**
	     * write to hard disc
	     */
	    outputStream.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * log new training
     */
    public void newTraining() {
	/**
	 * create a new element
	 */
	Element element = new Element("Training");
	/**
	 * add a time attribute to it with the current time in the ISO_DATE_TIME Format
	 */
	element.getAttributes().add(new Attribute("time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
	/**
	 * add the new Element (Training) to the Root
	 */
	document.getRootElement().getChildren().add(element);
    }

    /**
     * log a new try
     * 
     * @param success
     *                        was the answer correct?
     * @param type
     *                        (0)addition, (1)subtraction, (2)multiplication,
     *                        (3)division
     * @param calculation
     *                        the calculation (f.e.: 7*3=21)
     */
    public void newTry(boolean success, int type, String calculation) {
	/**
	 * create a new Element
	 */
	Element element = new Element("Try");
	/**
	 * add a children element with the start time of the try
	 */
	element.getChildren()
		.add(new Element("Start").setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
	/**
	 * add a children element with the value of success
	 */
	element.getChildren().add(new Element("Success").setText(String.valueOf(success)));
	/**
	 * ... with the value of type
	 */
	element.getChildren().add(new Element("Type").setText(String.valueOf(type)));
	/**
	 * ... with the value of calculation
	 */
	element.getChildren().add(new Element("Calculation").setText(String.valueOf(calculation)));
	/**
	 * get the last of roots children (the last training) and add the new try (with
	 * its children) to the training
	 */
	document.getRootElement().getChildren().get(document.getRootElement().getChildren().size() - 1).getChildren()
		.add(element);
    }

    /**
     * add the end time to the last try (this is used to calculate how long the user
     * needed to calculate
     */
    public void endTry() {
	/**
	 * get the root
	 */
	Element root = document.getRootElement();
	/**
	 * get its children
	 */
	List<Element> root_children = root.getChildren();
	/**
	 * get the last training
	 */
	Element root_last_child = root_children.get(root_children.size() - 1);
	/**
	 * get the last try
	 */
	Element last_try = root_last_child.getChildren().get(root_last_child.getChildren().size() - 1);
	/**
	 * add a children element with the current time in the ISO_DATE_TIME Format to
	 * it
	 */
	last_try.getChildren()
		.add(new Element("End").setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
    }

}
