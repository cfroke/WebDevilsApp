/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import java.io.File;
import java.util.LinkedList;

import common.Concept;
import common.User;

/**
 * @author Kevin Bryant
 *
 */
public class Storage {
	
	LinkedList<Concept>	conceptList	= new LinkedList<Concept>();
	LinkedList<User> 	userList	= new LinkedList<User>();

	public Storage() {
	}

	public boolean saveLists(File path){
		return false;
		// saves Storage class as an object on the subsystem
		// return true if succesful
	}
	
	public boolean restoreLists(File path){
		return false;
		// restores Storage class as an object from the sub system
		// return true if successful
	}

}
