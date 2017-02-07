/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.io.Serializable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import common.Concept;
import common.User;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

/**
 * @author Kevin Bryant
 *
 */
public class Services implements IServices, Serializable {

	private static final long serialVersionUID = -7875091839442836772L;
	
	Storage storage;
	
	protected Services(){
		try {
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(IServices.class, this);
            Server server = new Server();
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
		storage = Storage.getInstance();
		System.out.println("//// Server Services Loaded ////");
	}
	
	public User validateUser(String userName, String password){
		User user = storage.getUser(userName);
		if(user == null){
			System.out.println("User Name is not in storage, unable to validate");
		}else{
			if(user.tryPassword(password)){
				System.out.println("User '" + user.getUserName() +"' logged in ...");
				return user;
			}else{
				System.out.println("Incorrect Password entered for '" + user.getUserName() + "'");
			}
		}
		return null;
	}
	
	public LinkedList<Concept> getConceptsByUser(User user){
		System.out.println("Sent concept list to client");
		return storage.getConceptsByUserName(user.getUserName());
	}
	
	public Concept createConcept(User user, String description){
		Concept result = new Concept(user,description);
		storage.addConcept(result);
		System.out.println("***** Concept created *****");
		return result;
	}
	
	public User createMemberUser(String name, String password){
		User result = new User(name , password);
		result.setUserTypeToMemeber();
		boolean isSaved = storage.addUser(result);
		if(isSaved){
			System.out.println("Member User '" + result.getUserName() + "' created and stored on server ...");
			return result;
		}else{
			System.out.println("!!! Member User '" + result.getUserName() + "' already exists on server !!!");
			System.out.println("Try different username");
			return null;
		}
	}
	
	public User createEmployeeUser(String name, String password){
		User result = new User(name , password);
		result.setUserTypeToEmployee();
		if(storage.addUser(result)){
			System.out.println("Employee User '" + result.getUserName() + "' created and stored on server ...");
			return result;
		}else{
			System.out.println("!!! Employee User '" + result.getUserName() + "' already exists on server !!!");
			System.out.println("Try different username");
			return null;
		}
	}
	
	public void saveConcept(Concept concept){
		storage.updateConcept(concept);
		System.out.print("***** Concept Saved on Server *****");
	}
	
	public void upVoteConcept(Concept concept){
		concept.updateUpvoteStatus(true);
		storage.updateConcept(concept);
		System.out.println("Concept Up-Voted and saved on server ... ");
	}
	
	public void downVoteConcept(Concept concept){
		concept.updateUpvoteStatus(false);
		storage.updateConcept(concept);
		System.out.println("Concept Down-Voted and saved on server ... ");
	}
	
	public void provideConceptFeedback(Concept concept, String feedback){
		//TODO 
		System.out.println("Service in development ... Sorry =P");
	}
	
	public String viewConceptStatus(Concept concept){
		//TODO
		System.out.println("Service in development ... Sorry =P");
		return null;
	}
	
	public boolean startCollaberation(User A, User B, String type){
		//TODO user to user interaction in the form of text, video, or voice chat
		System.out.println("Service in development ... Sorry =P");
		return false;
	}
	
}