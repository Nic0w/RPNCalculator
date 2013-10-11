package fr.esiea.mssis.rpn.operator.base;

import fr.esiea.mssis.rpn.operand.NumericOperand;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.AbstractOperator;

public class Addition extends AbstractOperator<Number> {

	private static final char ADDITION_OPERATOR = '+';
	
	public Addition() {
		super(ADDITION_OPERATOR);
	}

	@Override
	public Operand<Number> doOperation(Operand<Number>... operands) {
		
		double result = 0;
		
		for(Operand<Number> op : operands)
			result += op.getValue().doubleValue();
		
		return new NumericOperand(result);
	}
}
