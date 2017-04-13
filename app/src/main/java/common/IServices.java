package common;

//import java.rmi.Remote;
//import java.rmi.RemoteException;
import java.util.LinkedList;

public interface IServices { //extends Remote{

	User validateUser(String userName, String password);

	Concept getConceptByTitle(String title);

	Concept createConcept(User user, String title, String description, String type, String collaborator, String comments);// throws RemoteException;

	User createMemberUser(String name, String password);// throws RemoteException;

	User createEmployeeUser(String name, String password);

	void saveConcept(Concept concept);

	void giveConceptStars(Concept concept, int numberOfStars);

	void upVoteConcept(Concept concept);

	void downVoteConcept(Concept concept);

	void provideConceptFeedback(Concept concept, String feedback);

	String viewConceptStatus(Concept concept);

}