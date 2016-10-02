package com.project.todolist.servies;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.ws.rs.core.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.jose4j.lang.JoseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URI;
import java.net.URISyntaxException;

import com.project.todolist.model.HibernateToDoListDAO;
import com.project.todolist.model.ToDoItem;
import com.project.todolist.model.TodoListPlatformException;
import com.project.todolist.model.User;
import com.sun.research.ws.wadl.Request;
import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.ws.client.RequestContext;


@Path("/user")
public class UserServices 
{
	@GET
	@Path("/test")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
		System.out.println("innn");
	    return "Hello Jersey";
	  }
	


	@SuppressWarnings({ "unchecked" })
	@POST
	@Path("/addItem")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addItem(@QueryParam("title") String title,@QueryParam("description") String description, @CookieParam("Authorization") String jsonWebToken )
	{

		System.out.println("JWT :" +jsonWebToken );
	
		ToDoItem item = null;
		
		
		//get an instance of the model to add the user to the DB
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		try
		{	jwt jsonWT = new jwt();
			String email = (String)jsonWT.ConsumeAndCheckJWT(jsonWebToken).getClaimValue("email");
			System.out.println("email = "+email);
			item = new ToDoItem(0, title, description, email);
			model.addItem(item);
			
		}
		catch (TodoListPlatformException | JoseException e) {
			e.printStackTrace();
		}
		//return item;
		
			
		String json ="{\"title\" :"+item.getTitle()+","+"\"description\" :"+ item.getDescription() + "}";
		//JSONParser parser = new JSONParser();
		JSONObject jsItem = new JSONObject();
		jsItem.put("title", item.getTitle());
		jsItem.put("description", item.getDescription());
		jsItem.put("id", item.getId());
		return Response.ok(jsItem.toJSONString()).build();
		
	}
		
	
	@SuppressWarnings({ "finally", "unchecked" })
	@POST
	@Path("/updateitem")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateItem(@QueryParam("title") String title,@QueryParam("description") String description,@QueryParam("itemid") int itemId,@CookieParam("Authorization") String jsonWebToken)
	{
		ToDoItem item = null;
		System.out.println("in update");
		
		//get an instance of the model to add the user to the DB
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		try
		{
			jwt jsonWT = new jwt();
			String email = (String)jsonWT.ConsumeAndCheckJWT(jsonWebToken).getClaimValue("email");
			item = new ToDoItem(itemId, title, description, email);
			model.updateItem(item);
			
		}
		catch (TodoListPlatformException | JoseException e) {

		}
		//return item;
		
		
		JSONObject jsItem = new JSONObject();
		jsItem.put("title", item.getTitle());
		jsItem.put("description", item.getDescription());
		jsItem.put("id", item.getId());
		return Response.ok(jsItem.toJSONString()).build();
		
	}
	
	@GET
	@Path("/demo")
	@Produces(MediaType.APPLICATION_JSON)
	//
	public Response demo(@QueryParam("title") String title,@QueryParam("description") String description,@Context HttpServletResponse servletResponse)
	{
		
		//return item;
		String json ="{\n"
				+ "title : blaa,\n"
					+"description : ggggg\n"
				+ "}";
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	
	@POST
	@Path("/register")
	  public Response registerAndCreateUserAndGetJwt(@QueryParam("name") String name,@QueryParam("password") String password,@QueryParam("email") String email) 
	{
		Response answer = null;
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		JSONObject jwtJson = new JSONObject();
		String base64userToken = null;
		try {
		System.out.println("in register");
			model.addUser(new User(name, email, password));
			System.out.println("after register");
			jwt jsonWebToken = new jwt();
			base64userToken = jsonWebToken.CreateJWT(email.trim());
			System.out.println("register  = "+ jsonWebToken.ConsumeAndCheckJWT(base64userToken));
			NewCookie cookie  = new NewCookie("Authorization", base64userToken);
			answer = Response.seeOther(new URI("/androidToDoListFinal/todolist.html")).cookie(cookie).build();
					
		} 
		catch (TodoListPlatformException | URISyntaxException | JoseException e) {
			//answer = Response.seeOther(new URI("/androidToDoListFinal/error.html")).build();
		}
	    return answer;
	  }
	
	@SuppressWarnings({ "unchecked" })
	@POST
	@Path("/login")
	// @Produces(MediaType.APPLICATION_JSON)
	public Response VerifyDataAndGetJwt(@QueryParam("email") String email,@QueryParam("password") String password)
	{
		Response answer = null;
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		boolean isLegalUser = false;
		String str = "notValid";
	
		JSONObject jwtJson = new JSONObject();
		

		try {
			isLegalUser = model.authenticateUser(email, password);
			if(isLegalUser)
			{
			jwt jsonWebToken = new jwt();
			str = jsonWebToken.CreateJWT(email.trim());
			System.out.println("login un = "+ jsonWebToken.ConsumeAndCheckJWT(str));
			
			jwtJson.put("jwt", str);
			NewCookie cookie  = new NewCookie("Authorization", str);
			
			answer = Response.seeOther(new URI("/androidToDoListFinal/todolist.html")).cookie(cookie).build();
			
			//answer = Response.seeOther(new URI("/androidToDoListFinal/todolist.html")).header(HttpHeaders.AUTHORIZATION, "Bearer " + str).build();
			}			
		} 
		catch (JoseException  | TodoListPlatformException | URISyntaxException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(answer == null)
		{
			jwtJson.put("jwt", str);
			//answer = Response.ok(jwtJson.toJSONString()).header(HttpHeaders.AUTHORIZATION, "Bearer " + str).build();
		
		}
		return answer;
	}

	

	@GET
	@Path("/logout")
	// @Produces(MediaType.APPLICATION_JSON)
	public Response logout()
	{
		
		Response answer = null;
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		JSONObject jwtJson = new JSONObject();
		
		Cookie cookie = new Cookie("Authorization", "");
		NewCookie Newcookie  = new NewCookie(cookie, "", 0, false);
		answer = Response.ok().cookie(Newcookie).build();
		return answer;
	}
	
	@Path("/deleteitem")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public Response deleteTodo(@QueryParam("itemid") String itemId) 
	{
		
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		ToDoItem item = null;
		try
		{
			item = model.getItemById(Integer.parseInt(itemId));
			model.deleteItem(item);
		}
		catch (NumberFormatException | TodoListPlatformException e)
		{

			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("/getitems")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems(@CookieParam("Authorization") String jsonWebToken)
	{
		HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
		List<ToDoItem> todos = new ArrayList<ToDoItem>();
		try
		{
			jwt jsonWT = new jwt();
			String email = (String)jsonWT.ConsumeAndCheckJWT(jsonWebToken).getClaimValue("email");
			todos.addAll(model.getUserItems(email));
		}
		catch (TodoListPlatformException | JoseException e)
		{

			e.printStackTrace();
		}
		
	
		JSONArray js= new JSONArray();
		
		//js.addAll(todos);
		for(ToDoItem item : todos)
		{
			JSONObject jsItem1 = new JSONObject();
			jsItem1.put("title", item.getTitle());
			jsItem1.put("description", item.getDescription());
			jsItem1.put("id", item.getId());
			js.add(jsItem1);
			
		}
		System.out.println("items = ");
		System.out.println(js.toJSONString());
		return Response.ok(js.toJSONString()).build();
	}

}
