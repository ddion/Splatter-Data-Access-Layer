/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the operations available 
 * for Request objects.
 */
package cmsc.splatter.dataaccess;

/**
 * The operations available for Request objects:
 * Accept - Accept the request (Connection is created)
 * Reject - Reject the request (Connection IS NOT created)
 */
public enum RequestOperation {
    Accept,
    Reject
}
