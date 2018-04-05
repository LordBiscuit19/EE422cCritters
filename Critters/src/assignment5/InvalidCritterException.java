package assignment5;

/**
 * A special error for invalid critter types
 * @author LordBiscuit
 *
 */
public class InvalidCritterException extends Exception {
	String offending_class;
	
	
	/**
	 * sets the offending class name to the name of the critter class that threw it
	 * @param critter_class_name, the critter that threw the exception
	 */
	public InvalidCritterException(String critter_class_name) {
		offending_class = critter_class_name;
	}
	
	public String toString() {
		return "Invalid Critter Class: " + offending_class;
	}

}
