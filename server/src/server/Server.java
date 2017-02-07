/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

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

			//traditional rmi implementation
//			IServices stub = new Services();
//			Registry reg = LocateRegistry.createRegistry(8080);
//			reg.bind("rmi://localhost:8080", stub);
			
			//start server by instantiating Services ...
			Services services = new Services();
		
			System.out.println("Server listing on port 8080 and ready for action.");
		
	}

}
