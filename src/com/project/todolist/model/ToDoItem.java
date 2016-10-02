package com.project.todolist.model;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class ToDoItem.
 */
@XmlRootElement
public class ToDoItem  implements Serializable

{	
	 private static final long serialVersionUID = 1L;
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/** The user id. */
	private String userId;
	
	/** The date. */
	private Date date;

	/**
	 * Default constructor .
	 */
	public ToDoItem() 
	{
		super();
	}

	/**
	 * constructor to create a new todoitem.
	 *
	 * @param id the id
	 * @param title the title
	 * @param description the description
	 * @param userId the user id
	 */
	public ToDoItem(int id, String title, String description , String userId) 
	{
		setId(id);
		setTitle(title);
		setDescription(description);
		setUserId(userId);
		date = new Date();

	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		if(id>0)
		{
			this.id = id;
		}
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
	
		this.userId = userId;
	}
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "ToDoItem [title=" + title + ", description=" + description + ", id=" + id + ", userId=" + userId
				+ ", date=" + date + "]";
	}




}	