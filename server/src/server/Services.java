/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IConcept;
import common.IUser;

/**
 * @author Kevin Bryant
 *
 */
public class Services extends UnicastRemoteObject implements IServices {

	private static final long serialVersionUID = -7875091839442836772L;
	
	protected Services() throws RemoteException {
	}
	
	public IConcept getConcept(int key){
		return null;
	}
	
	public IConcept createConcept(IUser user, String description){
		return null;
	}
	
	public IUser createMemberUser(String Name, String password){
		return null;
	}
	
	public IUser createEmployeeUser(String Name, String password){
		return null;
	}
	
	public boolean saveConcept(IConcept concept){
		return false;
	}
	
	public void upVoteConcept(IConcept concept){
		
	}
	
	public void downVoteConcept(IConcept concept){
		
	}
	
	public void provideFeedback(IConcept concept, String feedback){
		
	}
	
	public String viewConceptStatus(IConcept concept){
		return null;
	}
	
	public boolean startCollaberation(IUser A, IUser B, String type){
		return false;
	}
	
}