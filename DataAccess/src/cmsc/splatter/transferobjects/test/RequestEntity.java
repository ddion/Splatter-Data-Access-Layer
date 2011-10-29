/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.transferobjects.test;

import javax.persistence.*;
import java.io.Serializable;
import cmsc.splatter.transferobjects.Request;

/**
 *
 * @author david
 */
@Entity(name="REQUESTENTITY")
public class RequestEntity implements Serializable{
    @Id //signifies the primary key
    @Column(name = "requestId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String requestId;
    
    @Column(name = "requested", nullable = false)
    private String requested;
    @Column(name = "requesting", nullable = false)
    private String requesting;
    @Column(name = "created", nullable = true)
    private String created;        
    @Column(name = "message", nullable = true)
    private String message;

    /**
     * @return the id
     */
    public String getId() {
        return requestId;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.requestId = id;
    }

    /**
     * @return the requested
     */
    public String getRequested() {
        return requested;
    }

    /**
     * @param requested the requested to set
     */
    public void setRequested(String requested) {
        this.requested = requested;
    }

    /**
     * @return the requesting
     */
    public String getRequesting() {
        return requesting;
    }

    /**
     * @param requesting the requesting to set
     */
    public void setRequesting(String requesting) {
        this.requesting = requesting;
    }

    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
