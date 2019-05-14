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
public class NotLoggedInException extends Exception {
    public NotLoggedInException(String msg)
    {
        super(msg);
    }
    public NotLoggedInException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
