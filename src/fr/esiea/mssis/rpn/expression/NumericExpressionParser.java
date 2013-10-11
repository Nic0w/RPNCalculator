package fr.esiea.mssis.rpn.expression;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import fr.esiea.mssis.rpn.operand.NumericOperand;
import fr.esiea.mssis.rpn.operand.Operand;

/**
 * @author nic0w
 *
 * We want to parse numbers so this class's role is to parse the operands as Number objects.
 *
 */
public class NumericExpressionParser extends AbstractExpressionParser<Number> {

	private final NumberFormat numberFormat;
	
	public NumericExpressionParser(Locale locale) {
		
		this.numberFormat = NumberFormat.getInstance(locale);
	}
	
	public NumericExpressionParser() {
		this(Locale.ENGLISH);
	}

	@Override
	protected Operand<Number> parseOperand(String token) {
		
		Number n = null;
		try {
			
			n = this.numberFormat.parse(token);
			
		} catch (ParseException e) {

			e.printStackTrace();
			
			return new NumericOperand(Double.NaN); //If we fail to parse the number, an NaN is returned.
		}
		
		return new NumericOperand(n);
	}

	
	

}
