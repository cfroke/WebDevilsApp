package webdevils.webdevilsapp.common;

//import java.rmi.Remote;
//import java.rmi.RemoteException;
import java.util.LinkedList;

public interface IServices { //extends Remote{

	User validateUser(String userName, String password);

	LinkedList<Concept> getConceptsByUser(User user);

	Concept createConcept(User user, String description);// throws RemoteException;

	User createMemberUser(String name, String password);// throws RemoteException;

	User createEmployeeUser(String name, String password);

	void saveConcept(Concept concept);

	void upVoteConcept(Concept concept);

	void downVoteConcept(Concept concept);

	void provideConceptFeedback(Concept concept, String feedback);

	String viewConceptStatus(Concept concept);

	boolean startCollaberation(User A, User B, String type);

}