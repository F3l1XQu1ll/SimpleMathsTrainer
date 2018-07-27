package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogEvaluater {
    /**
     * the path to the logs
     */
    private Path log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "log.txt");

    /**
     * new Buffered Reader for reading the logs
     */
    private BufferedReader reader;

    /**
     * to store the errors and the correct answers count
     */
    private double add_e = 0, add_c = 0;
    private double sub_e = 0, sub_c = 0;
    private double mul_e = 0, mul_c = 0;
    private double div_e = 0, div_c = 0;

    /**
     * constructor to initialize the new BufferedReader
     */
    public LogEvaluater() {
	try {
	    reader = Files.newBufferedReader(log_path);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * method for output
     */
    public void dump() {
	/**
	 * store the current line read by the reader
	 */
	String current_line = null;
	try {
	    /**
	     * read all lines while the next line isn't empty (and store read line, because
	     * reader can just read each line one time, not again)
	     */
	    while ((current_line = reader.readLine()) != null) {
		/**
		 * store all parts of the current read line, split at each ";"
		 */
		String[] content = current_line.split(";");

		/**
		 * just read the relevant lines (not these who signal a new training or a new
		 * try)
		 */
		if (content[0] != "started new training" && content[0] != "new Try") {
		    /**
		     * get the 2nd part of each line (which is showing if the calculation was an
		     * addition, subtraction, multiplication or division)
		     */
		    switch (content[1]) {
		    case "0":
			/**
			 * if the answer wasn't right, add 1 to the errors, if not add 1 to the corrects
			 * (in this case to the additions)
			 */
			if (!content[0].equals("Correct Answer")) {
			    add_e++;
			} else {
			    add_c++;
			}
			break;
		    case "1":
			if (!content[0].equals("Correct Answer")) {
			    sub_e++;
			} else {
			    sub_c++;
			}
			break;
		    case "2":
			if (!content[0].equals("Correct Answer")) {
			    mul_e++;
			} else {
			    mul_c++;
			}
			break;
		    case "3":
			if (!content[0].equals("Correct Answer")) {
			    div_e++;
			} else {
			    div_c++;
			}
			break;
		    }
		}
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	/**
	 * print the results (the "\t" prints an tabulator) The average is only
	 * calculated, if the count of answers is bigger than 0
	 */
	System.out.println("\t\tError\tCorrect");
	System.out.println("Addition:\t" + add_e + "\t" + add_c);
	if ((add_c + add_e) != 0)
	    System.out.println("Average:\t" + add_e / (add_c + add_e));
	else
	    System.out.println("You have calculated nothing yet ...");

	System.out.println("Subtraction:\t" + sub_e + "\t" + sub_c);
	if ((sub_c + sub_e) != 0)
	    System.out.println("Average:\t" + sub_e / (sub_c + sub_e));
	else
	    System.out.println("You have calculated nothing yet ...");

	System.out.println("Multiplication:\t" + mul_e + "\t" + mul_c);
	if ((mul_c + mul_e) != 0)
	    System.out.println("Average:\t" + mul_e / (mul_c + mul_e));
	else
	    System.out.println("You have calculated nothing yet ...");

	System.out.println("Division:\t" + div_e + "\t" + div_c);
	if ((div_c + div_e) != 0)
	    System.out.println("Average:\t" + div_e / (div_c + div_e));
	else
	    System.out.println("You have calculated nothing yet ...");
    }

}
