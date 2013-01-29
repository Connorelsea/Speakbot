package elsea.speakbot.testing;

import java.util.Scanner;

import elsea.speakbot.brain.*;
import elsea.speakbot.repositories.ElementRepository;

public class DisplayConsole implements Runnable {
	
	public Speakbot SPEAKBOT;
	public Thread THREAD;
	
	public String INPUT_CURRENT;
	public String OUTPUT_CURRENT;
	
	public DisplayConsole() {
		SPEAKBOT = new Speakbot();
		ElementRepository.generate();
		SPEAKBOT.IES.addAll(ElementRepository.ALL);
		startThread();
	}
	
	public void startThread() {
		THREAD = new Thread(this);
		THREAD.start();
	}
	
	public void run() {
		
		while (true) {
			
			System.out.print("input:// ");
			INPUT_CURRENT = new Scanner(System.in).nextLine();
			
			SPEAKBOT.interpret(INPUT_CURRENT);
			System.out.println(SPEAKBOT.getOutput());
			
		}
		
	}

	
}