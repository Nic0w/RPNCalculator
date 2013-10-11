package fr.esiea.mssis.rpn.operand;

/**
 * @author nic0w
 *
 * Represent a numeric operand : it is composed of a Number, so it supports every native type of Java.
 *
 */
public class NumericOperand implements Operand<Number> {

	
	private final Number value;
	
	
	public NumericOperand(Number n) {
		this.value = n;
	}

	
	public Number getValue() {
		return this.value;
	}
	
	public String toString() {
		
		return this.getValue()+"";
	}
	
}
