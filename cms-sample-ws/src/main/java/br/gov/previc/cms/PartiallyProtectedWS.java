package br.gov.previc.cms;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("partiallyprotected")
public class PartiallyProtectedWS {
	@GET
	@RolesAllowed({"usuario"})
    public Response protectedGet(@Context UriInfo uriInfo, 
    		@Context HttpServletRequest request) {        
        
            return Response.ok("This webservice is protected on the get method.").build();
    }  
	@POST
    public Response publicPost(@Context UriInfo uriInfo, 
    		@Context HttpServletRequest request) {        
        
            return Response.ok("This webservice is public on the post method.").build();
    }  
}
