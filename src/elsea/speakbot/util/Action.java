package elsea.speakbot.util;

/**
*  <b>Action.class</b></br>
*  <i>Holds an action.</i></br>
*  </br>
*  This class holds one method that can be altered when instantiating a new instance of
*  this class. It also can be identified by a integer key and a string name.</br>
*
*  @creator Connor Elsea
*  @author <b>Elsea Laboratories;</b> Connor Elsea
*  @version 1.0.0
*
*/
public abstract class Action {
	
	public int KEY;
	public String NAME;
	
	public boolean execute() {
		return build();
	}
	
	public abstract boolean build();
	
}
