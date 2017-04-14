/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import common.Concept;
import common.User;
import webdevils.webdevilsapp.AppContext;

/**
 * @author Kevin Bryant
 *
 */
public class Storage implements Serializable {
	
	private static final long serialVersionUID = -7857868388343559683L;

	private static Storage singleton = new Storage();
	
	private static final LinkedList<Concept>	conceptList	= new LinkedList<Concept>();
	private static final LinkedList<User>		userList	= new LinkedList<User>();
	private static final LinkedList<Concept>  userComments = new LinkedList<Concept>();

	private Storage() {
	}
	
	public static Storage getInstance(){
        //data persistence
		restoreLists();
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
			if(concept.equals(CONCEPT)){
				return false;
			}
		}
		conceptList.add(CONCEPT);
		saveLists();
		return true;
	}
	
	public boolean saveConcept(Concept CONCEPT){
		for(Concept concept : conceptList){
			if( concept.getDescription().equals(CONCEPT.getDescription()) ){
				concept = CONCEPT;
			}
		}
		return saveLists();
	}
	
	public LinkedList<Concept> getConceptsByUserName(String userName){
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.getUserThatCreatedThisConcept().getUserName().equals(userName)){
				result.add(concept);
			}
		}
		return result;
	}

    public Concept getConceptByTitle(String title){
        for(Concept concept : conceptList){
            if(concept.getTitle().toUpperCase().equals(title)){
                return concept;
            }
        }
        return null;
    }

    public static LinkedList<Concept> getUnreviewedConcepts() {
        LinkedList<Concept> result = new LinkedList<Concept>();
        for(Concept concept : conceptList){
            if(concept.isSubmitted() || concept.isEmployeeViewed()){
                result.add(concept);
            }
        }
        return result;
    }

	public static LinkedList<Concept> getApprovedConcepts() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.isApproved()){
				result.add(concept);
			}
		}
		return result;
	}

	public static LinkedList<Concept> getRejectedConcepts() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.isRejected()){
				result.add(concept);
			}
		}
		return result;
	}

    public static LinkedList<Concept> getAllConcepts() {
        LinkedList<Concept> result = new LinkedList<Concept>();
        for(Concept concept : conceptList){
            result.add(concept);
        }
        return result;
    }

	public static LinkedList<Concept> getComments() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : userComments){
			result.add(concept);
		}
		return result;
	}
	
	private static boolean saveLists(){

		String fileName= "Storage.obj";
	    FileOutputStream fos = null;
	    ObjectOutputStream oos = null;
		File filePath = new File(AppContext.getAppContext().getFilesDir() , fileName);

	    try {
	    	fos = new FileOutputStream(filePath.getAbsoluteFile() + fileName);
	    	oos = new ObjectOutputStream(fos);
	    	oos.writeObject(singleton);
	    	oos.close();
	    	return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Storage restoreLists(){

		String fileName= "Storage.obj";
		FileInputStream fin;
		ObjectInputStream ois;
		File filePath = new File(AppContext.getAppContext().getFilesDir() , fileName);
		
		try {
			fin = new FileInputStream(filePath.getAbsolutePath() + fileName);
			ois = new ObjectInputStream(fin);
			singleton = (Storage) ois.readObject();
			ois.close();
			return singleton;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public LinkedList<User> getAllUsers(){
		return userList;
	}

}
