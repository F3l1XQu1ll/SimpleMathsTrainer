package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogEvaluater {
    private Path log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "log.txt");
    private BufferedReader reader;

    private double add_e = 0, add_c = 0;
    private double sub_e = 0, sub_c = 0;
    private double mul_e = 0, mul_c = 0;
    private double div_e = 0, div_c = 0;

    public LogEvaluater() {
	try {
	    reader = Files.newBufferedReader(log_path);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void dump() {
	String current_line = null;
	try {
	    /**
	     * read all lines
	     */
	    while ((current_line = reader.readLine()) != null) {
		String[] content = current_line.split(";");
		if (content[0] != "started new training;2018-07-26T18:00:10.180134"
			&& content[0] != "new Try;2018-07-26T18:00:22.880847") {
		    switch (content[1]) {
		    case "0":
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
	
	/**
	 * tray again
	 */
    }

}
