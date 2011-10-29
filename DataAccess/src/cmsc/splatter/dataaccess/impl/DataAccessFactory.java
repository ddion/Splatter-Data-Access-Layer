/*
 * @Course Name: CMSC 495
 * @Project Name: Splatter
 * @Author: David Dion
 * @Date: 10/12/2011
 * 
 * This file contains the DataAccessFactory.
 */
package cmsc.splatter.dataaccess.impl;

import cmsc.splatter.dataaccess.IDataAccess;

/**
 * Singleton factory class that returns the IDataAccess
 * implementation configured for the app.
 */
public class DataAccessFactory {    
    
    /**
     * Factory method that return the configured
     * IDataAccess implementation. This will be 
     * configured in the web.config file.
     * @return 
     */
    public static IDataAccess GetDataAccess() {       
        return new PersistenceDataAccess();
    }
    
}
