/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.splatter.dataaccess;

/**
 *
 * @author Administrator
 */
public class DataAccessException extends Exception {
    
    /**
     * Default constructor.
     * @param msg - Exception message
     */
    public DataAccessException(String msg) {
        super(msg);
    }
}
