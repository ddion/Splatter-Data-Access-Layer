/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.transferobjects.Connection;
import cmsc.splatter.transferobjects.Requests;
import cmsc.splatter.transferobjects.RequestReference;
import cmsc.splatter.transferobjects.Connections;
import cmsc.splatter.dataaccess.RequestOperation;
import cmsc.splatter.dataaccess.ConnectionType;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.transferobjects.Users;
import cmsc.splatter.dataaccess.IDataAccess;
import cmsc.splatter.transferobjects.ObjectFactory;
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
public class TestConnections {
    ObjectFactory objFactory = null;
    IDataAccess dataAccess = null;
    Users users = null;
    
    public TestConnections() {
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
            TestRequests.CreateRequests(dataAccess, objFactory);
        } catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {        
        try {
            TestRequests.DeleteRequests(dataAccess);        
            TestUsers.DeleteUsers(dataAccess);
        }
        // TODO add test methods here.
        // The methods must be annotated with annotation @Test. For example:
        //
        // @Test
        // public void hello() {}
        catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void CreateConnection() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());        
            assertEquals(reqs.getRequests().size(), 2);
            RequestReference ref = reqs.getRequests().get(0);
            dataAccess.CreateConnection(dataAccess.GetRequest(ref.getId()));
            
            Connections  conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(1, conns.getConnections().size());
            
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(1, reqs.getRequests().size());
                    
            ref = reqs.getRequests().get(0);
            dataAccess.UpdateRequest(ref.getId(), RequestOperation.Accept);
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(0, reqs.getRequests().size());
            
            conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(2, conns.getConnections().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void GetConnection() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());        
            assertEquals(reqs.getRequests().size(), 2);
            RequestReference ref = reqs.getRequests().get(0);
            dataAccess.CreateConnection(dataAccess.GetRequest(ref.getId()));
            
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(1, reqs.getRequests().size());
                    
            ref = reqs.getRequests().get(0);
            dataAccess.UpdateRequest(ref.getId(), RequestOperation.Accept);
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(0, reqs.getRequests().size());
            
            Connections conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(2, conns.getConnections().size());
            
            Connection conn = dataAccess.GetConnection(conns.getConnections().get(0).getId());
            assertEquals("mugs", conn.getRequestor().getHandle());
            conn = dataAccess.GetConnection(conns.getConnections().get(1).getId());
            assertEquals("deez", conn.getRequestor().getHandle());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void GetConnections () {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());        
            assertEquals(reqs.getRequests().size(), 2);
            RequestReference ref = reqs.getRequests().get(0);
            dataAccess.CreateConnection(dataAccess.GetRequest(ref.getId()));
            
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(1, reqs.getRequests().size());
                    
            ref = reqs.getRequests().get(0);
            dataAccess.UpdateRequest(ref.getId(), RequestOperation.Accept);
            reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());
            assertEquals(0, reqs.getRequests().size());
            
            Connections conns = dataAccess.GetConnections(users.getUsers().get(0).getId(), ConnectionType.All);
            assertEquals(2, conns.getConnections().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test 
    public void DeleteConnection() {
        try {
            Requests reqs = dataAccess.GetRequests(users.getUsers().get(0).getId());        
            assertEquals(2, reqs.getRequests().size());
            RequestReference ref = reqs.getRequests().get(0);
            dataAccess.CreateConnection(dataAccess.GetRequest(ref.getId()));
            
            reqs = dataAccess.GetRequests("1");
            assertEquals(1, reqs.getRequests().size());
                    
            ref = reqs.getRequests().get(0);
            dataAccess.UpdateRequest(ref.getId(), RequestOperation.Accept);
            reqs = dataAccess.GetRequests("1");
            assertEquals(0, reqs.getRequests().size());
            
            Connections conns = dataAccess.GetConnections("1", ConnectionType.All);
            assertEquals(2, conns.getConnections().size());        
            
            dataAccess.DeleteConnection(conns.getConnections().get(0).getId());
            
            conns = dataAccess.GetConnections("1", ConnectionType.All);
            assertEquals(1, conns.getConnections().size());
            
            dataAccess.DeleteConnection(conns.getConnections().get(0).getId());
            
            conns = dataAccess.GetConnections("1", ConnectionType.All);
            assertEquals(0, conns.getConnections().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
