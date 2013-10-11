/**
 * 
 */
package fr.esiea.mssis.rpn.operator.base;

import fr.esiea.mssis.rpn.operand.NumericOperand;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.AbstractOperator;

/**
 * @author nic0w
 *
 */
public class Substraction extends AbstractOperator<Number> {
	
	private static final char SUBSTRACTION_OPERATOR = '-';

	public Substraction() {
		super(SUBSTRACTION_OPERATOR);
	}

	@Override
	public Operand<Number> doOperation(Operand<Number>... operands) {
		
		double n1 = operands[0].getValue().doubleValue();
		double n2 = operands[1].getValue().doubleValue();
		
		return new NumericOperand(n1 - n2);
	}

}
