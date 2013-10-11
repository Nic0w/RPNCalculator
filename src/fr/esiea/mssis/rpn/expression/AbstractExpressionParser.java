/**
 * 
 */
package fr.esiea.mssis.rpn.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.esiea.mssis.rpn.StackableObject;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.Operator;

/**
 * @author nic0w
 *
 * This class the base for an expression parser : it manages the operators and the parsing.
 *
 */
public abstract class AbstractExpressionParser<T> implements ExpressionParser<T> {

	private final Map<Pattern, Operator<T>> operators;
	
	/**
	 * 
	 */
	public AbstractExpressionParser() {
		this.operators = new HashMap<>();
	}


	@Override
	public final void registerOperator(Operator<T> operator) {
		
		this.operators.put(operator.getPattern(), operator);
	}


	/**
	 * @param token a token from the expression to be parsed
	 * @return the corresponding operator, or null;
	 */
	private Operator<T> parseOperator(String token) {
		
		Matcher matcher;
		
		for(Map.Entry<Pattern, Operator<T>> mapEntry : this.operators.entrySet()) {

			Pattern operatorPattern = mapEntry.getKey();
			Operator<T> operator    = mapEntry.getValue();
			
			matcher = operatorPattern.matcher(token);
			
			if(matcher.matches())
				return operator;
		}
		
		//No operator found
		return null;
	}

	/**
	 * @param token a token from the expression to be parsed
	 * @return An operand corresponding to the current token
	 */
	protected abstract Operand<T> parseOperand(String token);
	
	@Override
	public RPNExpression<T> parse(String line) {
		
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		RPNExpression<T> expression = new RPNExpression<>();
		
		String token;
		while(tokenizer.hasMoreTokens()) {
			
			token = tokenizer.nextToken();
			
			StackableObject<T> object = null;
			
			//First we search for an operator
			if((object = parseOperator(token)) != null) {
				
				expression.push(object);
				continue;
			}
				
			//if none was found, we search for an operand
			if((object = parseOperand(token)) == null)
				throw new IllegalArgumentException("Unknown token : '"+ token +"'");
			
			
			expression.push(object);
		}
		
		return expression;
	}

	
	

}
