/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the IReferencable object.
 */
package cmsc.splatter.transferobjects.reference;

/**
 * This interface defines a Class that is referenceable.
 * This means that the primary object can be reference from
 * link. An example would be a User object, which can be 
 * referenced by the following link: /splatter/user/{userid}
 */
public interface IReferencable {
    
    /**
     * Gets the IReference for the primary
     * object.
     * @return IReference 
     */
    public IReference getReference();
}
