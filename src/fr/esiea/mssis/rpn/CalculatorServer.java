/**
 * 
 */
package fr.esiea.mssis.rpn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.esiea.mssis.rpn.calculator.InteractiveNumericCalculator;


/**
 * @author nic0w
 *
 * This class is a multi-threaded server
 *
 */
public class CalculatorServer implements Runnable {

	public static final int DEFAULT_PORT = 2013;
	
	private final int serverPort;
	
	private final boolean loggingEnabled;

	private boolean isRunning;
	
	/**
	 * 
	 */
	public CalculatorServer(int port) {
		
		this(port, false);
	}

	public CalculatorServer() {
		this(DEFAULT_PORT);
	}
	
	public CalculatorServer(int port, boolean enableLog) {
		
		this.serverPort = port;
		
		this.isRunning = false;
		
		this.loggingEnabled = enableLog;
	}

	@Override
	public void run() {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(this.serverPort);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return;
		}
		
		this.isRunning = true;
		
		System.out.println("Server started, listening at port " + serverPort);
		
		while(this.isRunning) {
			
			try {
				Socket newClient =  serverSocket.accept();
				
				System.out.println("Accepted new connection from " + newClient.getRemoteSocketAddress());
				
				new Thread(
						new InteractiveNumericCalculator(
								newClient.getInputStream(), 
								newClient.getOutputStream(),
								this.loggingEnabled
						)
				).start();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	

}
