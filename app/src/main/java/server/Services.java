/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

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
	
	private static Storage storage;
	
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
        return storage.getUnreviewedConcepts();
    }

    public static LinkedList<Concept> getApprovedConcepts(){
		System.out.println("Sent approved concept list to client");
		return storage.getApprovedConcepts();
	}

	public static LinkedList<Concept> getRejectedConcepts(){
		System.out.println("Sent rejected concept list to client");
		return storage.getRejectedConcepts();
	}

	public static LinkedList<Concept> getAllConcepts(){
		System.out.println("Sent approved concept list to client");
		return storage.getAllConcepts();
	}
	
	public Concept createConcept(User user, String title, String description, String type, String collaborator){
		Concept result = new Concept(user, title, description, type, collaborator);
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
			System.out.println("Employee User '" + result.getUserName() +
					"' created and stored on server ...");
			return result;
		}else{
			System.out.println("!!! Employee User '" + result.getUserName() +
					"' already exists on server !!!");
			System.out.println("Try different username");
			return null;
		}
	}

	public void makeComment(Concept concept, String comment){
		concept.addComment(comment);
		storage.saveConcept(concept);
		System.out.println("***** Comment left and saved on the server for " +
				concept.getTitle() + " *****");
	}

	public LinkedList<String> getComments(Concept concept){
		return concept.getComments();
	}

	public void makeConceptSticky(Concept concept){
		concept.makeSticky();
		storage.saveConcept(concept);
		System.out.println("***** Concept stickied on server *****");
	}

	public void makeConceptSlippery(Concept concept){
		concept.makeSlippery();
		storage.saveConcept(concept);
		System.out.println("***** Concept no longer stickied on server *****");
	}

	public void saveConcept(Concept concept){
		storage.saveConcept(concept);
		System.out.println("***** Concept Saved on Server *****");
	}

	public void giveConceptStars(Concept concept, int numberOfStars){
		for(int i = 0 ; i < numberOfStars ; i++){
			upVoteConcept(concept);
		}
		storage.saveConcept(concept);
		System.out.println( numberOfStars + " added to concept and saved on server ... ");
	}
	
	public void upVoteConcept(Concept concept){
		concept.plusOneStarCount(true);
		storage.saveConcept(concept);
		System.out.println("Concept Up-Voted and saved on server ... ");
	}

	public void downVoteConcept(Concept concept){
		concept.plusOneStarCount(false);
		storage.saveConcept(concept);
		System.out.println("Concept Down-Voted and saved on server ... ");
	}

	public void provideConceptFeedback(Concept concept, String feedback){
		concept.setFeedback(feedback);
        storage.saveConcept(concept);
		System.out.println("Feedback saved for given concept");
	}

	// used to calculate the score for a user, by adding all votes on their concepts
	public static int getUserScore(User user){
		int score = 0;
		LinkedList<Concept> concepts = getConceptsByUser(user);
		for (Concept concept : concepts) {
			score = score + concept.getStarCount();
		}
		// gets scores for collaborated concepts and adds them to overall score
		LinkedList<Concept> allConcepts = getApprovedConcepts();
		for (Concept collab : allConcepts) {
			if (collab.getCollaborator().equals(user.getUserName())){
				score = score + collab.getStarCount();
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