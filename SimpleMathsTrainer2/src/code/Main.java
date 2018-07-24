package code;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	// ich bin dämlich. man muss immer ctrl + s drücken...........

	System.out.println("Hello World!");
	int a = (int) (1 + Math.random() * 10);
	int b = (int) (1 + Math.random() * 10);

	Random rand = new Random();
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

	int input = 0;

	System.out.println("Rechnen Sie " + a + operator_Char + b + " aus.");
	Scanner keyboard = new Scanner(System.in);
	input = keyboard.nextInt();

	if (a + b == input) {

	    System.out.println("Richtig :D");

	}
	if (a + b != input) {

	    System.out.println("FALSCH!");
	}
	keyboard.close();
    }
}
