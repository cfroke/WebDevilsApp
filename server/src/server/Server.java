/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.rmi.*;
import java.rmi.registry.*;

import common.User;

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
	public static void main(String[] args) {
		try {
			
			//TODO
			// initialize storage
			
			IServices stub = new Services();
			Registry reg = LocateRegistry.createRegistry(8080);
			reg.bind("rmi://localhost:8080", stub);
			
			System.out.println("Server bound to 'localhost:8080' and ready for action.");
			
			}catch (Exception e) {
				System.out.println("Server failed: " + e);
			}
		
	}

}
