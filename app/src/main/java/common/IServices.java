package common;

public interface IServices {

	User validateUser(String userName, String password);

	Concept getConceptByTitle(String title);

	Concept createConcept(User user, String title, String description, String type, String collaborator);

	User createMemberUser(String name, String password);

	User createEmployeeUser(String name, String password);

	void makeComment(Concept concept, String comment);

	void saveConcept(Concept concept);

	void giveConceptStars(Concept concept, int numberOfStars);

	void upVoteConcept(Concept concept);

}