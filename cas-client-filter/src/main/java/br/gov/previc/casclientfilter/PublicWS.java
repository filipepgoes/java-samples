package br.gov.previc.casclientfilter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("public")
public class PublicWS {
	@GET
    public Response test(@Context UriInfo uriInfo, 
    		@Context HttpServletRequest request) {        
        
            return Response.ok("This webservice is not protected.").build();
    }  
}
