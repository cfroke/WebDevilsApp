/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

import java.io.Serializable;

/**
 * @author Kevin Bryant
 *
 */
public class Concept implements Serializable {

	private static final long serialVersionUID = -8873025068773379469L;
	private String	description;
	private int		upvoteStatus;
	private User userThatCreatedThisConcept;
	private String	Status;
	private String	SUBMITTED_STATUS = "Submitted";
	private String	EMPLOYEE_VIEWED_STATUS = "Employee Viewed";
	private String	EMPLOYEE_REVIEWED_STATUS = "Employee Reviewed";

	/**
	 *  Instantiate a new concept
	 */
	public Concept(User user, String description) {
		this.userThatCreatedThisConcept = user;
		this.description = description;
		this.Status = SUBMITTED_STATUS;
	}

	public String getDescription(){
		return description;
	}

	public void updateUpvoteStatus(boolean trueForUpFalseForDown){
		if(trueForUpFalseForDown){
			setUpvoteStatus(getUpvoteStatus() + 1);
		}else{
			setUpvoteStatus(getUpvoteStatus() - 1);
		}
	}

	public User getUserThatCreatedThisConcept(){
		return userThatCreatedThisConcept;
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

	public int getUpvoteStatus() {
		return upvoteStatus;
	}

	private void setUpvoteStatus(int upvoteStatus) {
		this.upvoteStatus = upvoteStatus;
	}
}
