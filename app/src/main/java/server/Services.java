/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import android.content.Context;

import java.io.Serializable;
import java.util.LinkedList;

import common.Concept;
import common.IServices;
import common.User;


/**
 * @author Kevin Bryant
 *
 */
public class Services implements IServices, Serializable {

	private static final long serialVersionUID = -7875091839442836772L;
	
	static Storage storage;
	
	public Services(){
		storage = Storage.getInstance();
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
	
	public static LinkedList<Concept> getConceptsByUser(User user){
		System.out.println("Sent concept list to client");
		return storage.getConceptsByUserName(user.getUserName());
	}

    public Concept getConceptByTitle(String title){
        System.out.println("Sent concept to client");
        return storage.getConceptByTitle(title);
    }

    public static LinkedList<Concept> getUnreviewedConcepts(){
        System.out.println("Sent unreviewed concept list to client");
        return Storage.getUnreviewedConcepts();
    }

    public static LinkedList<Concept> getApprovedConcepts(){
		System.out.println("Sent approved concept list to client");
		return Storage.getApprovedConcepts();
	}

	public static LinkedList<Concept> getRejectedConcepts(){
		System.out.println("Sent rejected concept list to client");
		return Storage.getRejectedConcepts();
	}

	public static LinkedList<Concept> getAllConcepts(){
		System.out.println("Sent approved concept list to client");
		return Storage.getAllConcepts();
	}

	public static LinkedList<Concept> getComments(){
		System.out.println("Sent comments to the client");
		return Storage.getComments();
	}
	
	public Concept createConcept(User user, String title, String description, String type, String collaborator, String comments){
		Concept result = new Concept(user, title, description, type, collaborator, comments);
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

	/*public Concept makeComment(Concept concept) {
        Concept listed = new Concept(comments);
        storage.getAllComments(listed);
        System.out.println("////////Comments list created////////////");
        return listed;
    }*/

	/*public Concept makeComment(Concept concept){
        storage.saveConcept(concept);
        System.out.println("///////// Comments have been loaded //////////");
        return null;
    }*/

	public void makeConceptSticky(Concept concept){
		concept.makeSticky();
		storage.saveConcept(concept);
		System.out.print("***** Concept stickied on server *****");
	}

	public void makeConceptSlippery(Concept concept){
		concept.makeSlippery();
		storage.saveConcept(concept);
		System.out.print("***** Concept no longer stickied on server *****");
	}

	public void saveConcept(Concept concept){
		storage.saveConcept(concept);
		System.out.print("***** Concept Saved on Server *****");
	}

	public void giveConceptStars(Concept concept, int numberOfStars){
		for(int i = 0 ; i < numberOfStars ; i++){
			upVoteConcept(concept);
		}
		storage.saveConcept(concept);
		System.out.println( numberOfStars + " added to concept and saved on server ... ");
	}
	
	public void upVoteConcept(Concept concept){
		concept.updateUpvoteStatus(true);
		storage.saveConcept(concept);
		System.out.println("Concept Up-Voted and saved on server ... ");
	}

	public void downVoteConcept(Concept concept){
		concept.updateUpvoteStatus(false);
		storage.saveConcept(concept);
		System.out.println("Concept Down-Voted and saved on server ... ");
	}

	public void provideConceptFeedback(Concept concept, String feedback){
		concept.setFeedback(feedback);
        storage.saveConcept(concept);
		System.out.println("Feedback saved for given concept");
	}
	
	public String viewConceptStatus(Concept concept){
		//TODO
		System.out.println("Service in development ... Sorry =P");
		return null;
	}

	// used to calculate the score for a user, by adding all votes on their concepts
	public static int getUserScore(User user){
		int score = 0;
		LinkedList<Concept> concepts = getConceptsByUser(user);
		for (Concept concept : concepts) {
			score = score + concept.getUpvoteStatus();
		}
		// gets scores for collaborated concepts and adds them to overall score
		LinkedList<Concept> allConcepts = getApprovedConcepts();
		for (Concept collab : allConcepts) {
			if (collab.getCollaborator().equals(user.getUserName())){
				score = score + collab.getUpvoteStatus();
			}
		}

		return score;
	}

	public static LinkedList<User> getAllUsers() {
		LinkedList<User> users = storage.getAllUsers();
		return users;
	}

    public Concept makeComment(String s) {
        return null;
    }
}