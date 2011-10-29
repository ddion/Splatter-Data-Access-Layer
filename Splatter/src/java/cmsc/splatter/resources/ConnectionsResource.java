/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the ConnectionsResource.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.dataaccess.ConnectionType;
import cmsc.splatter.transferobjects.Connections;

/**
 *
 * 
 */
@Path("/splatter/user/{userid}/connections")
public class ConnectionsResource {
    
    @Context
    UriInfo uriInfo;  
    
    @GET
    @Produces({"text/xml","application/json"})    
    public Connections GetConnections(@PathParam("userid") String userid) {
        Connections connections = null;
        try {
            connections = DataAccessFactory.GetDataAccess().GetConnections(userid, ConnectionType.All);
        } catch (DataAccessException ex) {
            Logger.getLogger(ConnectionsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connections;
    }
        
    @GET
    @Path("/followers")
    @Produces({"text/xml","application/json"})
    public Connections GetFollowers(@PathParam("userid") String userid) {
        Connections connections = null;
        try {
            connections = DataAccessFactory.GetDataAccess().GetConnections(userid, ConnectionType.Followers);
        } catch (DataAccessException ex) {
            Logger.getLogger(ConnectionsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connections;
    }
    
    @GET
    @Path("/following")
    @Produces({"text/xml","application/json"})
    public Connections GetFollowing(@PathParam("userid") String userid) {
        Connections connections = null;
        try {
            connections = DataAccessFactory.GetDataAccess().GetConnections(userid, ConnectionType.Following);
        } catch (DataAccessException ex) {
            Logger.getLogger(ConnectionsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connections;
    }
}
