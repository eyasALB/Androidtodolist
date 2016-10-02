package com.project.todolist.servies;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/demo")
public class test {
	@GET
	 @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
		//System.out.println("innn");
	    return "Hello Jersey";
	  }

}
