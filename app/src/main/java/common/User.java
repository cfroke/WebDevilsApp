/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

import java.io.Serializable;

/**
 * @author Kevin Bryant
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
	
	public String getUserName(){
		return name;
	}
	
	public boolean tryPassword(String passwordAttempt){
        return password.equals(passwordAttempt);
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public void setUserTypeToMemeber(){
		this.setUserType(MEMBER);
	}
	
	public void setUserTypeToEmployee(){
		this.setUserType(EMPLOYEE);
	}
	
	public void setUserTypeToDeveloper(){
		this.setUserType(DEVELOPER);
	}

	public String getUserType() {
		return userType;
	}

	private void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isMember(){
        boolean result = false;
        if(userType.equals(MEMBER)){
            result = true;
        }
        return result;
    }

    public boolean isEmployee(){
        boolean result = false;
        if(userType.equals(EMPLOYEE)){
            result = true;
        }
        return result;
    }

    public boolean isDeveloper(){
        boolean result = false;
        if(userType.equals(DEVELOPER)){
            result = true;
        }
        return result;
    }

    public boolean isUndefined(){
        boolean result = false;
        if(userType.equals(UNDEFINED)){
            result = true;
        }
        return result;
    }
}
