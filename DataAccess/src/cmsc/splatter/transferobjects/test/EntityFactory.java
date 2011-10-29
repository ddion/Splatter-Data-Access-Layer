/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.transferobjects.test;

import cmsc.splatter.transferobjects.Connection;
import cmsc.splatter.transferobjects.ConnectionReference;
import cmsc.splatter.transferobjects.Connections;
import cmsc.splatter.transferobjects.ObjectFactory;
import cmsc.splatter.transferobjects.Request;
import cmsc.splatter.transferobjects.RequestReference;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.Update;
import cmsc.splatter.transferobjects.UpdateReference;
import cmsc.splatter.transferobjects.Updates;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.transferobjects.UserName;
import cmsc.splatter.transferobjects.UserReference;
import cmsc.splatter.transferobjects.Users;
import java.util.List;
/**
 *
 * @author david
 */
public class EntityFactory {

    public static Connection GetConnection(ConnectionEntity entity) {
        ObjectFactory objFactory = new ObjectFactory();
        Connection conn = objFactory.createConnection();
        
        conn.setId(entity.getId());
        
        return conn;
    }
        
    public static ConnectionEntity GetConnectionEntity(Request req) {
        ConnectionEntity connEntity = new ConnectionEntity();
        
        connEntity.setId(req.getId());
        connEntity.setRequested(req.getRequestedUser().getId());
        connEntity.setRequestor(req.getRequestingUser().getUser().getId());
        
        return connEntity;
    }
    
    public static ConnectionEntity GetConnectionEntity(Connection conn) {
        ConnectionEntity connEntity = new ConnectionEntity();
        
        connEntity.setRequested(conn.getRequested().getId());
        connEntity.setRequestor(conn.getRequestor().getId());
        
        return connEntity;
    }
    
    public static ConnectionReference GetConnectionRef(ConnectionEntity connEntity) {
        ObjectFactory objFactory = new ObjectFactory();
        ConnectionReference conn = objFactory.createConnectionReference();
        conn.setId(connEntity.getId());
        conn.setLink(String.format("/splatter/connection/%s", connEntity.getId()));
        return conn;
    }    
    
    public static Connections GetConnections(List<ConnectionEntity> entities) {
        ObjectFactory objFactory = new ObjectFactory();
        Connections conns = objFactory.createConnections();
        
        ConnectionReference connRef = null;
        for(int idx=0;idx<entities.size();idx++) {
            connRef = GetConnectionRef(entities.get(idx));
            conns.getConnections().add(connRef);
        }
        
        return conns;
    }
    
    public static Request GetRequest(RequestEntity entity) {
        ObjectFactory objFactory = new ObjectFactory();
        Request req = objFactory.createRequest();
        
        req.setId(entity.getId());
        req.setMessage(entity.getMessage());
        req.setCreatetime(entity.getCreated());
        return req;
    }    

    public static RequestReference GetRequestRef(RequestEntity entity) {
        ObjectFactory objFactory = new ObjectFactory();
        RequestReference req = objFactory.createRequestReference();
        req.setId(entity.getId());
        req.setLink(String.format("/splatter/requests/%s", entity.getId()));
        return req;
    }        
    
    public static Requests GetRequests(List<RequestEntity> entities) {
        ObjectFactory objFactory = new ObjectFactory();
        Requests reqs = objFactory.createRequests();
        
        RequestReference reqRef = null;
        for(int idx=0;idx<entities.size();idx++) {
            reqRef = GetRequestRef(entities.get(idx));
            reqs.getRequests().add(reqRef);
        }
        
        return reqs;
    }    
    
    public static RequestEntity GetRequestEntity(Request request) {
        RequestEntity reqEntity = new RequestEntity();
        
        reqEntity.setMessage(request.getRequestingUser().getMessage());
        reqEntity.setRequested(request.getRequestedUser().getId());
        reqEntity.setRequesting(request.getRequestingUser().getId());
        
        return reqEntity;
    }

    public static UpdateReference GetUpdateRef(UpdateEntity entity) {
        ObjectFactory objFactory = new ObjectFactory();
        UpdateReference update = objFactory.createUpdateReference();
        update.setId(entity.getId());
        update.setMessage(entity.getMessage());
        update.setLink(String.format("/splatter/updates/%s", entity.getId()));
        update.setUser(null);
        return update;
    }        
    
    public static Updates GetUpdates(List<UpdateEntity> entities) {
        ObjectFactory objFactory = new ObjectFactory();
        Updates updates = objFactory.createUpdates();
        
        UpdateReference updateRef = null;
        for(int idx=0;idx<entities.size();idx++) {
            updateRef = GetUpdateRef(entities.get(idx));
            updates.getUpdates().add(updateRef);
        }
        
        return updates;
    }    
    
    public static Update GetUpdate(UpdateEntity entity) {
        ObjectFactory objFactory = new ObjectFactory();
        Update update = objFactory.createUpdate();
        
        update.setId(entity.getId());
        update.setMessage(entity.getMessage());
        update.setCreateTime(entity.getCreateTime());
        update.setPosted(entity.isPosted());
        update.setUser(null);
        return update;
    }    
    
    public static UpdateEntity GetUpdateEntity(Update update) {
        UpdateEntity updateEntity = new UpdateEntity();
        
        updateEntity.setMessage(update.getMessage());
        updateEntity.setPosted(update.isPosted());
        updateEntity.setUserid(update.getUser().getId());
        
        return updateEntity;
    }
    
    public static UserEntity GetUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setHandle(user.getHandle());
        userEntity.setFirst(user.getUsername().getFirst());
        userEntity.setMiddle(user.getUsername().getMiddle());
        userEntity.setLast(user.getUsername().getLast());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        if(user.isEmailPrivacy())
            userEntity.setEmailPrivacy(1);
        if(user.isUserPrivacy())
            userEntity.setUserPrivacy(1);
        return userEntity;
    }
    
    public static User GetUser(UserEntity userEntity) {
        ObjectFactory objFactory = new ObjectFactory();
        User user = objFactory.createUser();
        user.setId(userEntity.getUserid());
        UserName userName = objFactory.createUserName();
        user.setHandle(userEntity.getHandle());
        userName.setFirst(userEntity.getFirst());
        userName.setMiddle(userEntity.getMiddle());
        userName.setLast(userEntity.getLast());
        user.setUsername(userName);
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        if(userEntity.getEmailPrivacy() == 1)
            user.setEmailPrivacy(true);
        if(userEntity.getUserPrivacy() == 1)
            user.setUserPrivacy(true);
        return user;
    }
    
    public static UserReference GetUserRef(UserEntity userEntity) {
        ObjectFactory objFactory = new ObjectFactory();
        UserReference user = objFactory.createUserReference();
        user.setEmail(userEntity.getEmail());
        user.setHandle(userEntity.getHandle());
        user.setId(userEntity.getUserid());
        user.setLink(String.format("/splatter/user/%s", userEntity.getUserid()));
        return user;
    }    
    
    public static Users GetUsers(List<UserEntity> entities) {
        ObjectFactory objFactory = new ObjectFactory();
        Users users = objFactory.createUsers();
        
        UserReference userRef = null;
        for(int idx=0;idx<entities.size();idx++) {
            userRef = GetUserRef(entities.get(idx));
            users.getUsers().add(userRef);
        }
        
        return users;
    }    
}
