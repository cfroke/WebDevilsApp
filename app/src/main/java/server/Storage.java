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
 * This storage class is used to store all data for the app in local storage in the form of two
 * lists of Concept objects and User objects. This whole class is saved as a single object in local
 * storage.
 */
public class Storage implements Serializable {
	
	private static final long		serialVersionUID = -7857868388343559683L;
	private static Storage			singleton = new Storage();
	private static LinkedList<Concept>	conceptList = new LinkedList<Concept>();
	private static LinkedList<User>		userList = new LinkedList<User>();

	/**
	 * Constructor -- Must be private
	 */
	private Storage() {
	}

	/**
	 * singleton pattern used to insure that data only exits in one place during runtime.
	 * (constructor must be private)
	 */
	public static Storage getInstance(){
		//restores data from local storage
		restoreLists();
		return singleton;
	}

	/**
	 * Saves a new User on the server. This method should only used on the Services class.
	 * Returns true if the user was added to storage
 	 * @param USER {@link common.User}
	 * @return boolean
	 */
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

	/**
	 * Returns a specific User from storage based on user name. This method should only be used
	 * by the Services class
	 * @param userName String
	 * @return {@link common.User}
	 */
	public User getUser(String userName){
		User result = null;
		for(User user : userList){
			if(user.getUserName().equals(userName)){
				result = user;
			}
		}
		return result;
	}

	/**
	 * Saves a new Concept on the server. This method should only used on the Services class.
	 * Returns true if the concept was added to storage
	 * @param CONCEPT {@link common.Concept}
	 * @return boolean
	 */
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

	/**
	 * Updates a concept in storage. This method should only be used by the Services class
	 * Returns true if the concept was updated in storage
	 * @param CONCEPT {@link common.Concept}
	 * @return boolean
	 */
	public boolean saveConcept(Concept CONCEPT){
		for(Concept concept : conceptList){
			if( concept.getDescription().equals(CONCEPT.getDescription()) ){
				concept = CONCEPT;
			}
		}
		return saveLists();
	}

	/**
	 * Returns a list of concepts associated with a given the user to the Services class.
	 * @param userName String
	 * @return LinkedList<Concept>
	 */
	public LinkedList<Concept> getConceptsByUserName(String userName){
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.getUserThatCreatedThisConcept().getUserName().equals(userName)){
				result.add(concept);
			}
		}
		return result;
	}

	/**
	 * Returns a given concept based on the title and sends it to the services class.
	 * @param title String
	 * @return {@link common.Concept}
	 */
	public Concept getConceptByTitle(String title){
		for(Concept concept : conceptList){
			if(concept.getTitle().toUpperCase().equals(title)){
				return concept;
			}
        	}
        	return null;
	}

	/**
	 * Sends a list of Un-reviewed Concepts to the Services class
	 * @return LinkedList<Concept>
	 */
	public LinkedList<Concept> getUnreviewedConcepts() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.isSubmitted() || concept.isEmployeeViewed()){
				result.add(concept);
			}
		}
		return result;
	}

	/**
	 * Sends a list of approved Concepts to the Services class
	 * @return LinkedList<Concept>
	 */
	public LinkedList<Concept> getApprovedConcepts() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.isApproved()){
				result.add(concept);
			}
		}
		return result;
	}

	/**
	 * Sends a list of rejected Concepts to the Services class
	 * @return LinkedList<Concept>
	 */
	public LinkedList<Concept> getRejectedConcepts() {
		LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			if(concept.isRejected()){
				result.add(concept);
			}
		}
		return result;
	}

	/**
	 * Sends a list of all Concepts to the Services class
	 * @return LinkedList<Concept>
	 */
	public LinkedList<Concept> getAllConcepts() {
	LinkedList<Concept> result = new LinkedList<Concept>();
		for(Concept concept : conceptList){
			result.add(concept);
		}
		return result;
	}

	/**
	 * Sends an instance of the saved user list to the Services class
	 * @return LinkedList<Concept>
	 */
	public LinkedList<User> getAllUsers(){
		return userList;
	}

	/**
	 * This method saves all data for the app as a "Storage.obj" file in the local storage of the
	 * device that this app is currently on. This method should be re-written if remote storage is
	 * desired. Returns true if save was successful.
	 * @return boolean
	 */
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

	/**
	 * This method restores all data that exists on the device that is using this app. This method
	 * should be re-written if remote storage is desired. Returns true if restore was successful.
	 * @return boolean
	 */
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
}
