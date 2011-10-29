/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the ReferenceFactory.
 */
package cmsc.splatter.transferobjects.reference;

import cmsc.splatter.transferobjects.Connection;
import cmsc.splatter.transferobjects.ConnectionReference;
import cmsc.splatter.transferobjects.ObjectFactory;
import cmsc.splatter.transferobjects.Request;
import cmsc.splatter.transferobjects.RequestReference;
import cmsc.splatter.transferobjects.Update;
import cmsc.splatter.transferobjects.UpdateReference;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.transferobjects.UserReference;

/**
 * Singleton factory class that creates reference objects
 * based on the primary object: User->UserReference.
 */
public final class ReferenceFactory {
   
    private ObjectFactory objFactory;
    
    private static volatile ReferenceFactory instance = null;
    private static String ITEM_LINK_FORMAT="/splatter/%s/%s";
    
    /**
     * Factory method that returns the ReferenceFactory instance.
     * @return ReferenceFactory
     */
    public static ReferenceFactory getReferenceFactory () {
        if(instance == null) {
            synchronized(ReferenceFactory.class) {
                instance = new ReferenceFactory();
            }
        }
        
        return instance;
    }
    
    /**
     * Private constructor
     */
    private ReferenceFactory() {
        objFactory = new ObjectFactory();
    }
    
    /**
     * Creates a ConnectionReference from a Connection object.
     * @param connection - Connection
     * @return ConnectionReference
     */
    public ConnectionReference getConnectionReference(Connection connection) {
        ConnectionReference ref = objFactory.createConnectionReference();
        ref.setId(connection.getId());
        ref.setUser(connection.getRequestor());
        ref.setLink(String.format(ITEM_LINK_FORMAT, "connection", connection.getId()));
        return ref;
    }
    
    /**
     * Creates a RequestReference from a request object.
     * @param request - Request
     * @return RequestReference
     */
    public RequestReference getRequestReference(Request request) {
        RequestReference ref = objFactory.createRequestReference();
        ref.setId(request.getId());
        ref.setRequestedUser(request.getRequestedUser());
        ref.setRequestingUser(request.getRequestingUser().getUser());
        ref.setLink(String.format(ITEM_LINK_FORMAT, "request", request.getId()));
        return ref;
    }    
    
    /**
     * Creates an UpdateReference from an Update object.
     * @param update - Update
     * @return UpdateReference
     */
    public UpdateReference getUpdateReference(Update update) {
        UpdateReference ref = objFactory.createUpdateReference();
        ref.setId(update.getId());
        ref.setUser(update.getUser());
        ref.setMessage(update.getMessage());
        ref.setLink(String.format(ITEM_LINK_FORMAT, "update", update.getId()));
        return ref;
    }    
    
    /**
     * Creates an UserReference from an User object.
     * @param user User
     * @return UserReference
     */
    public UserReference getUserReference(User user) {
        UserReference ref = objFactory.createUserReference();
        ref.setId(user.getId());
        ref.setHandle(user.getHandle());
        ref.setEmail(user.getEmail());
        ref.setLink(String.format(ITEM_LINK_FORMAT, "user", user.getId()));
        return ref;
    }    
}
