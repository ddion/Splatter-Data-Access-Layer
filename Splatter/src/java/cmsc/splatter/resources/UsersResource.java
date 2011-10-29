/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the UsersResource.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Users;
import cmsc.splatter.transferobjects.User;
import java.net.URI;
import javax.ws.rs.core.Response;

/**
 *
 * 
 */
@Path("/splatter/users")
public class UsersResource {
    
    @Context
    UriInfo uriInfo;  
    
    @GET
    @Consumes({"text/xml","application/json"})
    @Produces({"text/xml","application/json"})    
    public Users GetUsers() {
        Users users = null;
        try {
            users = DataAccessFactory.GetDataAccess().GetUsers(null);
        } catch (DataAccessException ex) {
            Logger.getLogger(UsersResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @POST
    @Consumes({"text/xml","application/json"})
    @Produces({"text/xml","application/json"})    
    public Response CreateUser(User user) {        
        try {
            DataAccessFactory.GetDataAccess().CreateUser(user);
            return Response.created(URI.create("/splatter/users/1")).entity(user).build();
        } catch (DataAccessException ex) {
            Logger.getLogger(UsersResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }    
}
