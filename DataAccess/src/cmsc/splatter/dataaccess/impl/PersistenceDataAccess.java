/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.dataaccess.impl;

import cmsc.splatter.dataaccess.ConnectionType;
import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.dataaccess.RequestOperation;
import cmsc.splatter.dataaccess.UpdateOperation;
import cmsc.splatter.dataaccess.UpdateType;
import cmsc.splatter.transferobjects.Connection;
import cmsc.splatter.transferobjects.Connections;
import cmsc.splatter.transferobjects.Request;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.SearchTerms;
import cmsc.splatter.transferobjects.Update;
import cmsc.splatter.transferobjects.Updates;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.transferobjects.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import cmsc.splatter.transferobjects.test.*;
import cmsc.splatter.dataaccess.IDataAccess;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author david
 */
public class PersistenceDataAccess implements IDataAccess {

    @Override
    public Connection CreateConnection(Request req) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        ConnectionEntity newConn = null;
        
        try {
            newConn = EntityFactory.GetConnectionEntity(req);
            em.persist(newConn);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        return EntityFactory.GetConnection(newConn);
    }

    @Override
    public Connections GetConnections(String currentUserId, ConnectionType type) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        List<ConnectionEntity> results = null;
        try {
            Query qry = em.createQuery("SELECT x FROM CONNECTIONENTITY x");            
            results = qry.getResultList();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        return EntityFactory.GetConnections(results);                                
    }

    @Override
    public Connection GetConnection(String connectionId) throws DataAccessException {
                EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        ConnectionEntity conn = null;
        try {
            conn = em.find(ConnectionEntity.class, connectionId);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        if(conn == null)
            return null;
        else
            return EntityFactory.GetConnection(conn);
    }

    @Override
    public void DeleteConnection(String connectionId) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        try {
            ConnectionEntity conn = em.find(ConnectionEntity.class, connectionId);
            em.getTransaction().begin();
            em.remove(conn);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
    }

    @Override
    public void CreateRequest(Request request) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();    
        
        try {
            userTransaction.begin();
            RequestEntity newRequest = EntityFactory.GetRequestEntity(request);
            em.persist(newRequest);
            userTransaction.commit();
        } catch (Exception e) {           
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }        
    }    
        
    @Override
    public Request GetRequest(String requestId) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        RequestEntity req = null;
        try {
            req = em.find(RequestEntity.class, requestId);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        if(req == null)
            return null;
        else
            return EntityFactory.GetRequest(req);
    }

    @Override
    public Requests GetRequests(String currentUserId) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        List<RequestEntity> results = null;
        try {
            Query qry = em.createQuery("SELECT x FROM REQUESTENTITY x where x.requested = :userid");            
            qry.setParameter("userid", currentUserId);            
            results = qry.getResultList();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        return EntityFactory.GetRequests(results); 
    }

    @Override
    public Connection UpdateRequest(String requestId, RequestOperation op) throws DataAccessException {
        Request req = GetRequest(requestId);
        return UpdateRequest(req, op);
    }

    @Override
    public Connection UpdateRequest(Request request, RequestOperation op) throws DataAccessException {
        Connection conn = null;
        
        if(op == RequestOperation.Accept)
            CreateConnection(request);
        
        DeleteRequest(request.getId());
        
        return conn;        
    }
    
