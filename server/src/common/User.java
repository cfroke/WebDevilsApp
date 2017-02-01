/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

import java.io.Serializable;

import server.Storage;

/**
 * @author Kevin Bryant
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1866235317697481634L;
	private String name;
	private String password;
	private String MEMBER = "member";
	private String EMPLOYEE ="employee";
	private String DEVELOPER = "developer";
	private String UNDEFINED = "undefined";
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
		if(password.equals(passwordAttempt)){
			return true;
		}else{
			return false;
		}
	}

	public void setPassword(String password){
		this.password = password;
		//TODO update password in storage;
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

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
