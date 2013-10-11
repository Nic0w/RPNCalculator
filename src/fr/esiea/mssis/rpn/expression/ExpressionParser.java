/**
 * 
 */
package fr.esiea.mssis.rpn.expression;

import fr.esiea.mssis.rpn.operator.Operator;

/**
 * @author nic0w
 *
 * 
 */
public interface ExpressionParser<T> {
	
	/**
	 * @param operator add an operator to the list of supported operators
	 */
	public void registerOperator(Operator<T> operator);
	
	/**
	 * @param line the line to be parsed
	 * @return An object representing an expression written in Reverse Polish Notation
	 */
	public RPNExpression<T> parse(String line);
	

}
