package com.project.todolist.model;

/**
 * The Class TodoListPlatformException.
 */
public class TodoListPlatformException extends Exception {
	
	/**
	 * Constructor .
	 *
	 * @param msg 
	 */
	public TodoListPlatformException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor .
	 *
	 * @param msg the msg
	 * @param throwable the throwable
	 */
	public TodoListPlatformException(String msg, Throwable throwable) {
		super(msg,throwable);
	}
}
