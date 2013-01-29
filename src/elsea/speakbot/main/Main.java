package elsea.speakbot.main;

import elsea.speakbot.testing.*;

public class Main {
	public static void main(String[] args) {
		
		boolean inConsole = false;
		
		try {
			if (args[0].equals("-console")) {
				inConsole = true;
				Runtime.getRuntime().exec("title Elsea Laboratories");
				new DisplayConsole();
			}
		} catch (Exception ex) { }
		
		if (inConsole == false) {
			new DisplayUI().setVisible(true);
		}
	}
	
}