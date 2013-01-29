package elsea.speakbot.util;

import elsea.speakbot.brain.*;

/**
*  <b>ActionTurn.class</b></br>
*  <i>Holds an action an IntelligenceElement, and a Turn.</i></br>
*  </br>
*  This class is an extension of the Action class. It holds an Intelligence Element
*  object and a Turn object in order to make the creation of turns and their logic
*  easier by providing direct access from within the action class to the Intelligence 
*  Element and Turn said action is intended for use in.</br>
*
*  @creator Connor Elsea
*  @author <b>Elsea Laboratories;</b> Connor Elsea
*  @version 1.0.0
*
*/
public abstract class ActionTurn extends Action {
	
	public IntelligenceElement IE;
	public Turn TURN;
	
	public ActionTurn(IntelligenceElement IE, Turn turn) {
		this.IE = IE;
		TURN = turn;
	}
	
}