package common;

public interface IUser {

	void setPassword(String password);

	boolean validateUserName(String name);

	void setUserTypeToMemeber();

	void setUserTypeToEmployee();

	void setUserTypeToDeveloper();

}