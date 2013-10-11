/**
 * 
 */
package fr.esiea.mssis.rpn.operator;

import java.util.regex.Pattern;

import fr.esiea.mssis.rpn.StackableObject;
import fr.esiea.mssis.rpn.operand.Operand;

/**
 * @author nic0w
 * @param <T> Type of the Object on which this operator can operate.
 *
 * This class represents an operator in a mathematical expression.
 *
 */
public interface Operator<T> extends StackableObject<T> {
	
	
	/**
	 * This function is where the computation is done. 
	 * It takes a variable amount of arguments to allow for operators which take more (or less) than two args.
	 * 
	 * @param operands an array of operand
	 * @return the result of the operation, as an operand.
	 */
	public Operand<T> doOperation(@SuppressWarnings("unchecked") Operand<T> ... operands);

	/**
	 * @return a regex to identify the operator in a String.
	 */
	public Pattern getPattern();
	
}
