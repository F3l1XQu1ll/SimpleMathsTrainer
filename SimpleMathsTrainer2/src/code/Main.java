package code;

import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * initialize new Random
     */
    private static Random rand = new Random();

    private static double a, b;
    private static int operand;

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
	// I am dumb. ALWAYS press ctrl + s before uploading the newest code

	System.out.println("Hello Calculators World! \nUse q to quit.");

	while (newTraining()) {

	}

    }

    /**
     * show a message to the user depending on was the given answer correct
     * 
     * @param isCorrect
     *                      defines if the given answer was correct
     */
    private static void showReply(boolean isCorrect) {
	if (isCorrect)
	    System.out.println("Richtig :D");
	else
	    System.out.println("FALSCH!");
    }

    private static boolean newTraining() {
	/**
	 * generate new operands
	 */
	a = rand.nextInt(10);
	b = rand.nextInt(10);

	/**
	 * generate the operator and prevent division by zero
	 */
	char operator_Char = 0;
	if (b != 0)
	    operand = rand.nextInt(4);
	else
	    operand = rand.nextInt(3);
	
	switch (operand) {
	case 0:
	    operator_Char = '+';
	    break;
	case 1:
	    operator_Char = '-';
	    break;
	case 2:
	    operator_Char = '*';
	    break;
	case 3:
	    operator_Char = '/';
	    break;
	}

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
	switch (operand) {
	case 0:
	    showReply(attempt == a + b);
	    break;
	case 1:
	    showReply(attempt == a - b);
	    break;
	case 2:
	    showReply(attempt == a * b);
	    break;
	case 3:
	    showReply(attempt == a / b);
	}

	return true;
    }
}
