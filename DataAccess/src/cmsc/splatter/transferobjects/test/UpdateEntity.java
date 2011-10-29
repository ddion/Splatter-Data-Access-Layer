/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.transferobjects.test;

import javax.persistence.*;
import java.io.Serializable;
import cmsc.splatter.transferobjects.Update;

/**
 *
 * @author david
 */
@Entity(name="UPDATEENTITY")
public class UpdateEntity implements Serializable {
    
    @Id //signifies the primary key
    @Column(name = "updateId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String updateId;
    
    @Column(name = "message", nullable = true)
    private String message;
    @Column(name = "createded", nullable = true)
    private String createTime;
    @Column(name = "posted", nullable = true)
    private boolean posted;
    @Column(name = "userid", nullable = false)
    private String userid;

    /**
     * @return the id
     */
    public String getId() {
        return updateId;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.updateId = id;
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

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the posted
     */
    public boolean isPosted() {
        return posted;
    }

    /**
     * @param posted the posted to set
     */
    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
        
}
