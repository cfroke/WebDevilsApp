/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

/**
 * This interface would be used by the client if the server was not embedded in the app. Through out
 * the app the Services class is used. If the server where to be converted to be used remotely,
 * then IServices should be used instead of using the Services class directly, and the server
 * package should be removed from the app.
 */
public interface IServices {

	User validateUser(String userName, String password);

	Concept getConceptByTitle(String title);

	Concept createConcept(User user, String title, String description, String type, String collaborator);

	User createMemberUser(String name, String password);

	User createEmployeeUser(String name, String password);

	void saveConcept(Concept concept);

	void giveConceptStars(Concept concept, int numberOfStars);

	void upVoteConcept(Concept concept);

}