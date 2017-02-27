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
	private String  title;
	private String  type;
	private int		upvoteStatus;
	private User    userThatCreatedThisConcept;
	private String	Status;
	private String	SUBMITTED_STATUS = "Submitted";
	private String	EMPLOYEE_VIEWED_STATUS = "Employee Viewed";
	private String	EMPLOYEE_REVIEWED_STATUS = "Employee Reviewed";

	/**
	 *  Instantiate a new concept
	 */
	public Concept(User user, String title, String description, String type) {
		this.userThatCreatedThisConcept = user;
		this.title = title;
		this.description = description;
		this.type = type;
		this.Status = SUBMITTED_STATUS;
	}

	public String getDescription(){
		return description;
	}

    public String getTitle() {
		return title;
	}


    public String getType() {
		return type;
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
