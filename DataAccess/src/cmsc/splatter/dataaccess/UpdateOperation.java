/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the operations available 
 * for Update objects.
 */
package cmsc.splatter.dataaccess;

/**
 * The operations available for Update objects:
 * Save - Saves the update (not displayed for other users)
 * Post - Posts the update (displayed for other users)
 */
public enum UpdateOperation {
    Save,
    Post
}
