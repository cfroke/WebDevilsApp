/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

/**
 * @author Kevin Bryant
 *
 */
public class Concept implements IConcept {

	private String 	description;
	private int		upvoteStatus;
	private IUser 	userThatCreatedThisConcept;
	private String 	Status;
	private String	SUBMITTED_STATUS = "Submitted";
	private String	EMPLOYEE_VIEWED_STATUS = "Employee Viewed";
	private String	EMPLOYEE_REVIEWED_STATUS = "Employee Reviewed";
	
	/**
	 *  Instantiate a new concept
	 */
	public Concept(IUser user, String description) {
		setUser(user);
		this.description = description;
	}
	
	public String getDescription(String description){
		return description;
	}
	
	public void updateUpvoteStatus(boolean trueForUpFalseForDown){
		if(trueForUpFalseForDown){
			upvoteStatus++;
		}else{
			upvoteStatus--;
		}
	}
	
	private void setUser(IUser user){
		this.userThatCreatedThisConcept = user;
		this.Status = SUBMITTED_STATUS;
	}
	
	public void setStatusToSubmitted(){
		this.Status = SUBMITTED_STATUS;
	}
	
	public void setStatusToEmployeeViewed(){
		this.Status = EMPLOYEE_VIEWED_STATUS;
	}
	
	public void setStatusToEmployeeReviewed(){
		this.Status = EMPLOYEE_REVIEWED_STATUS;
	}
	
	public String getStatus(){
		return Status;
	}
}
