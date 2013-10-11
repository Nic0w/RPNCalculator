package fr.esiea.mssis.rpn.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import fr.esiea.mssis.rpn.expression.NumericExpressionParser;
import fr.esiea.mssis.rpn.expression.RPNExpression;
import fr.esiea.mssis.rpn.operand.Operand;
import fr.esiea.mssis.rpn.operator.base.Addition;
import fr.esiea.mssis.rpn.operator.base.Division;
import fr.esiea.mssis.rpn.operator.base.Multiplication;
import fr.esiea.mssis.rpn.operator.base.Substraction;

/**
 * @author nic0w
 * 
 * This class manages the interface with the human, either through the command line, 
 * the network or a replay file, or any other couple of InputStream/OutputStream.
 *
 */
public class InteractiveNumericCalculator implements Runnable {

	private final BufferedReader in;

	private final PrintWriter out;

	private final NumericExpressionParser parser;
	
	private boolean logEnabled;
	

	public InteractiveNumericCalculator(InputStream in, OutputStream out, boolean log) {

		this.in = new BufferedReader(new InputStreamReader(in));

		this.out = new PrintWriter(out, true);
		
		this.parser = new NumericExpressionParser();
		
		//Enable classic operators +, *, /, -
		parser.registerOperator(new Addition());
		parser.registerOperator(new Multiplication());
		parser.registerOperator(new Division());
		parser.registerOperator(new Substraction());
		
		this.logEnabled = log;
	}

	@Override
	public void run() {
		
		this.out.println("- Reverse Polish Notation Expressions Parser -");
		
		boolean isRunning = true;
		
		PrintWriter log = null; 

		if(logEnabled) {
			
			//Create log file with an unique name.
			File logFile = new File("log_"+ System.currentTimeMillis()  +".txt");
			
			try {
				log = new PrintWriter(new FileWriter(logFile), true);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				logEnabled = false;
			}
		}
		
		String line; 
		RPNExpression<Number> expression;
			
		while(isRunning) { //While there are no errors and the user didn't close the connection.

			try {
				line = this.in.readLine();
				
				if(line == null || line.trim().equalsIgnoreCase("stop")) {
					
					System.out.println("User closed connection !");
					
					isRunning = false;
					
					continue;
				}

				if(logEnabled)
					log.println(line);
				
				//Parse expression
				expression = parser.parse(line);


			} catch (IOException e) {

				System.out.println(e.getMessage());

				isRunning = false;
				break;

			} catch(IllegalArgumentException e) {
				
				out.println(e.getMessage());

				e.printStackTrace();
				
				continue;
			}

			Operand<Number> result = null;
			try {
				//Calculate the result
				result = expression.calculate();
				
			} catch (Exception e) {
				out.println(e.getMessage());
				e.printStackTrace();
			}

			out.println("result = " + result);
		}

		this.out.close();

	}





}
