package client;

import common.IConcept;
import common.IUser;

public interface IServices {

	IConcept getConcept(int key);

	IConcept createConcept(IUser user, String description);

	IUser createMemberUser(String Name, String password);

	IUser createEmployeeUser(String Name, String password);

	boolean saveConcept(IConcept concept);

	void upVoteConcept(IConcept concept);

	void downVoteConcept(IConcept concept);

	void provideFeedback(IConcept concept, String feedback);

	String viewConceptStatus(IConcept concept);

	boolean startCollaberation(IUser A, IUser B, String type);

}