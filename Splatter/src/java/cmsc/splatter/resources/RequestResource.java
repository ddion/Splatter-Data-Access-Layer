/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.dataaccess.RequestOperation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Request;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author david
 */
@Path("/splatter/request/{requestid}")
public class RequestResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces({"text/xml","application/json"})
    public Request GetRequest(@PathParam("requestid") String requestid) {
        Request req = null;
        try {
            req = DataAccessFactory.GetDataAccess().GetRequest(requestid);
        } catch (DataAccessException ex) {
            Logger.getLogger(RequestResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return req;
    }
    
    @Path("/accept")
    @PUT
    @Consumes({"text/xml","application/json"})
    public Response AcceptRequest(@PathParam("requestid") String requestid) {
        try {
            DataAccessFactory.GetDataAccess().UpdateRequest(requestid, RequestOperation.Accept);
            return Response.status(Status.OK).entity(new String("Accepted")).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(RequestResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }    
    
    @Path("/reject")
    @PUT
    @Consumes({"text/xml","application/json"})
    public Response RejectRequest(@PathParam("requestid") String requestid) {
        try {
            DataAccessFactory.GetDataAccess().UpdateRequest(requestid, RequestOperation.Reject);
            return Response.status(Status.OK).entity(new String("Rejected")).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(RequestResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }    
}
