package com.project.todolist.model;

import java.util.List;

/**
 * The interface IToDoListDAO.
 */
public interface IToDoListDAO {
	/**
	 * addItem
	 * add item to the items database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public void addItem(ToDoItem item) throws TodoListPlatformException ;
	

	/**
	 * updateItem
	 * update existing item in the database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public void updateItem(ToDoItem item) throws TodoListPlatformException ;
	
	
	/**
	 * deleteItem
	 * delete item from the  database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 */
	public boolean deleteItem(ToDoItem item) throws TodoListPlatformException ;
	
	/**
	 * getItems
	 * get all item from the  database
	 *  @exception TodoListPlatformException
	 * @throws this method may throw a TodoListPlatformException if it was a problem with the access to the database
	 * @return list of all items 
	 */
	public List<ToDoItem> getItems() throws TodoListPlatformException ;
	
	public List<ToDoItem> getUserItems(String userId) throws TodoListPlatformException ;
	public ToDoItem getItemById(int itemId)throws TodoListPlatformException ;

}
