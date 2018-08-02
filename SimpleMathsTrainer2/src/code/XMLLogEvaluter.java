package code;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * the implementation of the LogEvaluator for XML (SMTLogXML)
 * 
 * @author felixq
 *
 */
public class XMLLogEvaluter {
    /**
     * initialize a XMLLogger
     */
    private XMLLogger xmlLogger = new XMLLogger();

    /**
     * ... and a document
     */
    private Document document;

    /**
     * Constructor (auto call)
     */
    public XMLLogEvaluter() {
	/**
	 * call the read() method of the logger
	 */
	xmlLogger.read();
	/**
	 * get the document of the logger and assign it to our document
	 */
	document = xmlLogger.getDocument();
    }

    /**
     * get all training
     * 
     * @return a list with all "Training" Elements of the log
     */
    public List<Element> getTrainings() {
	Element root = document.getRootElement();
	List<Element> trainings = new ArrayList<>();
	trainings = root.getChildren("Training");
	return trainings;
    }

    /**
     * print all Training and their times
     */
    public void dumpTrainingsWithTimes() {
	/**
	 * lambda expression to print the training and its time (formated)
	 */
	getTrainings().forEach(e -> {
	    LocalDateTime localDateTime = parseDate(getDate(e));
	    /**
	     * do not use FormatStyle.LONG or ...FULL because our time data don't contain so
	     * much information
	     */
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	    System.out.println("A new " + e.getName() + " started at " + dateTimeFormatter.format(localDateTime) + ".");
	});
    }

    /**
     * print the durations between the trainings
     */
    public void dumpTimeBetweenTrainings() {
	List<Element> trainings = getTrainings();
	/**
	 * this is just working if there are more than one training is
	 */
	if (trainings.size() > 1) {
	    for (int i = 1; i < trainings.size(); i++) {
		Element e1 = trainings.get(i - 1);
		Element e2 = trainings.get(i);
		Duration duration = Duration.between(parseDate(getDate(e1)), parseDate(getDate(e2)));
		System.out.println(duration.toDays() + " Days\t" + duration.toHours() + " Hours\t"
			+ duration.toMinutes() + " Minutes");
	    }
	}
    }

    /**
     * print the times and the average time for the given type of calculation
     * 
     * @param type
     *                 the type of calculation
     */
    public void dumpTimeForCalc(int type) {
	List<Long> times = new ArrayList<>();
	getTrainings().forEach(e -> {
	    e.getChildren().forEach(t -> {
		if (t.getChild("Type").getValue().equals(String.valueOf(type))) {
		    String start_time = t.getChild("Start").getValue();
		    String end_time = t.getChild("End").getValue();
		    Duration duration = Duration.between(parseDate(start_time), parseDate(end_time));
		    times.add(duration.toMillis());
		    /**
		     * \t is the tabulator char
		     */
		    System.out.print(duration.toMillis() + "\t");
		}
	    });
	});

	/**
	 * just try to calculate the average time if there are times (if we don't check
	 * this, we could get a division by zero exception)
	 */
	if (times.size() != 0) {
	    long average = 0;
	    for (long l : times) {
		average += l;
	    }
	    /**
	     * short form for average=Σ(times)/times
	     */
	    average /= times.size();
	    System.out.print("\n");
	    System.out.println("Average is " + average + "MS");
	} else {
	    System.out.println("Could not calculate Average -  there are no calculations!");
	}
    }

    /**
     * print the error average of errors in the given type of calculation
     * 
     * @param type
     *                 the type f calculation
     */
    public void dumpErrorRate(int type) {
	double error = 0;
	double correct = 0;
	List<Element> trainings = getTrainings();
	for (Element e : trainings) {
	    List<Element> trys = e.getChildren();
	    for (Element f : trys) {
		if (f.getChild("Type").getValue().equals(String.valueOf(type))) {
		    if (f.getChild("Success").getValue().equals("true")) {
			correct++;
		    } else {
			error++;
		    }
		}
	    }
	}
	System.out.println(error + " Error\t" + correct + " Correct");
	if (error + correct != 0) {
	    System.out.println("Error-Rate is " + error / (error + correct) + ".");
	} else {
	    System.out.println("There are no calculations!");
	}
    }

    /**
     * get the time value of a node (this node need to have a time attribute!)
     * 
     * @param e
     *              the element
     * @return the value of the time attribute as String
     */
    private String getDate(Element e) {
	return e.getAttributeValue("time");
    }

    /**
     * parse a String to a date
     * 
     * @param data
     *                 the Time value as String
     * @return the time value as LocalDateTime in the format of ISO_DATE_TIME
     */
    private LocalDateTime parseDate(String data) {
	return LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
    }

}
