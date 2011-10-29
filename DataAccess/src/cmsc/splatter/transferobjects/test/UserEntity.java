/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.transferobjects.test;

import javax.persistence.*;
import java.io.Serializable;
import cmsc.splatter.transferobjects.User;

/**
 *
 * @author david
 */
@Entity(name="USERENTITY")
public class UserEntity implements Serializable{
    
    @Id //signifies the primary key
    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    
    @Column(name = "handle", nullable = false)
    private String handle;    
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "middle", nullable = false)
    private String middle;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "userPrivacy", nullable = true)
    private int userPrivacy;
    @Column(name = "emailPrivacy", nullable = true)
    private int emailPrivacy;

    /**
     * @return the userid
     */
    public String getUserid() {
        return userId;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userId = userid;
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return firstName;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.firstName = first;
    }

    /**
     * @return the middle
     */
    public String getMiddle() {
        return middle;
    }

    /**
     * @param middle the middle to set
     */
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return lastName;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.lastName = last;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the userPrivacy
     */
    public int getUserPrivacy() {
        return userPrivacy;
    }

    /**
     * @param userPrivacy the userPrivacy to set
     */
    public void setUserPrivacy(int userPrivacy) {
        this.userPrivacy = userPrivacy;
    }

    /**
     * @return the emailPrivacy
     */
    public int getEmailPrivacy() {
        return emailPrivacy;
    }

    /**
     * @param emailPrivacy the emailPrivacy to set
     */
    public void setEmailPrivacy(int emailPrivacy) {
        this.emailPrivacy = emailPrivacy;
    }
        
}
