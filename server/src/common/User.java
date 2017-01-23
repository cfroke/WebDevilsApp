/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package common;

/**
 * @author Kevin Bryant
 *
 */
public class User implements IUser {
	
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
	User(String name, String password){
		this.name = name;
		this.password = password;
		this.userType = UNDEFINED;
	}

	public void setPassword(String password){
		this.password = password;
		//TODO update password in storage;
	}
	
	public boolean validateUserName(String name){
		//TODO see if user name exist on the server
		return false;
	}
	
	public void setUserTypeToMemeber(){
		this.userType = MEMBER;
	}
	
	public void setUserTypeToEmployee(){
		this.userType = EMPLOYEE;
	}
	
	public void setUserTypeToDeveloper(){
		this.userType = DEVELOPER;
	}
	
}
