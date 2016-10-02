package com.project.todolist.model;

import java.io.Serializable;

/**
 * The Class User.
 */
public class User implements Serializable
{
	public final static long serialVersionUID = 101L;
	/** The user name. */
	private String userName;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/**
	 * default constructor .
	 */
	public User(){ }

	/**
	 * Constructor a new user.
	 *
	 * @param id the id
	 * @param name the name
	 * @param email the email
	 * @param password the password
	 */
	public User(String name, String email, String password) {
		super();
		setUserName(name);
		setEmail(email);
		setPassword(password);
	}
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param name the new user name
	 */
	public void setUserName(String name) {
		this.userName = name;

	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}
}
