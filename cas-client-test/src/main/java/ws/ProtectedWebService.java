package ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("protected")
public class ProtectedWebService {
	@GET
    @Path("consulta")
	public Response consulta(@Context UriInfo uriInfo,@Context HttpServletRequest request) {
		return Response.ok("Protected.").build();
		
	}
}
