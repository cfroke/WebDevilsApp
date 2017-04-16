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
 * This Services class would exist on the server if the server package wasn't embedded in the app as
 * it is here. Client applications would use the IServices interface to call methods on the server.
 * This is where the server methods are implemented.
 */
public class Services implements IServices, Serializable {

	private static final long serialVersionUID = -7875091839442836772L;
	
	private static Storage storage;

    /**
     * Constructor -- grabs th singleton instance of Storage
     */
	public Services(){
		storage = Storage.getInstance();
	}

    /**
     * When a user is logging into the server. This method is used to do a user name / password
     * check. It returns a User object of the user that is logging in if the user is validated,
     * otherwise it returns null if it is a failed login attempt.
     * @param userName String
     * @param password String
     * @return {@link common.User}
     */
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

    /**
     * Returns a list of concepts associated with a given user.
     * @param user {@link common.User}
     * @return LinkedList<Concept>
     */
	public static LinkedList<Concept> getConceptsByUser(User user){
		System.out.println("Sent concept list to client");
		return storage.getConceptsByUserName(user.getUserName());
	}

    /**
     * Returns a specific concept based on the title attribute of the the Concept
     * @param title String
     * @return {@link common.Concept}
     */
    public Concept getConceptByTitle(String title){
        System.out.println("Sent concept to client");
        return storage.getConceptByTitle(title);
    }

    /**
     * Returns a list of concepts that have not been reviewed by an employee yet.
     * @return LinkedList<Concept>
     */
    public LinkedList<Concept> getUnreviewedConcepts(){
        System.out.println("Sent unreviewed concept list to client");
        return storage.getUnreviewedConcepts();
    }

    /**
     * Returns a list of concepts that have been approved by an employee.
     * @return LinkedList<Concept>
     */
    public static LinkedList<Concept> getApprovedConcepts(){
		System.out.println("Sent approved concept list to client");
		return storage.getApprovedConcepts();
	}

    /**
     * Returns a list of concepts that have not been rejected by an employee yet.
     * @return LinkedList<Concept>
     */
	public static LinkedList<Concept> getRejectedConcepts(){
		System.out.println("Sent rejected concept list to client");
		return storage.getRejectedConcepts();
	}

    /**
     * Returns a list of all concepts that exist on the server.
     * @return LinkedList<Concept>
     */
	public static LinkedList<Concept> getAllConcepts(){
		System.out.println("Sent approved concept list to client");
		return storage.getAllConcepts();
	}

    /**
     * This is the main method that should be used when creating a concept object. It insures that
     * this new concept is saved on the server and can be used else where in the app.
     * @param user {@link common.User}
     * @param title String
     * @param description String
     * @param type String
     * @param collaborator String
     * @return {@link common.Concept}
     */
	public Concept createConcept(User user, String title, String description, String type, String collaborator){
		Concept result = new Concept(user, title, description, type, collaborator);
		storage.addConcept(result);
		System.out.println("***** Concept created *****");
		return result;
	}

    /**
     * Creates a member user and saves it on the server
     * @param name String
     * @param password String
     * @return {@link common.User}
     */
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

    /**
     * Creates a employee user and saves it on the server
     * @param name String
     * @param password String
     * @return {@link common.User}
     */
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

    /**
     * Adds a comment to a list of comments in a given concept object and saves it on the server.
     * @param concept String
     * @param comment String
     */
	public void makeComment(Concept concept, String comment){
		concept.addComment(comment);
		storage.saveConcept(concept);
		System.out.println("***** Comment left and saved on the server for " +
				concept.getTitle() + " *****");
	}

    /**
     * Returns a list of all comment associated with a given concept.
     * @param concept {@link common.Concept}
     * @return LinkedList<String>
     */
	public LinkedList<String> getComments(Concept concept){
		return concept.getComments();
	}

    /**
     * Makes a given concept "sticky" and updates it on the server
     * @param concept {@link common.Concept}
     */
	public void makeConceptSticky(Concept concept){
		concept.makeSticky();
		storage.saveConcept(concept);
		System.out.println("***** Concept stickied on server *****");
	}

    /**
     * Un-stickies a concept and saves it on the server.
     * @param concept {@link common.Concept}
     */
	public void makeConceptSlippery(Concept concept){
		concept.makeSlippery();
		storage.saveConcept(concept);
		System.out.println("***** Concept no longer stickied on server *****");
	}

    /**
     * Allows a client application to update a concept on there server so that it is saved for
     * future use.
     * @param concept {@link common.Concept}
     */
	public void saveConcept(Concept concept){
		storage.saveConcept(concept);
		System.out.println("***** Concept Saved on Server *****");
	}

    /**
     * Adds a specific number stars to a given concept
     * @param concept {@link common.Concept}
     * @param numberOfStars int
     */
	public void giveConceptStars(Concept concept, int numberOfStars){
		for(int i = 0 ; i < numberOfStars ; i++){
			upVoteConcept(concept);
		}
		storage.saveConcept(concept);
		System.out.println( numberOfStars + " added to concept and saved on server ... ");
	}

    /**
     * Re-purposed method, was used for up-voting a concept, but now is used to increment the
     * star rating up by one.
     * @param concept {@link common.Concept}
     */
	public void upVoteConcept(Concept concept){
		concept.plusOneStarCount(true);
		storage.saveConcept(concept);
		System.out.println("Concept Up-Voted and saved on server ... ");
	}

    /**
     * Re-purposed method, was used for down-voting a concept, but now is used to increment the
     * star rating down by one if needed.
     * @param concept {@link common.Concept}
     */
	public void downVoteConcept(Concept concept){
		concept.plusOneStarCount(false);
		storage.saveConcept(concept);
		System.out.println("Concept Down-Voted and saved on server ... ");
	}

    /**
     * When a concept is being reviewed feedback can be left in the form of a String. This method is
     * meant to capture and save the feedback on the server for a given concept.
     * @param concept {@link common.Concept}
     * @param feedback String
     */
	public void provideConceptFeedback(Concept concept, String feedback){
		concept.setFeedback(feedback);
        storage.saveConcept(concept);
		System.out.println("Feedback saved for given concept");
	}

    /**
     * Used to calculate the score for a user, by adding all votes on their concepts
     * @param user {@link common.User}
     * @return int
     */
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

    /**
     * Returns a list of all Users that are saved on the server.
     * @return LinkedList<User>
     */
	public static LinkedList<User> getAllUsers() {
		LinkedList<User> users = storage.getAllUsers();
		return users;
	}
}