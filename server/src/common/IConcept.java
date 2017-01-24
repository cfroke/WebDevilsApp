package common;

import java.rmi.Remote;

public interface IConcept extends Remote{
	
	String getDescription(String description);
	void updateUpvoteStatus(boolean trueForUpFalseForDown);
	void setStatusToSubmitted();
	void setStatusToEmployeeViewed();
	void setStatusToEmployeeReviewed();
	String getStatus();

}