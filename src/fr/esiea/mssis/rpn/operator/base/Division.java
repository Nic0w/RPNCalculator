package fr.esiea.mssis.rpn.operator.base;

import fr.esiea.mssis.rpn.operand.NumericOperand;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.AbstractOperator;

public class Division extends AbstractOperator<Number> {

	private static final char DIVISION_OPERATOR = '/';
	
	public Division() {
		super(DIVISION_OPERATOR);
		// TODO Auto-generated constructor stub
	}

	public Division(char operator) {
		super(operator);
	}

	@Override
	public Operand<Number> doOperation(Operand<Number>... operands) {
		
		double n1 = operands[0].getValue().doubleValue();
		double n2 = operands[1].getValue().doubleValue();
		
		return new NumericOperand(n1 / n2);
	}

}
