/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the IReference object.
 */
package cmsc.splatter.transferobjects.reference;

/**
 * This interface defines a reference object in relation to a primary
 * object. A primary object contains all of the information about the
 * object, while a reference object contains only minimal information
 * about the object a link referencing the object. A primary object 
 * can be referenced from link. An example would be a User object, 
 * which can be referenced by the following link: /splatter/user/{userid}
 */
public interface IReference {
    
    /**
     * Gets the id for the item.
     * @return 
     */
    public String getId();
    
    /**
     * Gets the RESTful web service resource URL
     * for the item
     * @return 
     */
    public String getLink();
}
