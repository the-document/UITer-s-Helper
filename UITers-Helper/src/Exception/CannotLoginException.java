/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author mirushi
 */
public class CannotLoginException extends Exception {
    public CannotLoginException(String msg)
    {
        super(msg);
    }
    public CannotLoginException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
