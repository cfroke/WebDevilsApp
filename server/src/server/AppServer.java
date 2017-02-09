/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.io.IOException;
import java.net.Socket;

import common.IServices;
import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;
/**
 * @author Kevin Bryant
 * 
 * This class is used to initialize the server that communicates with the android app
 *
 */
public class AppServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			//traditional rmi implementation
//			IServices stub = new Services();
//			Registry reg = LocateRegistry.createRegistry(8080);
//			reg.bind("rmi://localhost:8080", stub);
			
			//start server by instantiating Services ...
			Services services = new Services();
			
			try {
	            CallHandler callHandler = new CallHandler();
	            callHandler.registerGlobal(IServices.class, services);
	            lipermi.net.Server server = new lipermi.net.Server();
	            server.bind(8080, callHandler);
	            server.addServerListener(new IServerListener() {

	                @Override
	                public void clientDisconnected(Socket socket) {
	                    System.out.println("Client Disconnected: " + socket.getInetAddress());
	                }

	                @Override
	                public void clientConnected(Socket socket) {
	                    System.out.println("Client Connected: " + socket.getInetAddress());
	                }
	            });
	            System.out.println("Server Online ... ");
	        } catch (LipeRMIException | IOException e) {
	        	System.out.println("Server failed: " + e);
	        }
		
			System.out.println("Server listing on port 8080 and ready for action.");
		
	}

}
