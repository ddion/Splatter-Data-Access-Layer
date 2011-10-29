/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the RequestsResource.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * 
 */
@Path("/splatter/user/{userid}/requests")
public class RequestsResource {
    
    @Context
    UriInfo uriInfo;  
    
    @GET
    @Produces({"text/xml","application/json"})    
    public Requests GetConnections(@PathParam("userid") String userid) {
        Requests reqs = null;
        try {
            reqs =  DataAccessFactory.GetDataAccess().GetRequests(userid);
        } catch (DataAccessException ex) {
            Logger.getLogger(RequestsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reqs;
    }
    
    @POST
    @Consumes({"text/xml","application/json"})
    @Produces({"text/xml","application/json"})
    public Response CreateRequest(Request request) {
        try {
            DataAccessFactory.GetDataAccess().CreateRequest(request);
            return Response.status(Status.OK).entity(request).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(RequestsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
}
