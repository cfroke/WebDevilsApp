package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import common.Concept;
import common.User;

public interface IServices extends Remote{

	User validateUser(String userName, String password) throws RemoteException;

	LinkedList<Concept> getConceptsByUser(User user) throws RemoteException;

	Concept createConcept(User user, String description) throws RemoteException;

	User createMemberUser(String name, String password) throws RemoteException;

	User createEmployeeUser(String name, String password) throws RemoteException;

	void saveConcept(Concept concept) throws RemoteException;

	void upVoteConcept(Concept concept) throws RemoteException;

	void downVoteConcept(Concept concept) throws RemoteException;

	void provideConceptFeedback(Concept concept, String feedback) throws RemoteException;

	String viewConceptStatus(Concept concept) throws RemoteException;

	boolean startCollaberation(User A, User B, String type) throws RemoteException;

}