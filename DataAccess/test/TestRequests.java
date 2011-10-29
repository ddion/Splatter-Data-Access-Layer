/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.transferobjects.Connections;
import cmsc.splatter.dataaccess.ConnectionType;
import cmsc.splatter.transferobjects.RequestReference;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.Request;
import cmsc.splatter.transferobjects.UserReference;
import cmsc.splatter.transferobjects.RequestingUser;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Users;
import cmsc.splatter.transferobjects.ObjectFactory;
import cmsc.splatter.dataaccess.IDataAccess;
import cmsc.splatter.dataaccess.RequestOperation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class TestRequests {

    ObjectFactory objFactory = null;
    IDataAccess dataAccess = null;
    Users users = null;
    
    public TestRequests() {
        objFactory = new ObjectFactory();
        dataAccess = DataAccessFactory.GetDataAccess();        
        users = objFactory.createUsers();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        try {
            dataAccess = DataAccessFactory.GetDataAccess();
            users = TestUsers.CreateUsers(dataAccess, objFactory);                
            CreateRequests(dataAccess, objFactory);
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {        
        try {
            DeleteRequests(dataAccess);
            TestUsers.DeleteUsers(dataAccess);
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void CreateRequests() {
        try {
            User dave = dataAccess.GetUser(users.getUsers().get(0).getId());        
            User brendan = dataAccess.GetUser(users.getUsers().get(2).getId());        
            
            RequestingUser reqUser = null;
            
            //Create a request from dave to brendan
            reqUser = objFactory.createRequestingUser();
            reqUser.setUser((UserReference)dave.getReference());
            reqUser.setMessage("Hi brendan");
            reqUser.setId(dave.getId());
            
            Request req = objFactory.createRequest();
            
            req.setId("");
            req.setCreatetime("");
            req.setRequestedUser((UserReference)brendan.getReference());
            req.setRequestingUser(reqUser);
            dataAccess.CreateRequest(req);        
            
            Requests reqs = dataAccess.GetRequests(brendan.getId());
            RequestReference ref = reqs.getRequests().get(reqs.getRequests().size()-1);
            req = null;
            req = dataAccess.GetRequest(ref.getId());
            assertEquals("Hi brendan", req.getMessage());
            //assertEquals("dd", req.getRequestingUser().getUser().getHandle());
            //assertEquals("dd", req.getRequestingUser().getUser().getHandle());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void GetRequest() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            RequestReference ref = reqs.getRequests().get(reqs.getRequests().size()-1);
            
            Request req = null;
            req = dataAccess.GetRequest(ref.getId());
            assertEquals("Hey buddy?", req.getMessage());
            //assertEquals("mugs", req.getRequestingUser().getUser().getHandle());        
            //assertEquals("mugs", req.getRequestingUser().getUser().getHandle());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void GetRequests() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(3).getId());
            assertEquals(2, reqs.getRequests().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void UpdateRequest() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(reqs.getRequests().size(), 2);
            RequestReference reject = reqs.getRequests().get(0);
            RequestReference accept = reqs.getRequests().get(1);
            dataAccess.UpdateRequest(reject.getId(), RequestOperation.Reject);
            
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(1, reqs.getRequests().size());
            
            Connections  conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(0, conns.getConnections().size());
            
            dataAccess.UpdateRequest(accept.getId(), RequestOperation.Accept);
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(0, reqs.getRequests().size());
            
            conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(1, conns.getConnections().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void CreateRequests(IDataAccess dataAccess, ObjectFactory objFactory) throws DataAccessException {
        
        Users users = dataAccess.GetUsers(null);               
        User dave = dataAccess.GetUser(users.getUsers().get(0).getId());        
        User megan = dataAccess.GetUser(users.getUsers().get(1).getId());        
        User brendan = dataAccess.GetUser(users.getUsers().get(2).getId());        
        User piper = dataAccess.GetUser(users.getUsers().get(3).getId());        
        User diesel = dataAccess.GetUser(users.getUsers().get(4).getId());

        RequestingUser reqUser = null;
        
        //Create a request from megan to dave
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)megan.getReference());
        reqUser.setMessage("Hey, whats going on?");
        reqUser.setId(megan.getId());
        
        Request req = objFactory.createRequest();
        
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)dave.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);
        
        //Create a request from brendan to megan
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)brendan.getReference());
        reqUser.setMessage("Ruff?");
        reqUser.setId(brendan.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)megan.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);                
        
        //Create a request from piper to brendan
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)piper.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(piper.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)brendan.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);  
        
        //Create a request from diesel to piper
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)diesel.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(diesel.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)piper.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);         
        
        //Create a request from dave to diesel
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)dave.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(dave.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)diesel.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);  
        
        //Create a request from dave to piper
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)dave.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(dave.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)piper.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);   
        
        //Create a request from brendan to diesel
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)brendan.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(brendan.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)diesel.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req); 
        
        //Create a request from diesel to megan
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)diesel.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(diesel.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)megan.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);        
        
        //Create a request from diesel to dave
        reqUser = objFactory.createRequestingUser();
        reqUser.setUser((UserReference)diesel.getReference());
        reqUser.setMessage("Hey buddy?");
        reqUser.setId(diesel.getId());
        
        req = objFactory.createRequest();
        req.setId("");
        req.setCreatetime("");
        req.setRequestedUser((UserReference)dave.getReference());
        req.setRequestingUser(reqUser);
        dataAccess.CreateRequest(req);        
    }    
    
    public static void DeleteRequests(IDataAccess dataAccess) throws DataAccessException {
        
        Users users = dataAccess.GetUsers(null);
        for(int usrIdx=0;usrIdx<users.getUsers().size();usrIdx++) {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(usrIdx).getId());
            for(int idx=0;idx<reqs.getRequests().size();idx++)
                dataAccess.UpdateRequest(reqs.getRequests().get(idx).getId(), RequestOperation.Reject);        
        }
    }
}
