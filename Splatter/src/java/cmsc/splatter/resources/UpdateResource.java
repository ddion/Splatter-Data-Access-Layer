/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.dataaccess.RequestOperation;
import cmsc.splatter.dataaccess.UpdateOperation;
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
import cmsc.splatter.transferobjects.Update;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author david
 */
@Path("/splatter/update/{updateid}")
public class UpdateResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces({"text/xml","application/json"})
    public Update GetUpdate(@PathParam("updateid") String updateid) {
        Update update = null;
        try {
            update = DataAccessFactory.GetDataAccess().GetUpdate(updateid);
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdateResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return update;
    }
    
    @DELETE
    public Response DeleteUpdate(@PathParam("updateid") String updateid) {
        try {
            DataAccessFactory.GetDataAccess().DeleteUpdate(updateid);
            return Response.status(Status.OK).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdateResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @Path("/save")
    @PUT
    public Response SaveUpdate(@PathParam("updateid") String updateid) {
        try {
            DataAccessFactory.GetDataAccess().UpdateUpdate(updateid, UpdateOperation.Save);
            return Response.status(Status.OK).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdateResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @Path("/post")
    @PUT
    public Response PostUpdate(@PathParam("updateid") String updateid) {
        try {
            DataAccessFactory.GetDataAccess().UpdateUpdate(updateid, UpdateOperation.Post);
            return Response.status(Status.OK).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdateResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
}
