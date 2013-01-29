package elsea.speakbot.brain;

import java.util.ArrayList;

public class IntelligenceElement {
	
	/*
	 *  VARIABLES : Class-wide variables
	 */
	
	public int KEY;
	
	public ArrayList<String> HIST_OUTPUT;
	public ArrayList<String> HIST_INPUT;
	
	public String CURRENT_OUTPUT;
	public String CURRENT_INPUT;

	public ArrayList<Turn> TURNS;
	public Turn CURRENT_TURN;
	
	public int KEY_DEFAULT;
	public int KEY_NEXT;
	
	public boolean RUNNING;
	public boolean RESULT_VALUE;
	public boolean TOBE_GLOBAL_INTER;
	
	/*
	 *  CONSTRUCTOR : The class' constructor method
	 */
	
	public IntelligenceElement(int key) {
		KEY = key;
		
		HIST_OUTPUT = new ArrayList<String>();
		HIST_INPUT = new ArrayList<String>();
		TURNS = new ArrayList<Turn>();
		
		RUNNING = false;
		TOBE_GLOBAL_INTER = true;
	}
	
	/*
	 *  EXECUTE METHOD : Method for executing the actions of this IE
	 */
	
	public boolean execute() {
		
		if (RUNNING) {
			setCurrentTurn(findTurn(KEY_NEXT));
		} else {
			setCurrentTurn(findTurn(KEY_DEFAULT));
		}
		
		if (getCurrentTurn() == null) {
			System.out.println("[Error] Current turn is null");
			setDesiredGlobalInter(true);
			return false;
		}
		
		getCurrentTurn().setInput(this.getInput());
		setResultValue(getCurrentTurn().execute());
		
		if (getResultValue() == true) {
			setOutput(getCurrentTurn().getOutput());
		} else {
			setDesiredGlobalInter(true);
		}
		
		if (getDesiredGlobalInter() == false) {
			RUNNING = true;
		} else {
			RUNNING = false;
		}
		
		return getResultValue();
	}
	
	/*
	 *  GLOBAL INTERPRETATION : Handles global interpretation
	 */
	
	public void setDesiredGlobalInter(boolean value) {
		TOBE_GLOBAL_INTER = value;
	}
	
	public boolean getDesiredGlobalInter() {
		return TOBE_GLOBAL_INTER;
	}
	
	/*
	 *  RESULT VALUE : Getting and setting the current result value
	 */
	
	public void setResultValue(boolean value) {
		RESULT_VALUE = value;
	}
	
	public boolean getResultValue() {
		return RESULT_VALUE;
	}
	
	/*
	 *  KEYS : Handles keys and key switching
	 */
	
	public int getDefaultKey() {
		return KEY_DEFAULT;
	}
	
	public void setDefaultKey(int key) {
		KEY_DEFAULT = key;
	}
	
	public int getNextKey() {
		return KEY_NEXT;
	}
	
	public void setNextKey(int key) {
		KEY_NEXT = key;
	}
	
	/*
	 *  TURNS : Handles turns
	 */
	
	public Turn findTurn(int key) {
		for (Turn current : TURNS) {
			if (current.getKey() == key) return current;
		}
		return null;
	}
	
	public void setCurrentTurn(Turn turn) {
		CURRENT_TURN = turn;
	}
	
	public Turn getCurrentTurn() {
		return CURRENT_TURN;
	}
	
	public void addTurn(Turn turn) {
		TURNS.add(turn);
	}
	
	/*
	 *  INPUT AND OUTPUT : Handles input and output
	 */
	
	public void setOutput(String output) {
		HIST_OUTPUT.add(output);
		CURRENT_OUTPUT = output;
	}
	
	public String getOutput() {
		return CURRENT_OUTPUT;
	}
	
	public void setInput(String input) {
		HIST_INPUT.add(input);
		CURRENT_INPUT = input;
	}
	
	public String getInput() {
		return CURRENT_INPUT;
	}
	
}