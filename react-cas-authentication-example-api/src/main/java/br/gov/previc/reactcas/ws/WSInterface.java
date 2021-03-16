package br.gov.previc.reactcas.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("api")
public interface WSInterface {
	
	@GET
    @Path("protected") 
	public Response protectedInfo(
			@Context UriInfo uriInfo, 
			@Context HttpServletRequest request);
	/*
	@POST
    @Path("login") 
	public Response login(
			@Context UriInfo uriInfo, 
			@Context HttpServletRequest request);
	*/
	@GET
    @Path("") 
	public Response validateToken(
			@Context UriInfo uriInfo, 
			@Context HttpServletRequest request);
	
}