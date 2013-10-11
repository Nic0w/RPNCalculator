/**
 * 
 */
package fr.esiea.mssis.rpn.operator;

import java.util.regex.Pattern;

/**
 * @author nic0w
 *
 * @param <T>
 * 
 * Provide default Constructors and methods to subclasses in order to avoid boring copy/paste of repetitive code (toString & getPattern
 */
public abstract class AbstractOperator<T> implements Operator<T> {

	private final String stringRepresentation;
	
	private final Pattern operatorPattern;
	
	protected AbstractOperator(String representation, String regex) {
		
		this.stringRepresentation = representation;
		
		this.operatorPattern = Pattern.compile(regex);
	}
	
	protected AbstractOperator(char operator) {

		this(operator+"", Pattern.quote(operator+""));
	}

	@Override
	public final Pattern getPattern() {
	
		return this.operatorPattern;
	}
	
	public String toString() {
		
		return this.stringRepresentation;
	}

}
