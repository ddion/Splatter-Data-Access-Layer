/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cmsc.splatter.dataaccess.DataAccessException;
import cmsc.splatter.transferobjects.UserReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.notification.Failure;
import cmsc.splatter.transferobjects.UserName;
import cmsc.splatter.transferobjects.User;
import cmsc.splatter.transferobjects.UserCollection;
import cmsc.splatter.transferobjects.ObjectFactory;
import cmsc.splatter.transferobjects.Users;
import cmsc.splatter.dataaccess.impl.DataAccessFactory;
import cmsc.splatter.dataaccess.IDataAccess;
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
public class TestUsers {
    
    ObjectFactory objFactory = null;
    IDataAccess dataAccess = null;
    Users users = null;
    
    public TestUsers() {
        objFactory = new ObjectFactory();
        dataAccess = DataAccessFactory.GetDataAccess();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        dataAccess = DataAccessFactory.GetDataAccess();
        try {
            users = CreateUsers(dataAccess, objFactory);
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            DeleteUsers(dataAccess);
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void CreateUser() {
        try {
            Users users = dataAccess.GetUsers(null);
            User user = objFactory.createUser();
            UserName name = objFactory.createUserName();
            name.setFirst("robert");
            name.setMiddle("j");
            name.setLast("dion");
            user.setHandle("deacon");
            user.setUsername(name);
            user.setId("6");
            user.setPassword("something");
            user.setEmail("robert@yahoo.com");
            user.setEmailPrivacy(false);
            user.setUserPrivacy(false); 
            dataAccess.CreateUser(user);
            
            users = dataAccess.GetUsers(null);
            assertEquals(6, users.getUsers().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void GetUser() {
        UserReference userRef = users.getUsers().get(0);
        User user=null;
        try {
            user = dataAccess.GetUser(userRef.getId());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("david", user.getUsername().getFirst());
    }
    
    @Test
    public void DeleteUser() {
        try {
            UserReference userRef = users.getUsers().get(0);
            dataAccess.DeleteUser(userRef.getId());
            Users users = dataAccess.GetUsers(null);
            assertEquals(4, users.getUsers().size());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void UpdateUser() {
        try {
            UserReference userRef = users.getUsers().get(0);
            User user = dataAccess.GetUser(userRef.getId());
            user.setEmail("dave@gmail.com");
            dataAccess.UpdateUser(user);
            
            user = null;
            user = dataAccess.GetUser(userRef.getId());
            assertEquals("dave@gmail.com", user.getEmail());
        } catch (DataAccessException ex) {
            Logger.getLogger(TestUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Users CreateUsers(IDataAccess dataAccess, ObjectFactory objFactory) throws DataAccessException {
        UserCollection users = objFactory.createUserCollection();
        User user = objFactory.createUser();
        UserName name = objFactory.createUserName();
        name.setFirst("david");
        name.setMiddle("a");
        name.setLast("dion");
        user.setHandle("dd");
        user.setUsername(name);
        user.setId("1");
        user.setPassword("something");
        user.setEmail("dave@yahoo.com");
        user.setEmailPrivacy(false);
        user.setUserPrivacy(false); 
        dataAccess.CreateUser(user);
        
        user = objFactory.createUser();
        name = objFactory.createUserName();
        name.setFirst("megan");
        name.setMiddle("l");
        name.setLast("dion");
        user.setHandle("mugs");
        user.setUsername(name);
        user.setId("2");
        user.setPassword("something");
        user.setEmail("megan@yahoo.com");
        user.setEmailPrivacy(false);
        user.setUserPrivacy(false); 
        dataAccess.CreateUser(user);
        
        user = objFactory.createUser();
        name = objFactory.createUserName();
        name.setFirst("brendan");
        name.setMiddle("f");
        name.setLast("dion");
        user.setHandle("finn");
        user.setUsername(name);
        user.setId("3");
        user.setPassword("something");
        user.setEmail("brendan@yahoo.com");
        user.setEmailPrivacy(false);
        user.setUserPrivacy(false); 
        dataAccess.CreateUser(user);
        
        user = objFactory.createUser();
        name = objFactory.createUserName();
        name.setFirst("piper");
        name.setMiddle("");
        name.setLast("dion");
        user.setHandle("pipe");
        user.setUsername(name);
        user.setId("4");
        user.setPassword("something");
        user.setEmail("piper@yahoo.com");
        user.setEmailPrivacy(false);
        user.setUserPrivacy(false); 
        dataAccess.CreateUser(user);       
        
        user = objFactory.createUser();
        name = objFactory.createUserName();
        name.setFirst("diesel");
        name.setMiddle("");
        name.setLast("dion");
        user.setHandle("deez");
        user.setUsername(name);
        user.setId("5");
        user.setPassword("something");
        user.setEmail("diesel@yahoo.com");
        user.setEmailPrivacy(false);
        user.setUserPrivacy(false); 
        dataAccess.CreateUser(user);        
        
        return dataAccess.GetUsers(null);
    }
    
    public static void DeleteUsers(IDataAccess dataAccess) throws DataAccessException {
        Users users = dataAccess.GetUsers(null);
        for(int idx=0;idx<users.getUsers().size();idx++)
            dataAccess.DeleteUser(users.getUsers().get(idx).getId());
    }
}
