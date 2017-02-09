package common;

import java.util.LinkedList;

public interface IServices {

	User validateUser(String userName, String password);

	LinkedList<Concept> getConceptsByUser(User user);

	Concept createConcept(User user, String description);

	User createMemberUser(String name, String password);

	User createEmployeeUser(String name, String password);

	void saveConcept(Concept concept);

	void upVoteConcept(Concept concept);

	void downVoteConcept(Concept concept);

	void provideConceptFeedback(Concept concept, String feedback);

	String viewConceptStatus(Concept concept);

	boolean startCollaberation(User A, User B, String type);

}