package code;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Trainer {

    /**
     * schon bekannt ...
     */
    private double a;
    private double b;
    private int operator;
    private Random rand = new Random();
    private Scanner keyboard = new Scanner(System.in);

    private DateTimeFormatter d_t_formater = DateTimeFormatter.ISO_DATE_TIME;
    private char operator_Char = 0;

    /**
     * Eine neue instanz der klasse Log (Log.java) anlegen
     */
    private Log<Object> log = new Log<>();

    /**
     * wird von Main.java aufgerufen; ist selbsterklärend
     */
    public void startTraining() {
	log.log("started new training;" + LocalDateTime.now().format(d_t_formater));
	/**
	 * tue etwas (den neuen versuch loggen) solange newTraining() true zurückgibt
	 */
	while (newTraining()) {
	    log.log("new Try;" + LocalDateTime.now().format(d_t_formater));
	}

	/**
	 * sollte nicht mehr true zurückgegeben werden (weil q eingegeben wurde)
	 */
	System.out.println("Wir sehen uns wieder!");
	/**
	 * log.close() ruft writer.close() auf und schreibt die Datei (sonst nicht weil
	 * BufferedWriter)
	 */
	log.close();

	/**
	 * beenden
	 */
	System.exit(0);
    }

    /**
     * schon bekannt
     * 
     * @return true wenn neues training gestartet werden soll oder false, falls
     *         beendet (q) wurde
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
	operator = rand.nextInt(3);

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
    }
}
