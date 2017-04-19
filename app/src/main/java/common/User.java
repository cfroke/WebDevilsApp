/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

import java.io.Serializable;

/**
 * The User class is used to store information about a particular User in the system. For the most
 * part a user object will have a MEMBER state or a EMPLOYEE state.
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1866235317697481634L;
	private final String name;
	private String password;
	private final String MEMBER = "member";
	private final String EMPLOYEE ="employee";
	private final String DEVELOPER = "developer";
	private final String UNDEFINED = "undefined";
	private String userType;
	
	/**
	 *  Instantiate a new user
	 */
	public User(String name, String password){
		this.name = name;
		this.password = password;
		this.setUserType(UNDEFINED);
	}

	/**
	 * Returns the user name for this user object.
	 * @return String
	 */
	public String getUserName(){
		return name;
	}

	/**
	 * password check for the given user object. Returns check if it is the correct password
	 * @param passwordAttempt String
	 * @return boolean
	 */
	public boolean tryPassword(String passwordAttempt){
        return password.equals(passwordAttempt);
	}

	/**
	 * Resets the password for a given User object.
	 * @param password String
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * Changes the state of this user object to MEMBER
	 */
	public void setUserTypeToMemeber(){
		this.setUserType(MEMBER);
	}

	/**
	 * Changes the state of this user object to EMPLOYEE
	 */
	public void setUserTypeToEmployee(){
		this.setUserType(EMPLOYEE);
	}

	/**
	 * Changes the state of this user object to DEVELOPER
	 */
	public void setUserTypeToDeveloper(){
		this.setUserType(DEVELOPER);
	}

	/**
	 * Returns the current state of this user object. ie. MEMBER or EMPLOYEE
	 * @return String
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Private method for changing the state of the user object
	 * @param userType String
	 */
	private void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * Checks to see if the current state of this user is set to MEMBER
	 * @return boolean
	 */
	public boolean isMember(){
		boolean result = false;
        	if(userType.equals(MEMBER)){
            		result = true;
        	}
        	return result;
	}

	/**
	 * Checks to see if the current state of this user is set to EMPLOYEE
	 * @return boolean
	 */
	public boolean isEmployee(){
		boolean result = false;
		if(userType.equals(EMPLOYEE)){
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if the current state of this user is set to DEVELOPER
	 * @return boolean
	 */
	public boolean isDeveloper(){
		boolean result = false;
		if(userType.equals(DEVELOPER)){
			result = true;
		}
		return result;
    }

	/**
	 * Checks to see if the current state of this user is set to UNDEFINED
	 * @return boolean
	 */
    public boolean isUndefined(){
        boolean result = false;
        if(userType.equals(UNDEFINED)){
            result = true;
        }
        return result;
    }
}
