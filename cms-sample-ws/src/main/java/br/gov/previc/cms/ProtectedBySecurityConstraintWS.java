package br.gov.previc.cms;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("protectedbysecurityconstraint")
public class ProtectedBySecurityConstraintWS {
	@GET	
    public Response protectedGet(@Context UriInfo uriInfo, 
    		@Context HttpServletRequest request) {        
        
            return Response.ok("This webservice is protected by security-constraint.").build();
    } 
}
