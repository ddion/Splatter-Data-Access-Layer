/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the UpdatesResource.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.dataaccess.UpdateType;
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
import cmsc.splatter.transferobjects.Updates;
import cmsc.splatter.transferobjects.Update;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * 
 */
@Path("/splatter/user/{userid}/updates")
public class UpdatesResource {
        
    @Context
    UriInfo uriInfo;  
    
    @GET
    @Produces({"text/xml","application/json"})    
    public Updates GetUpdates(@PathParam("userid") String userid) {
        Updates updates = null;
        try {
            updates = DataAccessFactory.GetDataAccess().GetUpdates(userid, UpdateType.Me);
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdatesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updates;
    }
    
    @Path("/all")
    @GET
    @Produces({"text/xml","application/json"})    
    public Updates GetAllUpdates(@PathParam("userid") String userid) {
        Updates updates = null;
        
        try {
            updates = DataAccessFactory.GetDataAccess().GetUpdates(userid, UpdateType.All);
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdatesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updates;
    }
    
    @Path("/followers")
    @GET
    @Produces({"text/xml","application/json"})    
    public Updates GetFollowerUpdates(@PathParam("userid") String userid) {
        Updates updates = null;
        try {
            updates = DataAccessFactory.GetDataAccess().GetUpdates(userid, UpdateType.Followers);
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdatesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updates;
    }
    
    @Path("/following")
    @GET
    @Produces({"text/xml","application/json"})    
    public Updates GetFollowingUpdates(@PathParam("userid") String userid) {
        Updates updates = null;
        try {
            updates = DataAccessFactory.GetDataAccess().GetUpdates(userid, UpdateType.Following);
        } catch (DataAccessException ex) {
            Logger.getLogger(UpdatesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updates;
    }
}
