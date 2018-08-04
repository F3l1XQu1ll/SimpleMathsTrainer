package code;

import gui.GuiStartup;

public class Main {

    public static void main(String[] args) {
	// I am dumb. ALWAYS press ctrl + s before uploading the newest code

	System.out.println("Hello Calculators World! \nUse q to quit or l to see your stas.");

	// Trainer trainer = new Trainer();

	GuiStartup guiStartup = new GuiStartup();
	guiStartup.start(args);

	// trainer.startTraining();
    }
}
