package fr.esiea.mssis.rpn.operator.base;

import fr.esiea.mssis.rpn.operand.NumericOperand;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.AbstractOperator;

public class Multiplication extends AbstractOperator<Number> {

	private static final char MULTIPLICATION_OPERATOR = '*';
	
	public Multiplication() {
		super(MULTIPLICATION_OPERATOR);
	}

	@Override
	public Operand<Number> doOperation(Operand<Number>... operands) {
		

		double n1 = operands[0].getValue().doubleValue();
		double n2 = operands[1].getValue().doubleValue();

		
		return new NumericOperand(n1 * n2);
	}



}
