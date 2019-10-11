package br.gov.previc.casclientfilter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("protected")
public class ProtectedByFilterWS {
	@GET
    public Response protectedGet(@Context UriInfo uriInfo, 
    		@Context HttpServletRequest request) {        
        
            return Response.ok("This webservice is protected.").build();
    }  
}
