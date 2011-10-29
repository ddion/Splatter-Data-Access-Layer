/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.transferobjects.test;

import javax.persistence.*;
import java.io.Serializable;
import cmsc.splatter.transferobjects.Connection;

/**
 *
 * @author david
 */
@Entity(name="CONNECTIONENTITY")
public class ConnectionEntity implements Serializable {
    
    @Id //signifies the primary key
    @Column(name = "connectionId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String connectionId;    
    
    @Column(name = "requested", nullable = false)
    private String requested;
    @Column(name = "requestor", nullable = false)
    private String requestor;
    @Column(name = "created", nullable = true)
    private String created;

    /**
     * @return the id
     */
    public String getId() {
        return connectionId;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.connectionId = id;
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
     * @return the requestor
     */
    public String getRequestor() {
        return requestor;
    }

    /**
     * @param requestor the requestor to set
     */
    public void setRequestor(String requestor) {
        this.requestor = requestor;
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
    
    
}
