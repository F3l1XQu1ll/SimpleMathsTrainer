package code;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ich bin dämlich. man muss immer ctrl + s drücken...........
		System.out.println("Hello World!");
		int a = (int) (1 + Math.random() * 10);
		int b = (int) (1 + Math.random() * 10);

		int input = 0;

		System.out.println("Rechnen Sie " + a + " + " + b + " aus.");
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
