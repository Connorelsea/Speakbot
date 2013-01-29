package elsea.speakbot.brain;

import java.util.ArrayList;

/**
*  <b>Speakbot.class</b></br>
*  <i>Oversees the production of simulated artificial intelligence.</i></br>
*  </br>
*  This class oversees and handles the production of simulated artificial intelligence, and
*  manages sorting pieces of knowledge.</br>
*
*  @creator Connor Elsea
*  @author <b>Elsea Laboratories;</b> Connor Elsea
*  @version 1.0.0
*
*/
public class Speakbot {
	
	/*
	 *  VARIABLES : Class-wide variables
	 */
	
	public ArrayList<String> HIST_INPUT;
	public ArrayList<String> HIST_OUTPUT;
	
	public String CURRENT_INPUT;
	public String CURRENT_OUTPUT;
	
	public ArrayList<IntelligenceElement> IES;
	public IntelligenceElement CURRENT_IE;
	
	public boolean DO_GLOBAL_INTERPRETATION;
	
	public boolean CURRENT_RESULT_VALUE;
	public boolean CURRENT_SUCCESS;
	
	/*
	 *  CONSTRUCTOR : Speakbot class constructor
	 */
	
	public Speakbot() {
		displayCredits();
		
		HIST_INPUT = new ArrayList<String>();
		HIST_OUTPUT = new ArrayList<String>();
		IES = new ArrayList<IntelligenceElement>();
		
		DO_GLOBAL_INTERPRETATION = true;
	}
	
	/*
	 *  INTERPRET METHOD : The interpret method
	 */
	
	public boolean interpret(String input) {
		
		this.setInput(input);
		
		// Do global interpretation
		
		if (DO_GLOBAL_INTERPRETATION == true) {
			
			// Cycle through all IEs
			
			for (IntelligenceElement current : IES) {
				
				// Set current iteration's IE's input then execute the IE
				
				setCurrentIE(current);
				getCurrentIE().setInput(this.getInput());
				setCurrentResultValue(getCurrentIE().execute());
				
				// Set SB configurations accordingly
				
				if (getCurrentResultValue() == true) {
					
					setOutput(getCurrentIE().getOutput());
					setGlobalInterpretation(getCurrentIE().getDesiredGlobalInter());
					CURRENT_SUCCESS = true;
					break;
					
				} else {
					CURRENT_SUCCESS = false;
				}
				
			}
			
			// If no IE was found
			
			if (CURRENT_SUCCESS == false) {
				setOutput("[Speakbot] Sorry, I didn't understand that.");
				setGlobalInterpretation(true);
				CURRENT_SUCCESS = false;
			}
		
		// Do local interpretation
		
		} else if (DO_GLOBAL_INTERPRETATION == false) {
			
			// Execute current IE
			
			getCurrentIE().setInput(this.getInput());
			setCurrentResultValue(getCurrentIE().execute());
			
			// Set SB configurations accordingly
			
			if (getCurrentResultValue() == true) {
				
				setOutput(getCurrentIE().getOutput());
				setGlobalInterpretation(getCurrentIE().getDesiredGlobalInter());
				CURRENT_SUCCESS = true;
				
			} else {
				CURRENT_SUCCESS = false;
			}
			
			// If failed, search re-perform global interpretation
			
			// Search through all IEs
			
			if (CURRENT_SUCCESS == false) {
				for (IntelligenceElement current : IES) {
					
					// Set current iteration's IE's input then execute the IE
					
					setCurrentIE(current);
					getCurrentIE().setInput(this.getInput());
					setCurrentResultValue(getCurrentIE().execute());
					
					// Set SB configurations accordingly
					
					if (getCurrentResultValue() == true) {
						
						setOutput(getCurrentIE().getOutput());
						setGlobalInterpretation(getCurrentIE().getDesiredGlobalInter());
						CURRENT_SUCCESS = true;
						break;
						
					} else {
						CURRENT_SUCCESS = false;
					}
					
				}
			}
			
			// If no IE was found
			
			if (CURRENT_SUCCESS == false) {
				setOutput("[Speakbot] Sorry, I didn't understand that.");
				setGlobalInterpretation(true);
				CURRENT_SUCCESS = false;
			}
			
		}
		
		return CURRENT_SUCCESS;
	}
	
	/*
	 *  INTERPRETATION : Handles methods relative to interpretation handling
	 */
	
	public void setGlobalInterpretation(boolean toValue) {
		DO_GLOBAL_INTERPRETATION = toValue;
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
	
	/*
	 *  INTELLIGENCE ELEMENTS : Methods relative to intelligence elements
	 */
	
	public void setCurrentIE(IntelligenceElement ie) {
		CURRENT_IE = ie;
	}
	
	public IntelligenceElement getCurrentIE() {
		return CURRENT_IE;
	}
	
	public void addIE(IntelligenceElement ie) {
		IES.add(ie);
	}
	
	/*
	 *  RESULT VALUE : Methods managing the current result value
	 */
	
	public void setCurrentResultValue(boolean value) {
		CURRENT_RESULT_VALUE = value;
	}
	
	public boolean getCurrentResultValue() {
		return CURRENT_RESULT_VALUE;
	}
	
	/*
	 *  CREDITS : Crediting the creator
	 */
	
	public void displayCredits() {
		System.out.printf("\nElsea Laboratories 2013\n" +
						  "Expiremental Software Division\n" +
						  "Speakbot 1.0.0\n\n");
	}

}