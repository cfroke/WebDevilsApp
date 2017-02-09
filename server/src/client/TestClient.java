/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package client;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

import common.User;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import common.Concept;
import common.IServices;

/**
 * @author Kevin Bryant
 *
 */
public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 CallHandler callHandler = new CallHandler();

	        // first we need to connect..
	        Client client = null;
	        try {
	            client = new Client("localhost", 8080, callHandler);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        // then we can get the remote reference
	        IServices services = (IServices) client.getGlobal(IServices.class);
	        
		User returnedUser = services.createMemberUser("test", "password");
		
		
		// traditional java.rmi Impl
//		try {
//			//connect to server
//			Registry reg = LocateRegistry.getRegistry(8080);
//            IServices services = (IServices) reg.lookup("rmi://localhost:8080");
//			
//            //test remote methods
//            User user = services.createMemberUser("user", "password");
//            user = services.validateUser("user", "password");
//            if(user != null){
//            	System.out.println("User authenticated");
//            	System.out.println("User object returned from server");
//            	services.createConcept(user, "test concept description 1");
//            	services.createConcept(user, "test concept description 2");
//            	services.createConcept(user, "test concept description 3");
//            }else{
//            	System.out.println("User not authenticated or returned from server");
//            }
//            
//            if(user != null){
//            	System.out.println("Getting concepts from server ...");
//            	LinkedList<Concept> conceptList = services.getConceptsByUser(user);
//            	for(Concept concept : conceptList){
//            		System.out.println("Concept desc from server: " + concept.getDescription());
//            	}
//            }
//            
//		}catch (Exception e) {
//			System.out.println("TestClient exception: " + e);
//		}

	}
}
