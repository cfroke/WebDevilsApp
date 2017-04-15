/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

import java.io.Serializable;

/**
 * The Concept class is meant to hold information about a given concept. A concept should have one
 * user object associated with it, and is interacted with by both Member users and Employee users.
 */
public class Concept implements Serializable {

	private static final long 	serialVersionUID = -8873025068773379469L;
	private final String		description;
	private final String		title;
	private final String		type;
	private final String		collaborator;
	private int startCount;
	private final User			userThatCreatedThisConcept;
    private String  			feedback = "";
	private String				Status;
	private final String		SUBMITTED_STATUS = "Submitted";
	private final String		APPROVED_STATUS = "Approved";
	private final String		REJECTED_STATUS = "Rejected";
	private final String		EMPLOYEE_VIEWED_STATUS = "Employee Viewed";
	private final String		EMPLOYEE_REVIEWED_STATUS = "Employee Reviewed";
	private boolean				sticky;
    private final String 		comments;

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

	/**
	 * Checks to see if a concept has been submitted
	 * @return boolean
	 */
	public boolean isSubmitted(){
		boolean result = false;
		if(Status.equals(SUBMITTED_STATUS)){
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if a concept has been Approved
	 * @return boolean
	 */
	public boolean isApproved(){
		boolean result = false;
		if(Status.equals(APPROVED_STATUS)){
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if a concept has been Rejected
	 * @return boolean
	 */
	public boolean isRejected(){
		boolean result = false;
		if(Status.equals(REJECTED_STATUS)){
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if a concept has been viewed by an employee
	 * @return boolean
	 */
	public boolean isEmployeeViewed(){
		boolean result = false;
		if(Status.equals(EMPLOYEE_VIEWED_STATUS)){
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if a concept has been reviewed by an employee
	 * @return boolean
	 */
	public boolean isEmployeeReviewed(){
		boolean result = false;
		if(Status.equals(EMPLOYEE_REVIEWED_STATUS)){
			result = true;
		}
		return result;
	}

	/**
	 * Gets the description of the concept as stated by the user
	 * @return String
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Gets the title of a concept as stated by the user
	 * @return String
	 */
    public String getTitle() {
		return title;
	}

	/**
	 * Gets comments made by other users about this concept
	 * @return String
	 */
	public String getComments() {
        return comments;
    }

	/**
	 * Returns the concept type. A concept can be a Product, Service, or Improvement type.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the collaborator name that is associated with this concept
	 * @return String
	 */
	public String getCollaborator() {
		return collaborator;
	}

	/**
	 * This has been re-purposed for star rating system. Moves rating up or down.
	 * @param trueForUpFalseForDown boolean
	 */
	public void plusOneStarCount(boolean trueForUpFalseForDown){
		if(trueForUpFalseForDown){
			setStarCount(getStarCount() + 1);
		}else{
			setStarCount(getStarCount() - 1);
		}
	}

	/**
	 * Returns the User object that created this concept object
	 * @return common.User
	 */
	public User getUserThatCreatedThisConcept(){
		return userThatCreatedThisConcept;
	}

	/**
	 * Sets the status of the concept to SUBMITTED_STATUS
	 */
	public void setStatusToSubmitted(){
		this.Status = SUBMITTED_STATUS;
	}

	/**
	 * Sets the status of this concept to APPROVED_STATUS
	 */
	public void setStatusToApproved(){
		this.Status = APPROVED_STATUS;
	}

	/**
	 * Sets the status of this concept to REJECTED_STATUS
	 */
	public void setStatusToRejected(){
		this.Status = REJECTED_STATUS;
	}

	/**
	 * Sets the status of this concept to EMPLOYEE_VIEWED_STATUS
	 */
	public void setStatusToEmployeeViewed(){
		this.Status = EMPLOYEE_VIEWED_STATUS;
	}

	/**
	 * Sets the status of this concept to EMPLOYEE_REVIEWED_STATUS
	 */
	public void setStatusToEmployeeReviewed(){
		this.Status = EMPLOYEE_REVIEWED_STATUS;
	}

	/**
	 * Returns the current status of this concept
	 * @return String
	 */
	public String getStatus(){
		return Status;
	}

	/**
	 * Returns the number of stars that this concept has
	 * @return int
	 */
	public int getStarCount() {
		return startCount;
	}

	/**
	 * Manually sets the number of stars that this concept has
	 * @param startCount int
	 */
	private void setStarCount(int startCount) {
		this.startCount = startCount;
	}

	/**
	 * Gets the feedback as entered by an employee user after it has been reviewed
	 * @return String
	 */
    public String getFeedback() {
        return feedback;
    }

	/**
	 * Sets feedback as entered by an employee user
	 * @param feedback String
	 */
	public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

	/**
	 * Checks to see if a concept is Sticky or not
	 * @return boolean
	 */
	public boolean isSticky() {
		return sticky;
	}

	/**
	 * Makes a concept sticky
	 */
	public void makeSticky() {
		this.sticky = true;
	}

	/**
	 * Un-stickies a concept
	 */
	public void makeSlippery(){
		this.sticky = false;
	}
}
