package elsea.speakbot.util;

import java.util.ArrayList;
import java.util.Random;

public class ResponseContainer {
	
	private ArrayList<String> VERSIONS;
	private Random RANDOM;
	
	public ResponseContainer(String string) {
		RANDOM = new Random();
		VERSIONS = new ArrayList<String>();
		VERSIONS.add(string);
	}
	
	public void addVersion(String string) {
		VERSIONS.add(string);
	}
	
	public String getRandomVersion() {
		return VERSIONS.get(RANDOM.nextInt(VERSIONS.size()));
	}

}