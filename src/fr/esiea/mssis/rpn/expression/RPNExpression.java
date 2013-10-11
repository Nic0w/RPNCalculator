/**
 * 
 */
package fr.esiea.mssis.rpn.expression;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import fr.esiea.mssis.rpn.StackableObject;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.Operator;

/**
 * @author nic0w
 * 
 * This class is merely a stack and represent an expression written in Reverse Polish Notation
 *
 */
public class RPNExpression<T>  {
	
	private final Deque<StackableObject<T>> internalStack;
	
	public RPNExpression() {

		this.internalStack = new ArrayDeque<>();
	}
	
	/**
	 * Adds an element on the stack.
	 * 
	 * @param element the element to add to the stack
	 * @return self, to allow for fluent code writing
	 */
	protected RPNExpression<T> push(StackableObject<T> element) {
		
		this.internalStack.push(element);
		
		return this;
	}
	
	/**
	 * Calculates the result of the expression. This function is recursive.
	 * 
	 * @return The result as an Operand, to permit recursion and expression chaining.
	 */
	public Operand<T> calculate() {
		
		StackableObject<T> object = this.internalStack.poll(); //Get and remove the first element of the stack
		
		if(object instanceof Operand) //If the object is an Operand, it means we found the result !
			return (Operand<T>) object;
		
		if(object instanceof Operator) {//If the object is an Operator, we need to go "down" the tree of the expression
			
			Operator<T> operator = (Operator<T>) object;
			
			//TODO : add support for operators with more than 2 operators
			Operand<T> op1 = calculate(); //Recursively calculate first operand...
			
			Operand<T> op2 = calculate(); //and then the second
			
			return operator.doOperation(op1, op2); //do the operation and return the result !
		}
		
		throw new NoSuchElementException("The stack is empty !");
	}
	
	public String toString() {
		
		return this.internalStack.toString();
	}
	

}
