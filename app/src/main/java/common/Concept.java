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
	private String	collaborator;
	private int		upvoteStatus;
	private User    userThatCreatedThisConcept;
    private String  feedback = "";
	private String	Status;
	private String	SUBMITTED_STATUS = "Submitted";
	private String	APPROVED_STATUS = "Approved";
	private String	REJECTED_STATUS = "Rejected";
	private String	EMPLOYEE_VIEWED_STATUS = "Employee Viewed";
	private String	EMPLOYEE_REVIEWED_STATUS = "Employee Reviewed";
	private boolean sticky;
    private String comments;

	/**
	 *  Instantiate a new concept
	 */
	public Concept(User user, String title, String description, String type, String collaborator, String comments) {
		this.userThatCreatedThisConcept = user;
		this.title = title;
		this.description = description;
		this.type = type;
		this.collaborator = collaborator;
		this.Status = SUBMITTED_STATUS;
		this.sticky = false;
        this.comments = comments;
	}

	public boolean isSubmitted(){
		boolean result = false;
		if(Status.equals(SUBMITTED_STATUS)){
			result = true;
		}
		return result;
	}

	public boolean isApproved(){
		boolean result = false;
		if(Status.equals(APPROVED_STATUS)){
			result = true;
		}
		return result;
	}

	public boolean isRejected(){
		boolean result = false;
		if(Status.equals(REJECTED_STATUS)){
			result = true;
		}
		return result;
	}

	public boolean isEmployeeViewed(){
		boolean result = false;
		if(Status.equals(EMPLOYEE_VIEWED_STATUS)){
			result = true;
		}
		return result;
	}

	public boolean isEmployeeReviewed(){
		boolean result = false;
		if(Status.equals(EMPLOYEE_REVIEWED_STATUS)){
			result = true;
		}
		return result;
	}

	public String getDescription(){
		return description;
	}

    public String getTitle() {
		return title;
	}

	public String getComments() {
        return comments;
    }

    public String getType() {
		return type;
	}

	public String getCollaborator() {
		return collaborator;
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

	public void setStatusToApproved(){
		this.Status = APPROVED_STATUS;
	}

	public void setStatusToRejected(){
		this.Status = REJECTED_STATUS;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

	public boolean isSticky() {
		return sticky;
	}

	public void makeSticky() {
		this.sticky = true;
	}

	public void makeSlippery(){
		this.sticky = false;
	}
}
