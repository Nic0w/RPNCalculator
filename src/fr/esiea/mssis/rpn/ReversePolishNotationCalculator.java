/**
 * 
 */
package fr.esiea.mssis.rpn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import fr.esiea.mssis.rpn.calculator.InteractiveNumericCalculator;

/**
 * @author nic0w
 *
 * Software for computing mathematical expressions written in Reverse Polish Notation.
 * There are 3 modes :
 * 	- Interactive -> classic command line interface; type your expression then Enter to see the result
 *  - Server -> to compute expressions remotely, using -server option.
 *  - Replay -> to replay a log file, using -replay [filename]
 *  
 * A -log option is also available to record the expressions typed either in Interactive or Server modes. 
 *
 * 
 */
public class ReversePolishNotationCalculator {
	
	private final boolean loggingEnabled;
	
	public ReversePolishNotationCalculator(boolean logging) {
		
		this.loggingEnabled = logging;
	}
	
	public void replayMode(String filename) {
		
		FileInputStream fileStream = null;
		try {
			
			fileStream = new FileInputStream(filename);
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			return;
		}	
	
		new InteractiveNumericCalculator(fileStream, System.out, false).run(); //Logging is disabled when replaying
		
	}
	
	public void serverMode() {
		
		new CalculatorServer(CalculatorServer.DEFAULT_PORT, this.loggingEnabled).run();
		
	}
	
	public void interactiveMode() {
		
		new InteractiveNumericCalculator(System.in, System.out, this.loggingEnabled).run();
	}
	
	
	public static void main(String args[]) {

		List<String> arguments = Arrays.asList(args);
		
		//Enable logging if -log is present in the args List
		boolean enableLog = arguments.contains("-log");
		
		
		ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator(enableLog);
		
		
		if(arguments.contains("-replay")) {
			
			if(arguments.size() == 1) {
				System.out.println("No file specified");
				return;
			}
			
			//Launch replay mode
			calculator.replayMode(arguments.get(1));
			
			return;
		}
		
		
		if(arguments.contains("-server")) {
			
			//Launch Server mode
			calculator.serverMode();
			
			return;
		}
		

		//Launch interactive mode
		calculator.interactiveMode();
		
	}


	
	
}
