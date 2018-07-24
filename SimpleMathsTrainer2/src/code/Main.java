package code;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	// I am dumb. ALWAYS press ctrl + s before uploading the newest code

	System.out.println("Hello Calculators World!");
	Random rand = new Random();
	double a = rand.nextInt(10);
	double b = rand.nextInt(10);
	//pathed
	
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

	System.out.println("Rechnen Sie " + a + operator_Char + b + " aus.");
	Scanner keyboard = new Scanner(System.in);
	input = keyboard.nextDouble();

	if (a + b == input) {

	    System.out.println("Richtig :D");

	}
	if (a + b != input) {

	    System.out.println("FALSCH!");
	}
	keyboard.close();
    }
}
