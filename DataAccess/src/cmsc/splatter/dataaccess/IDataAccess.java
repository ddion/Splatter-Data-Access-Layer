/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the data access layer interface.
 * This represents the data access layer behaviors available 
 * to the web services.
 */
package cmsc.splatter.dataaccess;

import cmsc.splatter.transferobjects.Users;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.transferobjects.SearchTerms;
import cmsc.splatter.transferobjects.Connection;
import cmsc.splatter.transferobjects.Connections;
import cmsc.splatter.transferobjects.Request;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.Update;
import cmsc.splatter.transferobjects.Updates;

/**
 *
 * This interface defines the behaviors available 
 * for the data access layer.
 */
public interface IDataAccess {
    
    /**
     * Gets all of the users based on the search criteria
     * passed in. If the SearchTerm object passed in is
     * empty or null, all of the users in the system are
     * returned (privacy setting enabled).
     * @param searchterm - SearchTerms object
     * @return Users - a collection of UserReference objects
     * @throws DataAccessException
     */
    public Users GetUsers(SearchTerms searchterm) throws DataAccessException;
    
    /**
     * Gets User object based on the userId passed in.
     * If the user is not found, null is returned.
     * @param userId - String
     * @return User object
     * @throws DataAccessException
     */
    
    public User GetUser(String userId) throws DataAccessException;
    
    /**
     * Creates a new user in the system. If the handle
     * or email address already exist, an exception is
     * thrown.
     * @param user 
     * @throws DataAccessException
     */
    public void CreateUser(User user) throws DataAccessException;
    
    /**
     * Updates an existing User. If the user does not 
     * an exception is thrown.
     * @param user - User object
     * @throws DataAccessException
     */
    public void UpdateUser(User user) throws DataAccessException;
    
    /**
     * Deletes the specified user. If the user is not
     * found an exception is thrown.
     * @param userId - String
     * @throws DataAccessException
     */
    public void DeleteUser(String userId) throws DataAccessException;
    
    /**
     * Gets the list of available Connection objects available
     * for the specified user. The types of Connection objects
     * returned are based on the ConnectionType requested.
     * @param currentUserId - String - requesting user id
     * @param type - ConnectionType - the type of Connections requested
     * @return Connections - a list ConnectReference objects
     * @throws DataAccessException
     */
    public Connections GetConnections(String currentUserId, ConnectionType type) throws DataAccessException;
    
    /**
     * Creates a Connection based on a Request object.
     * @param request - Request
     * @return Connection object
     * @throws DataAccessException
     */
    public Connection CreateConnection(Request request) throws DataAccessException;
    
    /**
     * Gets a Connection object based on the specified
     * Connection id. If the Connection is not found, then
     * null is returned.
     * @param connectionId - String
     * @return Connection
     * @throws DataAccessException
     */
    public Connection GetConnection(String connectionId) throws DataAccessException;    
    
    /**
     * Deletes the specified Connection. If the Connection 
     * is not found, then an exception is thrown.
     * @param connectionId - String
     * @throws DataAccessException
     */
    public void DeleteConnection(String connectionId) throws DataAccessException;    
    
    /**
     * Creates a new connection Request.
     * @param request - Request
     * @throws DataAccessException
     */
    public void CreateRequest(Request request) throws DataAccessException;
    
    /**
     * Gets the specified Request. If the Request is not
     * found, null is returned.
     * @param requestId - String
     * @return Request
     * @throws DataAccessException
     */
    public Request GetRequest(String requestId) throws DataAccessException;
    
    /**
     * Gets a list of Requests for the specified User.
     * @param currentUserId - String
     * @return Requests - a list of RequestReference objects
     * @throws DataAccessException
     */
    public Requests GetRequests(String currentUserId) throws DataAccessException;
    
    /**
     * Updates the specified request object (either accepts or rejects the
     * Request). If the Request is being accepted, a new Connection object 
     * is returned. If the Request is being rejected, then null is returned.
     * @param requestId - String
     * @param op - RequestOperation (Accept or Reject)
     * @return Connection
     * @throws DataAccessException
     */
    public Connection UpdateRequest(String requestId, RequestOperation op) throws DataAccessException;
    
    /**
     * Updates the specified request object (either accepts or rejects the
     * Request). If the Request is being accepted, a new Connection object 
     * is returned. If the Request is being rejected, then null is returned.
     * @param request - Request
     * @param op - RequestOperation (Accept or Reject)
     * @return Connection
     * @throws DataAccessException
     */    
    public Connection UpdateRequest(Request request, RequestOperation op) throws DataAccessException;
    
    /**
     * Gets a list of updates for the specified user. The updates
     * returned are based on the UpdateType passed in (Followers, 
     * Following, Me, All).
     * @param currentUserId - String
     * @param type - UpdateType
     * @return Updates - a list of UpdateReference objects
     * @throws DataAccessException
     */
    public Updates GetUpdates(String currentUserId, UpdateType type) throws DataAccessException;

    /**
     * Gets a list of updates for a specified user. The updates
     * returned are for a specific user.
     * @param currentUserId - String
     * @param userId - String
     * @return Updates - a list of UpdateReference objects
     * @throws DataAccessException
     */    
    public Updates GetUpdates(String currentUserId, String userId) throws DataAccessException;

    /**
     * Gets a list of updates based on a Connection.
     * @param connection - Connection
     * @return Updates - a list of UpdateReference objects
     * @throws DataAccessException
     */    
    public Updates GetUpdates(Connection connection) throws DataAccessException;
    
    /**
     * Gets a specified update. If the Update if not
     * found, null is returned
     * @param id - String
     * @return Update
     * @throws DataAccessException
     */
    public Update GetUpdate(String id) throws DataAccessException;
    
    /**
     * Creates a new update.
     * @param update - Update
     * @throws DataAccessException
     */
    public void CreateUpdate(Update update) throws DataAccessException;

    /**
     * Updates an update. The Update is modified based on
     * the UpdateOperation value (either Save or Post).
     * @param update - Update
     * @param op - UpdateOperation
     * @throws DataAccessException
     */
    public void UpdateUpdate(Update update, UpdateOperation op) throws DataAccessException;
    
    /**
     * Updates an update. The Update is modified based on
     * the UpdateOperation value (either Save or Post).
     * @param updateId - String
     * @param op - UpdateOperation
     * @throws DataAccessException
     */
    public void UpdateUpdate(String updateId, UpdateOperation op) throws DataAccessException;    
    
    /**
     * Deletes the specified update. If the update is not 
     * found an exception is thrown.
     * @param id - String
     * @throws DataAccessException
     */
    public void DeleteUpdate(String id) throws DataAccessException;
    
}
