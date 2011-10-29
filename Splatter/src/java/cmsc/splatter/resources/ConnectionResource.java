/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.resources;

import cmsc.splatter.dataaccess.DataAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Connection;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author david
 */
@Path("/splatter/connection/{connectionid}")
public class ConnectionResource {
    
    @Context
    UriInfo uriInfo;    
    
    @GET
    @Produces({"text/xml","application/json"})
    public Connection GetConnection(@PathParam("connectionid") String connectionid) {
        Connection connection = null;
        try {
            connection = DataAccessFactory.GetDataAccess().GetConnection(connectionid);
        } catch (DataAccessException ex) {
            Logger.getLogger(ConnectionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    @DELETE
    public Response DeleteConnection(@PathParam("connectionid") String connectionid) {
        try {
            DataAccessFactory.GetDataAccess().DeleteConnection(connectionid);
            return Response.status(Status.OK).build();
        } catch (DataAccessException dataAccessException) {
            return Response.serverError().build();
        }
    }
    
}
