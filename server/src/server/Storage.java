/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import common.Concept;
import common.User;

/**
 * @author Kevin Bryant
 *
 */
public class Storage implements Serializable {
	
	private static final long serialVersionUID = -7857868388343559683L;

	private static Storage singleton = new Storage();
	
	private static LinkedList<Concept>	conceptList	= new LinkedList<Concept>();
	private static LinkedList<User>		userList	= new LinkedList<User>();

	private Storage() {
	}
	
	public static Storage getInstance(){
//		TODO data persistence
//		restoreLists();
		return singleton;
	}
	
	public boolean addUser(User USER){
		for(User user : userList){
			if(user.getUserName().equals(USER.getUserName())){
				return false;
			}
		}
		userList.add(USER);
		saveLists();
		return true;
	}
	
	public User getUser(String userName){
		User result = null;
		for(User user : userList){
			if(user.getUserName().equals(userName)){
				result = user;
			}
		}
		return result;
	}
	
	public boolean addConcept(Concept CONCEPT){
		for(Concept concept : conceptList){
			if(concept == CONCEPT){
				return false;
			}
		}
		conceptList.add(CONCEPT);
		saveLists();
		return true;
	}
	
	public boolean updateUser(User USER){
		for(User user : userList){
			if(user.getUserName().equals(USER.getUserName())){
				user = USER;
			}
		}
		return saveLists();
	}
	
	public boolean updateConcept(Concept CONCEPT){
		for(Concept concept : conceptList){
			if(concept == CONCEPT){
				concept = CONCEPT;
			}
		}
		return saveLists();
	}
	
	public LinkedList<Concept> getConceptByUserName(String userName){
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.getUserThatCreatedThisConcept().getUserName().equals(userName)){
				result.add(concept);
			}
		}
		return result;
	}
	
	public static boolean saveLists(){
		
		String fileName= "data/Storage.obj";
	    FileOutputStream fos = null;
	    ObjectOutputStream oos = null;

	    try {
	    	fos = new FileOutputStream(fileName);
	    	oos = new ObjectOutputStream(fos);
	    	oos.writeObject(singleton);
	    	oos.close();
	    	return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Storage restoreLists(){
		
		String fileName= "data/Storage.obj";
		FileInputStream fin;
		ObjectInputStream ois;
		
		try {
			fin = new FileInputStream(fileName);
			ois = new ObjectInputStream(fin);
			singleton = (Storage) ois.readObject();
			ois.close();
			return singleton;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
