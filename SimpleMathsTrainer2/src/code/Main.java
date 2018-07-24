package code;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// I am dumb. ALWAYS press ctrl + s before uploading the newest code

	System.out.println("Hello Calculators World!");

	/**
	 * initialize new Random
	 */
	Random rand = new Random();

	/**
	 * generate new operands
	 */
	double a = rand.nextInt(10);
	double b = rand.nextInt(10);

	/**
	 * generate the operator
	 */
	char operator_Char = 0;
	int op = rand.nextInt(4);

	switch (op) {
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

	double input = 0;

	System.out.println("Rechnen Sie " + a + " " + operator_Char + " " + b + " aus.");
	Scanner keyboard = new Scanner(System.in);
	input = keyboard.nextDouble();

	/**
	 * check users answer
	 */
	switch (op) {
	case 0:
	    showReply(input == a + b);
	    break;
	case 1:
	    showReply(input == a - b);
	    break;
	case 2:
	    showReply(input == a * b);
	    break;
	case 3:
	    showReply(input == a / b);
	}
	keyboard.close();
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
}
