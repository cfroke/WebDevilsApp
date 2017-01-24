/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * @author Kevin Bryant
 *
 */
@SuppressWarnings("deprecation")
public class TestClient {
	

	@SuppressWarnings("unused")
	private static IServices services;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			//my not need ?
//			System.setSecurityManager(new RMISecurityManager());
			services = (IServices)Naming.lookup("rmi://localhost:8080/clientTestLocation");
			
			// invoke interface method
//			services.createConcept(null, null);
//			System.out.println("Result is :"+result);
			
		}catch (Exception e) {
			System.out.println("TestClient exception: " + e);
		}

	}
}
