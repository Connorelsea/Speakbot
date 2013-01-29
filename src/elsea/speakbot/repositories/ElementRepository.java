package elsea.speakbot.repositories;

import java.text.DecimalFormat;
import java.util.ArrayList;

import elsea.speakbot.brain.*;
import elsea.speakbot.util.ActionTurn;
import elsea.speakbot.util.ResponseContainer;

public class ElementRepository {
	
	public static ArrayList<IntelligenceElement> ALL;
	
	public static void generate() {
		
		ALL = new ArrayList<IntelligenceElement>();
		
		gen_elConvGeneral();
		gen_utilMathStart();
	}
	
	/*
	 *  ELEMENT CONVERSATION GENERAL : EL_CONV_GENERAL
	 */
	
	static IntelligenceElement EL_CONV_GENERAL = new IntelligenceElement(1001);
		static Turn TURN_CONVGEN_START = new Turn(EL_CONV_GENERAL, 1001);
		static Turn TURN_CONVGEN_HOWREPLY = new Turn(EL_CONV_GENERAL, 1002);
	
	public static void gen_elConvGeneral() {
		
		EL_CONV_GENERAL.setDefaultKey(1001);
		
		/*
		 *  TURN CONVERSATION GENERAL START : TURN_CONVGEN_START
		 */
		
		TURN_CONVGEN_START.LOGIC = new ActionTurn(EL_CONV_GENERAL, TURN_CONVGEN_START) {
			public boolean build() {
				boolean toReturn = false;
				
				if (TURN.getInput().toLowerCase().contains("hello") ||
					TURN.getInput().toLowerCase().contains("hey") ||
					TURN.getInput().toLowerCase().contains("hi")) {
					
					ResponseContainer RC = new ResponseContainer("Hello.");
					RC.addVersion("Hello to you too.");
					RC.addVersion("Hi!");
					RC.addVersion("Greetings.");
					RC.addVersion("Hey.");
					RC.addVersion("Hey!");
					
					TURN.setOutput(RC.getRandomVersion());
					TURN.setEndValues(true, 0);
					toReturn = true;
				}
				
				if (TURN.getInput().toLowerCase().contains("how are you")) {
					
					ResponseContainer RC = new ResponseContainer("I'm doing well. How are you?");
					RC.addVersion("I'm doing fantastic. How are you?");
					RC.addVersion("I'm running smoothly. How are you doing?");
					
					TURN.setOutput(RC.getRandomVersion());
					TURN.setEndValues(false, 1002);
					toReturn = true;
				}
				
				return toReturn;
			}
		};
		
		EL_CONV_GENERAL.addTurn(TURN_CONVGEN_START);
		
		/*
		 *  TURN CONVERSATION GENERAL HOW REPLY : TURN_CONVGEN_HOWREPLY
		 */
		
		TURN_CONVGEN_HOWREPLY.LOGIC = new ActionTurn(EL_CONV_GENERAL, TURN_CONVGEN_HOWREPLY) {
			public boolean build() {
				boolean toReturn = false;

				if (TURN.getInput().toLowerCase().contains("good")) {
					TURN.setOutput("That's lovely.");
					TURN.setEndValues(true, 0);
					toReturn = true;
				}
				
				if (TURN.getInput().toLowerCase().contains("bad")) {
					TURN.setOutput("That's horrible! I hope you feel better.");
					TURN.setEndValues(true, 0);
					toReturn = true;
				}
				
				return toReturn;
			}
		};
		
		EL_CONV_GENERAL.addTurn(TURN_CONVGEN_HOWREPLY);
		
		ALL.add(EL_CONV_GENERAL);
		
	}
	
	/*
	 *  ELEMENT CONVERSATION GENERAL : EL_CONV_GENERAL
	 */
	
	static IntelligenceElement EL_UTIL_MATH = new IntelligenceElement(1002);
		static Turn TURN_UTMATH_START = new Turn(EL_UTIL_MATH, 1001);
		
	public static void gen_utilMathStart() {
		
		EL_UTIL_MATH.setDefaultKey(1001);
		
		TURN_UTMATH_START.LOGIC = new ActionTurn(EL_UTIL_MATH, TURN_UTMATH_START) {
			public boolean build() {
				
				/*
				 *  Important Note Regarding This Section:
				 *  Frankly, the math code is embarrassing and rushed. The only reason I
				 *  didn't take it out is because even with faults, and horrible rushed
				 *  coding, some of it still works.
				 *  
				 *  Current Problems:
				 *  - Can't use multiple operators in one line of math.
				 *  - Related to prior; always uses last operator in line of math for every number action.
				 */
				
				boolean toReturn = false;
				
				char[] inputarr = TURN.getInput().toCharArray();
				
				if (Character.isDigit(inputarr[0])) {
					toReturn = true;
				} else {
					return false;
				}
				
				ArrayList<ArrayList<Integer>> INTEGER_GROUPS = new ArrayList<ArrayList<Integer>>();
				int GROUP_AT = 0;
				ArrayList<String> OPERATORS = new ArrayList<String>();
				
				int RESULT = 0;
				int DYPOS;
				
				if (toReturn == true) {
					
					try {
						
						for (char cur : inputarr) {
							if (Character.isDigit(cur)) {
								if (GROUP_AT == INTEGER_GROUPS.size()) {
									INTEGER_GROUPS.add(new ArrayList<Integer>());
								}
								INTEGER_GROUPS.get(GROUP_AT).add(Integer.parseInt(cur + ""));
							} else {
								if (Character.isWhitespace(cur)) {
									
								} else {
									GROUP_AT += 1;
									OPERATORS.add(cur + "");
								}
							} 
						}
						
						int gtr = 0;
						
						for (int i = 0; i != GROUP_AT + 1; i++) {
							
							System.out.println("OPERATOR: " + OPERATORS.get(GROUP_AT - 1));
							
							String curString = "";
							int curInt = 0;
							
							for (int a : INTEGER_GROUPS.get(i)) {
								curString += a;
							}
							
							if (gtr == 0) {
								System.out.println("Setting initial result");
								RESULT = Integer.parseInt(curString);
								gtr++;
							} else {
								
								int curResult = RESULT;
								int curInteger = Integer.parseInt(curString);
								
								if (OPERATORS.get(GROUP_AT - 1).equals("+")) {
									
									RESULT = curResult + curInteger;
									System.out.println(curResult + "+" + curInteger);
									
								} else if (OPERATORS.get(GROUP_AT - 1).equals("-")) {
									
									RESULT = curResult - curInteger;
									System.out.println(curResult + "-" + curInteger);
									
								} else if (OPERATORS.get(GROUP_AT - 1).equals("*")) {
									
									RESULT = (RESULT * Integer.parseInt(curString));
									System.out.println(curResult + "*" + curInteger);
									
								} else if (OPERATORS.get(GROUP_AT - 1).equals("/")) {
									
									RESULT = (RESULT / Integer.parseInt(curString));
									System.out.println(curResult + "/" + curInteger);
									
								}
								
							}
							
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
						return false;
					}
					
					double amount = Double.parseDouble(RESULT + "");
					DecimalFormat df = new DecimalFormat("#,###");
					
					TURN.setOutput("That equals " + df.format(amount));
					TURN.setEndValues(true, 0);
					toReturn = true;
					
				}
				
				return toReturn;
			}
		};
		
		EL_UTIL_MATH.addTurn(TURN_UTMATH_START);
		
		ALL.add(EL_UTIL_MATH);
		
	}
	
}