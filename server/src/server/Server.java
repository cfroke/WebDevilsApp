/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * @author Kevin Bryant
 * 
 * This class is used to initialize the server that communicates with the android app
 *
 */
public class Server {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// my not need ?
//			System.setSecurityManager(new RMISecurityManager());
			   
			Services services = new Services();			   		   
			Naming.rebind("rmi://localhost:8080/serverTestLocation", services);
			
			System.out.println("Server bound to 'localhost' and ready for action.");
			}catch (Exception e) {
				System.out.println("Server failed: " + e);
			}
		
	}

}
