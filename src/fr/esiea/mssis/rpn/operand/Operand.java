package fr.esiea.mssis.rpn.operand;

import fr.esiea.mssis.rpn.StackableObject;

/**
 * @author nic0w
 *
 * This class represent an operand in a mathematical expression. 
 * It could be many thing : a variable (x, y,...), a numeric value, a matrix,...
 *
 * @param <T> what the operand is composed of.
 */
public interface Operand<T> extends StackableObject<T> {

	
	/**
	 * @return the value of this Operand
	 */
	public T getValue();
	
}