    private void DeleteRequest(String id) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        try {
            RequestEntity req = em.find(RequestEntity.class, id);
            em.getTransaction().begin();
            em.remove(req);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }    
    }
    
    @Override
    public Updates GetUpdates(String currentUserId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
    
    @Override
    public Updates GetUpdates(Connection connection) {
        return GetUpdates(connection.getRequested().getId(), 
                connection.getRequestor().getId());
    }    

    @Override
    public Updates GetUpdates(String currentUserId, UpdateType type) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();

        List<UpdateEntity> results = null;
        try {
            Query qry = null;            
            
            switch (type) {
                case Me:
                    qry = em.createQuery("SELECT x FROM UPDATEENTITY x where x.userid = :userid");
                    qry.setParameter("userid", currentUserId);
                    break;
                case Following:                    
                    qry = em.createQuery("SELECT x FROM UPDATEENTITY x where x.userid = :userid");
                    qry.setParameter("userid", currentUserId);
                    break;
                case Followers:
                    qry = em.createQuery("SELECT x FROM UPDATEENTITY x where x.userid = :userid");
                    qry.setParameter("userid", currentUserId);
                    break;
                default:
                    qry = em.createQuery("SELECT x FROM UPDATEENTITY x where x.userid = :userid");
                    qry.setParameter("userid", currentUserId);
                    break;
            }
            
            results = qry.getResultList();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        return EntityFactory.GetUpdates(results);                                
    }
    
    @Override
    public void CreateUpdate(Update update) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();    
        
        try {
            userTransaction.begin();
            UpdateEntity newUpdate = EntityFactory.GetUpdateEntity(update);
            em.persist(newUpdate);
            userTransaction.commit();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }        
    }    

    @Override
    public Update GetUpdate(String id) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        UpdateEntity update = null;
        try {
            update = em.find(UpdateEntity.class, id);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        if(update == null)
            return null;
        else
            return EntityFactory.GetUpdate(update);
    }

    @Override
    public void UpdateUpdate(Update update, UpdateOperation op) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        try {
            Query qry = em.createQuery("UPDATE UPDATEENTITY x SET x.posted = :posted "
                    + " WHERE x.updateId = :id");
            
            qry.setParameter("posted", update.isPosted());
            qry.setParameter("id", update.getId());
            qry.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
    }

    @Override
    public void UpdateUpdate(String updateId, UpdateOperation op) throws DataAccessException {
        Update update = GetUpdate(updateId);
        UpdateUpdate(update, op);
    }

    @Override
    public void DeleteUpdate(String id) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        try {
            UpdateEntity conn = em.find(UpdateEntity.class, id);
            em.getTransaction().begin();
            em.remove(conn);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
    }
    
    @Override
    public Users GetUsers(SearchTerms searchterm) throws DataAccessException {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        List<UserEntity> results = null;
        try {
            Query qry = em.createQuery("SELECT x FROM USERENTITY x");            
            results = qry.getResultList();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            if(em!=null)
                em.close();
            
            if(entityManagerFactory!=null)
                entityManagerFactory.close();        
        }
        
        return EntityFactory.GetUsers(results);                                
    }

    @Override
    public User GetUser(String userId) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        UserEntity user = em.find(UserEntity.class, userId);
        
        em.close();
        entityManagerFactory.close();
        
        if(user == null)
            return null;
        else
            return EntityFactory.GetUser(user);                                        
    }
    
    @Override
    public void CreateUser(User user) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();    
        
        userTransaction.begin();
        UserEntity newUser = EntityFactory.GetUserEntity(user);
        em.persist(newUser);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();        
    }    

    @Override
    public void UpdateUser(User user) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        Query qry = em.createQuery ("UPDATE USERENTITY x SET x.handle = :handle, "
                + "x.firstName = :firstName, x.middle = :middle, x.lastName = :lastName, x.password = :password,"
                + "x.email = :email, x.userPrivacy = :userPrivacy, x.emailPrivacy = :emailPrivacy"
                + " WHERE x.userId = :id");
        
        int isPrivate=0, isEmailPrivate=0;
        qry.setParameter ("handle", user.getHandle());
        qry.setParameter ("firstName", user.getUsername().getFirst());
        qry.setParameter ("middle", user.getUsername().getMiddle());
        qry.setParameter ("lastName", user.getUsername().getLast());
        qry.setParameter ("password", user.getPassword());
        qry.setParameter ("email", user.getEmail());
        if(user.isUserPrivacy())
            isPrivate = 1;
        qry.setParameter ("userPrivacy", isPrivate);
        if(user.isEmailPrivacy())
            isEmailPrivate=1;
        qry.setParameter ("emailPrivacy", isEmailPrivate);
        qry.setParameter ("id", user.getId());
        
        userTransaction.begin();
        qry.executeUpdate();
        userTransaction.commit();
        
        em.close();
        entityManagerFactory.close();
    }

    @Override
    public void DeleteUser(String userId) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("DataAccessPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        UserEntity user = em.find(UserEntity.class, userId);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();        
        em.close();
        
        entityManagerFactory.close();
    }    
}
