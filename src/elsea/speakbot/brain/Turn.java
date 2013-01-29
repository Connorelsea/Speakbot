package elsea.speakbot.brain;

import elsea.speakbot.util.*;

public class Turn {
	
	/*
	 *  VARIABLES : Class-wide variables
	 */
	
	public int KEY;
	
	public IntelligenceElement IE;
	
	public ActionTurn LOGIC;
	
	public String CURRENT_OUTPUT;
	public String CURRENT_INPUT;
	
	public boolean RESULT_VALUE;
	
	/*
	 *  CONSTRUCTOR : This class' constructor
	 */
	
	public Turn(IntelligenceElement parentIE, int key) {
		IE = parentIE;
		KEY = key;
	}
	
	/*
	 * TURN END VALUES : Adds ease to ending a turn
	 */
	
	public void setEndValues(boolean tobeGlobaInter, int nextTurn) {
		IE.setDesiredGlobalInter(tobeGlobaInter);
		IE.setNextKey(nextTurn);
	}
	
	public void wipe() {
		CURRENT_OUTPUT = null;
		CURRENT_INPUT = null;
		RESULT_VALUE = (Boolean) null;
	}
	
	/*
	 *  EXECUTE : Executes the turns logic
	 */
	
	public boolean execute() {
		RESULT_VALUE = LOGIC.execute();
		return RESULT_VALUE;
	}
	
	/*
	 *  INPUT AND OUTPUT : Handles input and output
	 */
	
	public void setOutput(String output) {
		CURRENT_OUTPUT = output;
	}
	
	public String getOutput() {
		return CURRENT_OUTPUT;
	}
	
	public void setInput(String input) {
		CURRENT_INPUT = input;
	}
	
	public String getInput() {
		return CURRENT_INPUT;
	}
	
	/*
	 *  KEYS : Handles keys
	 */
	
	public int getKey() {
		return KEY;
	}

}
