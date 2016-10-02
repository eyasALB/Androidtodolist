package com.project.todolist.model;

/**
 * the Interface IToDoListUser
 */
public interface IToDoListUser {
	/**
	 * addUser
	 * add new user to the database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public void addUser(User userToAdd) throws TodoListPlatformException ;

	/**
	 * deleteUser
	 * delete user from the  database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public boolean deleteUser(User userToDelete) throws TodoListPlatformException ;

	/**
	 * updateUser
	 * update existing  user in the database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public void updateUser(User userToUpdate) throws TodoListPlatformException;

	public User getUserByEmail(String email) throws TodoListPlatformException ;

	public boolean authenticateUser(String userEmai, String password) throws TodoListPlatformException;



}
