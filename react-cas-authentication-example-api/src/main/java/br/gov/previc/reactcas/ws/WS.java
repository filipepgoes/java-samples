package br.gov.previc.reactcas.ws;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;



@Stateless
public class WS implements WSInterface{
	



	@Override
	public Response protectedInfo(UriInfo uriInfo, HttpServletRequest request) {
		return Response.ok().entity("I'm a protected information.").build();
	}
	/*
	@Override
	public Response login(UriInfo uriInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public Response validateToken(UriInfo uriInfo, HttpServletRequest request) {
		return Response.ok().entity("Valid token.").build();
	}

	
	
	
}