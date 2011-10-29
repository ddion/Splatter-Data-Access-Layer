/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
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
import cmsc.splatter.transferobjects.User;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author david
 */
@Path("/splatter/user/{userid}")
public class UserResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces({"text/xml","application/json"})
    public User GetUser(@PathParam("userid") String userid) {
        User user = null;
        try {
            user = DataAccessFactory.GetDataAccess().GetUser(userid);
        } catch (DataAccessException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    @DELETE
    public Response DeleteUser(@PathParam("userid") String userid) {
        try {
            DataAccessFactory.GetDataAccess().DeleteUser(userid);
        } catch (DataAccessException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.OK).build();
    }
    
    @PUT
    @Consumes({"text/xml","application/json"})
    public Response UpdateUser(@PathParam("userid") String userid, User user) {
        try {
            DataAccessFactory.GetDataAccess().UpdateUser(user);
        } catch (DataAccessException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.OK).entity(user).build();
    }
    

}
