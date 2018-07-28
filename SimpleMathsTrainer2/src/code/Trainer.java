package code;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Trainer {

    /**
     * some definitions ...
     */
    private double a;
    private double b;
    private int operator;
    private Random rand = new Random();
    private Scanner keyboard = new Scanner(System.in);

    private DateTimeFormatter d_t_formater = DateTimeFormatter.ISO_DATE_TIME;

    private NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

    private char operator_Char = 0;

    /**
     * new instance of Log (Log.java)
     */
    private Log<Object> log = new Log<>();

    /**
     * called by Main.java; it's for starting the training
     */
    public void startTraining() {
	log.log("started new training;" + LocalDateTime.now().format(d_t_formater));
	/**
	 * always a new training if newTraining() returns true and log this
	 */
	while (newTraining()) {
	    log.log("new Try;" + LocalDateTime.now().format(d_t_formater));
	}

	/**
	 * if the newTraining() returns false (because q was submitted)
	 */
	System.out.println("Wir sehen uns wieder!");
	/**
	 * log.close() calls the writer.close() method in Log.java and writes the File
	 * (is needed, because we use the BufferedWriter class)
	 */
	log.close();

	/**
	 * quit, this is not needed, but it is for more clear code
	 */
	System.exit(0);
    }

    /**
     * called by the startTraining() method, look there for more detail
     * 
     * @return true if a new training wanted or false, then termination is requested
     *         (q-key)
     */
    private boolean newTraining() {

	/**
	 * generate the operands and the operator
	 *
	 */
	genQuestion();

	String input = null;

	System.out.println("Rechnen Sie " + a + " " + operator_Char + " " + b + " aus.");

	input = keyboard.next();

	if (input.equals("q")) {
	    keyboard.close();
	    return false;
	} else if (input.equals("l")) {
	    LogEvaluater le = new LogEvaluater();
	    le.dump();
	    keyboard.close();
	    return false;
	}

	double attempt = 0;

	/**
	 * try to parse input from String to Double
	 */
	try {
	    attempt = Double.parseDouble(input);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	/**
	 * check users answer
	 */
	switch (operator) {
	case 0:
	    showReply(attempt == a + b, a + b);
	    break;
	case 1:
	    showReply(attempt == a - b, a - b);
	    break;
	case 2:
	    showReply(attempt == a * b, a * b);
	    break;
	case 3:
	    showReply(attempt == a / b, a / b);
	}

	return true;
    }

    /**
     * show a message to the user depending on was the given answer correct
     * 
     * @param isCorrect
     *                      defines if the given answer was correct
     */
    private void showReply(boolean isCorrect, double correct_answer) {
	if (isCorrect) {
	    System.out.println("Richtig :D");
	    /**
	     * write the users result to the log-file (with date and the type of calculation
	     * (+ - * /))
	     */
	    log.log("Correct Answer;" + operator + ";" + LocalDateTime.now().format(d_t_formater));
	} else {
	    System.out.println("Falsch du Eimer! (" + correct_answer + ")");
	    log.log("Incorrect Answer;" + operator + ";" + LocalDateTime.now().format(d_t_formater));
	}
    }

    /**
     * testing method for question generation
     */
    private void genQuestion() {
	/**
	 * replace the 0 when negative result is wanted
	 */
	int result_of_question = rand.nextInt(10) - 0;

	a = rand.nextInt(10);

	if (result_of_question == 0 && a == 0) {
	    operator = rand.nextInt(1);
	} else {
	    operator = rand.nextInt(3);
	}

	// char operator_Char = 0;
	switch (operator) {
	case 0:
	    operator_Char = '+';
	    b = result_of_question - a;
	    break;
	case 1:
	    operator_Char = '-';
	    b = a - result_of_question;
	    break;
	case 2:
	    operator_Char = '*';
	    b = result_of_question / a;
	    break;
	case 3:
	    operator_Char = '/';
	    b = a / result_of_question;
	    break;
	}
	/**
	 * this is for fixing bug #5
	 * (https://github.com/F3l1XQu1ll/SimpleMathsTrainer/issues/5)
	 */

	/**
	 * convert b to a string (Possible BUG: .format() cuts decimals if there is more
	 * than 4 times a zero (0.00001 = 0), but i'm going to ignore that, because this
	 * happens never (or rather i haven't seen this)
	 */
	String bForDecimalTest = nf.format(b);
	/**
	 * check if there is a ".", if true, the number has decimals so we generate a
	 * new question, if false, it has no decimals
	 */
	/**
	 * I know, this method is brute-force, because we generate as many new questions
	 * as we need to get a good result. But i have no other idea, how to do this
	 * else; if someone has a better solution, bring it on! :)
	 */
	if (bForDecimalTest.contains(".")) {
	    genQuestion();
	}
    }
}
