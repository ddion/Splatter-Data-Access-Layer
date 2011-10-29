/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the view type available 
 * for Update objects.
 */
package cmsc.splatter.dataaccess;

/**
 * The view types available for Update objects:
 * Followers - View all followers updates
 * Following - View all following users updates
 * Me - View all of the current users updates
 * All - View all updates
 */
public enum UpdateType {
    Followers,
    Following,
    Me,
    All
}
